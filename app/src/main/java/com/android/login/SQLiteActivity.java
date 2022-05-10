package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import model.CustomerModel;
import model.DatabaseHelper;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnViewAll, btnAdd, btnDeleteAll;
    private Switch stActive;
    private EditText etName, etAge;
    private ListView lsUsers;
    private ArrayAdapter customerArray;
    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initViews();
        setListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        stActive = findViewById(R.id.stActive);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnAdd = findViewById(R.id.btnAdd);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        lsUsers = findViewById(R.id.lsUsers);

        helper = new DatabaseHelper(this);
        customerArray = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, helper.getEveryone());
        lsUsers.setAdapter(customerArray);


    }

    private void setListeners() {
        btnAdd.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);

        lsUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("hii***********");
                System.out.println(position + "position");
                CustomerModel clickCustomer = (CustomerModel) parent.getItemAtPosition(position);
                helper.deleteOne(clickCustomer);
                customerArray = new ArrayAdapter<CustomerModel>(SQLiteActivity.this, android.R.layout.simple_list_item_1, helper.getEveryone());
                lsUsers.setAdapter(customerArray);
                Toast.makeText(SQLiteActivity.this, " DELETED " + clickCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addUser() {
        CustomerModel cModel;
        try {
            cModel = new CustomerModel(1, etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), stActive.isChecked());
            Toast.makeText(this, cModel.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Error while entering record.", Toast.LENGTH_SHORT).show();
            System.out.println("Error while entering record.");
            cModel = new CustomerModel(1, "error", 0, false);
//           cModel = new cModel
        }
        boolean success = helper.addOne(cModel);
        customerArray = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, helper.getEveryone());
        lsUsers.setAdapter(customerArray);
        Toast.makeText(this, "Success " + success, Toast.LENGTH_SHORT).show();

    }

    private void viewUsers() {
        customerArray = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, helper.getEveryone());
        System.out.println(helper.getEveryone() + "getUser");
        lsUsers.setAdapter(customerArray);
//        Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show();
    }

    private void deleteAll() {
        helper.deleteAll();
        customerArray = new ArrayAdapter<CustomerModel>(this, android.R.layout.simple_list_item_1, helper.getEveryone());
        lsUsers.setAdapter(customerArray);
        Toast.makeText(this, "Success ", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addUser();

                break;
            case R.id.btnViewAll:
                viewUsers();
                break;
            case R.id.btnDeleteAll:
                deleteAll();
        }
    }
}