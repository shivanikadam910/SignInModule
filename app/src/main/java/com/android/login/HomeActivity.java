package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Service.BackgroundService;
import Service.ForegroundService;
import model.UserModel;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnProfile, btnAddUser, btnPlace, btnAnimations, btnStartService, btnStopService, btnFGService, btnBoundService, btnReceiver, btnImageDownload, btnAddSQLite;
    private EditText etFirstName, etLastName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setListeners();

    }

    private void initViews() {
        btnProfile = findViewById(R.id.btnProfile);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnPlace = findViewById(R.id.btnPlace);
        btnAnimations = findViewById(R.id.btnAnimations);
        btnStartService = findViewById(R.id.btnStartBGService);
        btnStopService = findViewById(R.id.btnStopBGService);
        btnFGService = findViewById(R.id.btnForegroundService);
        btnBoundService = findViewById(R.id.btnBoundService);
        btnReceiver = findViewById(R.id.btnReceiver);
        btnImageDownload = findViewById(R.id.btnImageDownload);
        btnAddSQLite = findViewById(R.id.btnAddSQLite);
    }




    /*private void setData(){
        Intent intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        tvFName.setText(fName);
        String lName = intent.getStringExtra("lastName");
        tvLName.setText(lName);
        String address_string = intent.getStringExtra("address_string");
        tvAddress.setText(address_string);
        String country_string = intent.getStringExtra("country_string");
        tvCountry.setText(country_string);
        String zipcode_string = intent.getStringExtra("zipcode_string");
        tvZipcode.setText(zipcode_string);
        String email_string = intent.getStringExtra("email_string");
        tvEMail.setText(email_string);
        String phone_string = intent.getStringExtra("phone_string");
        tvPhone.setText(phone_string);
    }*/
    private void setListeners() {
        btnProfile.setOnClickListener(this);
        btnAddUser.setOnClickListener(this);
        btnPlace.setOnClickListener(this);
        btnAnimations.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnFGService.setOnClickListener(this);
        btnBoundService.setOnClickListener(this);
        btnReceiver.setOnClickListener(this);
        btnImageDownload.setOnClickListener(this);
        btnAddSQLite.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("userModel", UserModel.getLoginUser());
                startActivity(intent);
                break;
            case R.id.btnAddUser:
                Intent in = new Intent(this, AddUserActivity.class);
                startActivity(in);
                break;
            case R.id.btnPlace:
                Intent i = new Intent(this, PlacesActivity.class);
                startActivity(i);
                break;
            case R.id.btnAnimations:
                Intent i1 = new Intent(this, AnimationActivity.class);
                startActivity(i1);
                break;
            case R.id.btnStartBGService:
                startService(new Intent(this, BackgroundService.class));
                break;
            case R.id.btnStopBGService:
                stopService(new Intent(this, BackgroundService.class));
                break;
            case R.id.btnForegroundService:
                Intent i2 = new Intent(this, ForegroundActivity.class);
                startActivity(i2);
                break;
            case R.id.btnBoundService:
                Intent i3 = new Intent(this, BoundActivity.class);
                startActivity(i3);
                break;
            case R.id.btnReceiver:
                Intent i4 = new Intent(this, ReceiverActivity.class);
                startActivity(i4);
                break;
            case R.id.btnImageDownload:
                Intent i5 = new Intent(this, ImageDownloadActivity.class);
                startActivity(i5);
                break;
            case R.id.btnAddSQLite:
                Intent i6 = new Intent(this, SQLiteActivity.class);
                startActivity(i6);
                break;
        }
    }
}