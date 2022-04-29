package com.android.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import model.UserModel;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName;
    private Button btnAddUser;
    private RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<UserModel> listUsers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        initViews();
        setListeners();
        checkFieldsForEmptyValues();
    }
    private void checkFieldsForEmptyValues() {
        String s1 = etFirstName.getText().toString();
        String s2 = etLastName.getText().toString();
        if (s1.equals("") || s2.equals("")) {
            btnAddUser.setEnabled(false);
        } else {
            btnAddUser.setEnabled(true);
        }
    }

    private void initViews(){
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnAddUser = findViewById(R.id.btnAddUserToRV);

        recyclerView = findViewById(R.id.recyclerView);
        // everyitem --> fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        setAnimation();
        listUsers = new ArrayList<>();
        adapter = new CustomAdapter(listUsers, this);
        listUsers.add(new UserModel("Riddhi", "Shah"));
        listUsers.add(new UserModel("Kavita", "Kuchalia"));
        listUsers.add(new UserModel("Sagar", "Shah"));
        listUsers.add(new UserModel("Shubh", "Shah"));
        listUsers.add(new UserModel("Viraj", "Shah"));
        listUsers.add(new UserModel("Shivani", "Kadam"));
        recyclerView.setAdapter(adapter);
    }

    private void setAnimation(){
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }
    private void addUserInList() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        UserModel listUser = new UserModel(firstName, lastName);
        listUsers.add(listUser);
        adapter.notifyDataSetChanged();
        setAnimation();
    }
    private void clearEditText() {
        etLastName.getText().clear();
        etFirstName.getText().clear();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setListeners(){
        etFirstName.setOnClickListener(this);
        etLastName.setOnClickListener(this);
        btnAddUser.setOnClickListener(this);
        etFirstName.addTextChangedListener(addUserTextWatcher);
        etLastName.addTextChangedListener(addUserTextWatcher);
    }

    private TextWatcher addUserTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = etFirstName.getText().toString().trim();
            String passwordInput = etLastName.getText().toString().trim();
            btnAddUser.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAddUserToRV:
                addUserInList();
                clearEditText();
                break;
        }
    }
}
