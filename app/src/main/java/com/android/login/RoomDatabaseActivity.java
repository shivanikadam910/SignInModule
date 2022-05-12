package com.android.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Adapter.CustomerAdapter;
import Adapter.RoomAdapter;
import model.CustomerModel;
import model.DatabaseHelper;
import model.UserModel;

public class RoomDatabaseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private EditText etName, etAge;
    private Switch stActive;
    public List<CustomerModel> listUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);
        initViews();
    }

    private void initViews(){
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        stActive = findViewById(R.id.stActive);
        recyclerView = findViewById(R.id.recyclerView3);
        // everyitem --> fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        listUsers = new ArrayList<>();
        adapter = new RoomAdapter(listUsers, this);
        listUsers.add(new CustomerModel(1, "Riddhi", 23, true));
        listUsers.add(new CustomerModel(2, "Shivani", 23, true));
        listUsers.add(new CustomerModel(3, "Jay", 23, true));
        recyclerView.setAdapter(adapter);
    }
}