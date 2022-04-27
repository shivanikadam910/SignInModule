package com.android.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import model.UserModel;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnProfile, btnAddUser;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<UserModel> listUsers;
    private EditText etFirstName, etLastName;
    private ImageButton ibUpdate, ibDelete;
    private EditText etUpdateFName, etUpdateLName;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setListeners();
    }

    private void initViews() {
        btnProfile = findViewById(R.id.btnProfile);
        btnAddUser = findViewById(R.id.btnAddUSer);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        ibUpdate = findViewById(R.id.ibUpdate);
        ibDelete = findViewById(R.id.ibDelete);




        recyclerView = findViewById(R.id.recyclerView);
        // everyitem --> fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        listUsers = new ArrayList<>();
        adapter = new CustomAdapter(listUsers, this);
        recyclerView.setAdapter(adapter);
    }

    private void addUserInList() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        UserModel listUser = new UserModel(firstName, lastName);
        listUsers.add(listUser);
        adapter.notifyDataSetChanged();
    }
    private void updateUserInList(int count, String firstName, String lastName) {
        UserModel listUser = new UserModel(firstName, lastName);
        listUsers.set(count, listUser);
        adapter.notifyDataSetChanged();
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
    }

    private void clearEditText() {
        etFirstName.getText().clear();
        etLastName.getText().clear();
    }

    private void showAlertDialogButtonClicked(View view) {
        // set the custom layout
        builder = new AlertDialog.Builder(this);
        View customLayout = getLayoutInflater().inflate(R.layout.dialog_update, null);
        builder.setView(customLayout);
        etUpdateFName = customLayout.findViewById(R.id.etUpdateFName);
        etUpdateLName = customLayout.findViewById(R.id.etUpdateLName);

        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        builder.setTitle(firstName + lastName);
        // add a button
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // send data from the AlertDialog to the Activity
                updateUserInList(which, etUpdateFName.getText().toString(), etUpdateLName.toString());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("userModel", UserModel.getLoginUser());
                startActivity(intent);
                break;
            case R.id.btnAddUSer:
                addUserInList();
                clearEditText();
                break;
            case R.id.ibUpdate:
                System.out.println("update icon");
                showAlertDialogButtonClicked(view);
                break;
            case R.id.ibDelete:
                break;
        }
    }
}