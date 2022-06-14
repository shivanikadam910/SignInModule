package com.android.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPrefActivity extends AppCompatActivity {
    private EditText etName_shared, etAge_shared;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        initViews();
    }

    private void initViews(){
        etName_shared = findViewById(R.id.etName_shared);
        etAge_shared = findViewById(R.id.etAge_shared);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", etName_shared.getText().toString());
        myEdit.putInt("age", Integer.parseInt(etAge_shared.getText().toString()));
        myEdit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);

        // Setting the fetched data
        // in the EditTexts
        etName_shared.setText(s1);
        etAge_shared.setText(String.valueOf(a));
    }
}
