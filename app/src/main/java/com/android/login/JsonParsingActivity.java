package com.android.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.JsonDataAdapter;
import model.JsonModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonParsingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<JsonModel> jsonList;
    private JsonDataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing);
        initViews();
//        getJsonData();
        printJsonData();
    }


    private void initViews() {
        recyclerView = findViewById(R.id.rvJsonData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jsonList = new ArrayList<>();
        adapter = new JsonDataAdapter(jsonList, this);
    }


    private void getJsonData() {
        Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().getString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("response body : " + response.body());
                try {
                    String strJson = response.body().string();
                    JSONArray jsonArray = new JSONArray(strJson);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = Integer.parseInt(jsonObject.optString("id").toString());
                        String name = jsonObject.optString("name").toString();
                        String email = jsonObject.optString("email").toString();
                        String gender = jsonObject.opt("gender").toString();
                        String status = jsonObject.optString("status").toString();
                        JsonModel jsonModel = new JsonModel();
                        jsonModel.setId(id);
                        jsonModel.setName(name);
                        jsonModel.setEmail(email);
                        jsonModel.setGender(gender);
                        jsonModel.setStatus(status);

                        jsonList.add(jsonModel);
                    }
                    adapter.addData(jsonList);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ex) {
                    System.out.println("fails");
                    ex.printStackTrace();
                }
//                jsonList.add(new JsonModel());


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("fails");
            }
        });
    }


    private void printJsonData() {
        Call<List<JsonModel>> call = RetrofitClient.getInstance().getMyApi().getUserModel();
        call.enqueue(new Callback<List<JsonModel>>() {
            @Override
            public void onResponse(Call<List<JsonModel>> call, Response<List<JsonModel>> response) {
                jsonList = response.body();
                adapter.addData(jsonList);
                System.out.println("listUser : " + jsonList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<JsonModel>> call, Throwable t) {
                System.out.println("fails");

            }
        });


    }
}
