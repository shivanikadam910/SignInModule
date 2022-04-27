package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import model.UserModel;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvFName, tvLName, tvAddress, tvCountry, tvZipcode, tvEMail, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        setData();
    }

    private void initViews() {
        tvFName = findViewById(R.id.tvFName_profile);
        tvLName = findViewById(R.id.tvLname_profile);
        tvAddress = findViewById(R.id.tvAddress_profile);
        tvCountry = findViewById(R.id.tvCountry_profile);
        tvZipcode = findViewById(R.id.tvZipcode_profile);
        tvEMail = findViewById(R.id.tvEmail_profile);
        tvPhone = findViewById(R.id.tvPhone_profile);

    }


    private void setData() {
        UserModel user = new UserModel("","");
        user = (UserModel) getIntent().getSerializableExtra("userModel");
        tvFName.setText(user.getfName());
        tvLName.setText(user.getlName());
        tvAddress.setText(user.getAddress());
        tvCountry.setText(user.getCountry());
        tvZipcode.setText(Integer.toString(user.getZipcode()));
        tvEMail.setText(user.getEmail());
        tvPhone.setText(Integer.toString(user.getPhone()));

//        Intent intent = getIntent();
//        String fName = intent.getStringExtra("firstName");
//        tvFName.setText(fName);
//        String lName = intent.getStringExtra("lastName");
//        tvLName.setText(lName);
//        String address_string = intent.getStringExtra("address_string");
//        tvAddress.setText(address_string);
//        String country_string = intent.getStringExtra("country_string");
//        tvCountry.setText(country_string);
//        String zipcode_string = intent.getStringExtra("zipcode_string");
//        tvZipcode.setText(zipcode_string);
//        String email_string = intent.getStringExtra("email_string");
//        tvEMail.setText(email_string);
//        String phone_string = intent.getStringExtra("phone_string");
//        tvPhone.setText(phone_string);
    }
}