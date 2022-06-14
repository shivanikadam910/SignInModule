package com.android.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.JsonModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonParsingActivityPost extends AppCompatActivity implements View.OnClickListener {
    private EditText etJsonName, etJsonEmail, etJsonGender, etJsonStatus;
    private Button btnJsonPost;
    private ProgressBar pgLoading;
    private TextView tvResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing_post);
        initViews();
        setListeners();
    }

    private void initViews() {
        etJsonName = findViewById(R.id.etJsonName);
        etJsonEmail = findViewById(R.id.etJsonEmail);
        etJsonGender = findViewById(R.id.etJsonGender);
        etJsonStatus = findViewById(R.id.etJsonStatus);
        btnJsonPost = findViewById(R.id.btnPost);
        pgLoading = findViewById(R.id.pgLoading);
        tvResponse = findViewById(R.id.tvResponse);
    }

    private RequestBody getRequestBody(String jsonStr) {
        return RequestBody.create(jsonStr, okhttp3.MediaType.parse("application/json; charset=utf-8"));
    }

    private void setListeners() {
        btnJsonPost.setOnClickListener(this);
    }

    private void PostData(String name, String email, String gender, String status) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("gender", gender);
        jsonObject.put("status", status);

        Call<JsonModel> call = RetrofitClient.getInstance().getMyApi().createPost(getRequestBody(jsonObject.toString()));
        call.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
//                System.out.println("response body" + response.body());
                if (response.isSuccessful()) {
                    pgLoading.setVisibility(View.GONE);
                    etJsonName.setText("");
                    etJsonEmail.setText("");
                    etJsonGender.setText("");
                    etJsonStatus.setText("");

                    JsonModel jsonModelnew = response.body();
                    String responseString = "\n id: " + jsonModelnew.getId() + "\n name: " + jsonModelnew.getName() + "\n email: " + jsonModelnew.getEmail() + "\n gender: " + jsonModelnew.getGender() + "\n status: " + jsonModelnew.getStatus();
                    tvResponse.setText(responseString);
                } else if(response.code() == 422) {
                    try {
                        Log.e("JsonParsingActivity", response.errorBody().string());
                        Toast.makeText(JsonParsingActivityPost.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        System.out.println("error body" + response.errorBody());
                        if(response.errorBody().string().equals(null)){

                        }
                        else{
//                            String jsonStr = response.errorBody().string();
//                            Log.e("Jsonparsing post : " , jsonStr);
//                            JSONArray jsonArray = new JSONArray(response.errorBody().string());
//                            String data = null;
//                            for(int i=0; i<jsonArray.length(); i++){
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                String field = jsonObject.optString("field").toString();
//                                String msg = jsonObject.optString("message").toString();
//                                data += field + " " + msg + " ";
//                                Log.e("Jsonactivity", data);
//                            }
//                            Toast.makeText(JsonParsingActivityPost.this, data , Toast.LENGTH_SHORT).show();
                            Toast.makeText(JsonParsingActivityPost.this, "Enter field values correctly" , Toast.LENGTH_SHORT).show();
                        }
                        pgLoading.setVisibility(View.GONE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
                System.out.println("fails");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPost) {
            if (etJsonName.getText().toString().isEmpty() &&
                    etJsonEmail.getText().toString().isEmpty() && etJsonGender.getText().toString().isEmpty() && etJsonStatus.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter field values.", Toast.LENGTH_LONG).show();
            } else {
                try {
                    pgLoading.setVisibility(View.VISIBLE);
                    PostData(etJsonName.getText().toString(), etJsonEmail.getText().toString(), etJsonGender.getText().toString(), etJsonStatus.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
