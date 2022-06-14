package com.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeNewActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnJsonParseGet, btnJsonParsePost, btnSharedPref, btnFibonacci, btnJsonXml;
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
        btnSharedPref = findViewById(R.id.btnSharedPref);
        btnFibonacci = findViewById(R.id.btnFibonacci);
        btnJsonXml = findViewById(R.id.btnJsonXml);
    }
    private void setListeners(){
        btnJsonParseGet.setOnClickListener(this);
        btnJsonParsePost.setOnClickListener(this);
        btnSharedPref.setOnClickListener(this);
        btnFibonacci.setOnClickListener(this);
        btnJsonXml.setOnClickListener(this);
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
            case R.id.btnSharedPref:
                Intent i2 = new Intent(this, SharedPrefActivity.class);
                startActivity(i2);
                break;
            case R.id.btnFibonacci:
                Intent i3 = new Intent(this, FibonacciListActivity.class);
                startActivity(i3);
                break;
            case R.id.btnJsonXml:
                Intent i4 = new Intent(this, ShowJsonXmlActivity.class);
                startActivity(i4);
                break;
        }
    }
}
