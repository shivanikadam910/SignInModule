package com.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeNewActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnJsonParseGet, btnJsonParsePost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        initViews();
        setListeners();
    }
    private void initViews(){
        btnJsonParseGet = findViewById(R.id.btnJsonParseGet);
        btnJsonParsePost = findViewById(R.id.btnJsonParsePost);

    }
    private void setListeners(){
        btnJsonParseGet.setOnClickListener(this);
        btnJsonParsePost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnJsonParseGet:
                Intent i0 = new Intent(this, JsonParsingActivity.class);
                startActivity(i0);
                break;
            case R.id.btnJsonParsePost:
                Intent i1 = new Intent(this, JsonParsingActivityPost.class);
                startActivity(i1);
                break;
        }
    }
}
