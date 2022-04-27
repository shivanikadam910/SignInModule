package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.UserModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUserName, etPassword;
    private Button btnLogin, btnSignup_loginScreen;
    private String default_username = "shivani", default_password = "kadam";
    private String usernameInput;
    private String passwordInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setListeners();
        checkFieldsForEmptyValues();
    }

    private void checkFieldsForEmptyValues() {
        String s1 = etUserName.getText().toString();
        String s2 = etPassword.getText().toString();
        if (s1.equals("") || s2.equals("")) {
            btnLogin.setEnabled(false);
        } else {
            btnLogin.setEnabled(true);
        }
    }

    private void initViews() {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup_loginScreen = findViewById(R.id.btnSignUp_loginScreen);
    }
    private void setListeners() {
        btnLogin.setOnClickListener(this);
        btnSignup_loginScreen.setOnClickListener(this);
        etUserName.addTextChangedListener(loginTextWatcher);
        etPassword.addTextChangedListener(loginTextWatcher);
    }


    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            usernameInput = etUserName.getText().toString().trim();
            passwordInput = etPassword.getText().toString().trim();
            btnLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                System.out.println("username input : " + usernameInput);
                System.out.println("password input : " + passwordInput);
                if(usernameInput.equals(default_username) && passwordInput.equals(default_password)){
                    Log.d("loginActivity","if pass");
                    System.out.println("if pass");
                    Intent i1 = new Intent(this, HomeActivity.class);
                    i1.putExtra("userModel", UserModel.getLoginUser());
                    startActivity(i1);
                }
                else{
                    Toast.makeText(this,"Username and password don't match", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnSignUp_loginScreen:
                Intent i2 = new Intent(this, SignUpActivity.class);
                startActivity(i2);
                break;

        }
    }
}