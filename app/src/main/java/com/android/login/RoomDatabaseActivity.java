package com.android.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Adapter.CustomerAdapter;
import Adapter.RoomAdapter;
import database.User;
import database.AppDatabase;
import model.CustomerModel;
import model.DatabaseHelper;
import model.UserModel;

public class RoomDatabaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAddUser, btnViewAll, btnDelete, btnSelectedData;
    private RecyclerView recyclerView;
    private EditText etName, etAge;
    private Switch stActive;
    private RoomAdapter userListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);
        initViews();
        setListener();
    }

    private void addNewUser(String name, int age, boolean isActive) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        User user = new User();
        user.user_name = name;
        user.age = age;
        user.isActive = isActive;
        db.userDao().insertUser(user);
        List<User> userList = db.userDao().getAllUsers();
        userListAdapter.setUserList(userList);
        resetParameters();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        etName.requestFocus();
    }

    private void resetParameters() {
        etName.setText("");
        etAge.setText("");
        stActive.setChecked(false);
        closeKeyboard();
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList = db.userDao().getAllUsers();
        userListAdapter.setUserList(userList);
        resetParameters();
        closeKeyboard();
    }

    private void deleteSelectedUsers() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        db.userDao().deleteSelectedUsers(true);
        List<User> userList = db.userDao().getAllUsers();
        userListAdapter.setUserList(userList);
    }

    private void setListener() {
        btnAddUser.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
//        btnSelectedData.setOnClickListener(this);
    }

    private void showSelectedData() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList = db.userDao().showSelectedData(true);
        userListAdapter.setUserList(userList);
        System.out.println("update selected user " + userList.toString());
    }

    private void initViews() {
        etName = findViewById(R.id.etName_rm);
        etAge = findViewById(R.id.etAge_rm);
        stActive = findViewById(R.id.stActive_rm);
        recyclerView = findViewById(R.id.recyclerView3);
        btnAddUser = findViewById(R.id.btnAdd_rm);
        btnViewAll = findViewById(R.id.btnViewAll_rm);
        btnDelete = findViewById(R.id.btnDelete_rm);

//        btnSelectedData = findViewById(R.id.btnShowSelectedData);

        // everyitem --> fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        userListAdapter = new RoomAdapter(this);
        recyclerView.setAdapter(userListAdapter);
        loadUserList();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd_rm) {

            if (etName.getText().toString().equals("") || etAge.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter name and age", Toast.LENGTH_SHORT).show();
            } else {
                addNewUser(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), stActive.isChecked());
            }
        } else if (v.getId() == R.id.btnViewAll_rm) {
            loadUserList();
        } else if (v.getId() == R.id.btnDelete_rm) {
            deleteSelectedUsers();
        }
//        else if(v.getId() == R.id.btnShowSelectedData){
//            showSelectedData();
//        }
    }
}