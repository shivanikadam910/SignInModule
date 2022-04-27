package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.UserModel;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private EditText fName, lName, address, country, zipcode, email, phone;
    private Button btnSignup_signupScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
        setListeners();
    }

    private void initViews() {
        textview = findViewById(R.id.tvLogin);
        fName = findViewById(R.id.etFName);
        lName = findViewById(R.id.etLName);
        address = findViewById(R.id.etAddress);
        country = findViewById(R.id.etCountry);
        zipcode = findViewById(R.id.etZipcode);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        btnSignup_signupScreen = findViewById(R.id.btnSignup_signupScreen);
    }

    private void setListeners(){
        btnSignup_signupScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String firstName = fName.getText().toString();
        String lastName = lName.getText().toString();
        String address_string = address.getText().toString();
        String country_string = country.getText().toString();
        String zipcode_string = zipcode.getText().toString();
        String email_string = email.getText().toString();
        String phone_string = phone.getText().toString();

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(address_string) && TextUtils.isEmpty(country_string) &&TextUtils.isEmpty(zipcode_string) && TextUtils.isEmpty(email_string) && TextUtils.isEmpty(phone_string)) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("userModel", UserModel.getLoginUser());
            startActivity(intent);
//            intent.putExtra("lastName", lastName);
//            intent.putExtra("address_string", address_string);
//            intent.putExtra("country_string", country_string);
//            intent.putExtra("zipcode_string", zipcode_string);
//            intent.putExtra("email_string", email_string);
//            intent.putExtra("phone_string", phone_string);
            startActivity(intent);
        }

    }
}