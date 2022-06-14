package com.android.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Adapter.SeriesAdapter;
import model.SeriesNumberModel;
import model.UserModel;

public class FibonacciListActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView etFirstNumber, etSecondNumber;
    private Button btnShowSeries, btnshowPrev;
    public SeriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_list);
        initViews();
        setListeners();
    }

    private void initViews() {
        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        btnShowSeries = findViewById(R.id.btnShowFibo);
        btnshowPrev = findViewById(R.id.btnshowPrev);
    }

    private void setListeners() {
        btnShowSeries.setOnClickListener(this);
        btnshowPrev.setOnClickListener(this);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void clearInputs(){
        etFirstNumber.setText("");
        etSecondNumber.setText("");
        closeKeyboard();
        etSecondNumber.clearFocus();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowFibo) {
            if (etFirstNumber.getText().toString().isEmpty() || etSecondNumber.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter all numbers", Toast.LENGTH_SHORT).show();
            } else if (Integer.parseInt(etFirstNumber.getText().toString()) == 0 && Integer.parseInt(etSecondNumber.getText().toString()) == 0) {
                Toast.makeText(this, "Enter valid numbers. Series not possible", Toast.LENGTH_SHORT).show();
            }
            else if(Integer.parseInt(etFirstNumber.getText().toString()) >= Integer.parseInt(etSecondNumber.getText().toString())){
                Toast.makeText(this, "First number should be smaller than second number.", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, ShowSeriesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstNum", etFirstNumber.getText().toString());
                bundle.putString("secondNum", etSecondNumber.getText().toString());
                bundle.putString("series", "forward");
                intent.putExtras(bundle);
                startActivity(intent);
                clearInputs();
            }
        }
        else if(v.getId() == R.id.btnshowPrev){
            if (etFirstNumber.getText().toString().isEmpty() || etSecondNumber.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter all numbers", Toast.LENGTH_SHORT).show();
            } else if (Integer.parseInt(etFirstNumber.getText().toString()) == 0 && Integer.parseInt(etSecondNumber.getText().toString()) == 0) {
                Toast.makeText(this, "Enter valid numbers. Series not possible", Toast.LENGTH_SHORT).show();
            }
            else if(Integer.parseInt(etFirstNumber.getText().toString()) >= Integer.parseInt(etSecondNumber.getText().toString())){
                Toast.makeText(this, "First number should be smaller than second number.", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, ShowSeriesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstNum", etFirstNumber.getText().toString());
                bundle.putString("secondNum", etSecondNumber.getText().toString());
                bundle.putString("series", "backward");
                intent.putExtras(bundle);
                startActivity(intent);
                clearInputs();
            }
        }
    }
}