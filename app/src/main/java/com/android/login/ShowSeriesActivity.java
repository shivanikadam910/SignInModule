package com.android.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Adapter.SeriesAdapter;
import model.SeriesNumberModel;

public class ShowSeriesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_series);
        initViews();
        setSeries();
    }

    private RecyclerView recyclerView;
    private SeriesAdapter adapter;
    private List<SeriesNumberModel> listNumbers, listSeekValues;
    public static int MAX = 0;

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewListSeries);
        // everyitem --> fixed size
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
    }


    private BigInteger[] FibonacciSeries(BigInteger i1, BigInteger i2) {
        BigInteger n1 = i1, n2 = i2, n3;
        int count = 100;
        BigInteger[] arrFibo = new BigInteger[count];
        arrFibo[0] = i1;
        arrFibo[1] = i2;
        System.out.print(n1 + " " + n2);//printing first 2
        for (int i = 2; i < count; ++i)//loop starts from 2 because first 2 are already printed
        {
            n3 = n1.add(n2);
            System.out.print(" " + n3);
            arrFibo[i] = n3;
            n1 = n2;
            n2 = n3;
        }
        return arrFibo;
    }

    private int[] FibonacciSeriesPreviousNums(int i1, int i2) {
        int n1 = i1, n2 = i2, n3;
        int count = 0;

        n3 = n1 - n2;
        if(n3 <= n2){
            do {
                n1 = n2;
                n2 = n3;
                n3 = n1 - n2;
                count++;
                System.out.println(" n1 " + n1 + " n2 " + n2 + " n3 " + n3);
                System.out.println("count : " + count);
            }
            while (n3 <= n2);
        }

        Log.e("count", String.valueOf(count));
        int[] arrFibo = new int[count + 2];
        int l3 = 0;
        arrFibo[count] = i2;
        arrFibo[count + 1] = i1;
        for (int i = arrFibo.length - 3; i >= 0; i--) {
            l3 = i1 - i2;
            arrFibo[i] = l3;
            i1 = i2;
            i2 = l3;
        }
        return arrFibo;
    }


    private void setSeries() {
        Bundle bundle = getIntent().getExtras();
        String series = bundle.getString("series", "Default");
        if (series.equals("forward")) {
            forwardSeries();
        } else if (series.equals("backward")) {
            backwardSeries();
        }
    }

    private void forwardSeries() {
        Bundle bundle = getIntent().getExtras();
        String firstNum = bundle.getString("firstNum", "Default");
        String secondNum = bundle.getString("secondNum", "Default");
        BigInteger[] arrFibo;
        arrFibo = FibonacciSeries(new BigInteger(firstNum), new BigInteger(secondNum));
        for (int i = 0; i < arrFibo.length; i++) {
            Log.e("fibo", String.valueOf(arrFibo[i]));
        }
        listNumbers = new ArrayList<>();
        listSeekValues = new ArrayList<>();
        adapter = new SeriesAdapter(listNumbers, listSeekValues, this);
        for (int i = 0; i < arrFibo.length; i++) {
            listNumbers.add(new SeriesNumberModel(arrFibo[i]));
        }
        BigInteger max = maxNumBigInteger(firstNum, secondNum);

        BigInteger[] seekValues = seekValuesBigInteger(max, arrFibo);
        for (int i = 0; i < seekValues.length; i++) {
            listSeekValues.add(new SeriesNumberModel(seekValues[i]));
        }
        recyclerView.setAdapter(adapter);
    }

    private void backwardSeries() {
        Bundle bundle = getIntent().getExtras();
        String firstNum = bundle.getString("firstNum", "Default");
        String secondNum = bundle.getString("secondNum", "Default");
        int[] arrFibo;
        arrFibo = FibonacciSeriesPreviousNums(Integer.parseInt(secondNum), Integer.parseInt(firstNum));

        listNumbers = new ArrayList<>();
        listSeekValues = new ArrayList<>();

        adapter = new SeriesAdapter(listNumbers, listSeekValues, this);
        for (int i = 0; i < arrFibo.length; i++) {
            listNumbers.add(new SeriesNumberModel(new BigInteger(String.valueOf(arrFibo[i]))));
        }

        int max = maxNum(firstNum, secondNum);
        int[] seekValues = seekValues(max, arrFibo);
        for (int i = 0; i < seekValues.length; i++) {
            listSeekValues.add(new SeriesNumberModel(seekValues[i]));
        }
        System.out.println("listNums" + listNumbers);
        System.out.println("listSeek" + listSeekValues);
        System.out.println("Max num " + MAX);
        recyclerView.setAdapter(adapter);
    }

    private int maxNum(String firstNum, String secondNum){
        int[] arrFibo;
        arrFibo = FibonacciSeriesPreviousNums(Integer.parseInt(secondNum), Integer.parseInt(firstNum));
        int maxNum = arrFibo[0];
        for (int j : arrFibo) {
            if (j > maxNum)
                maxNum = j;
        }
        MAX = maxNum;
        return maxNum;
    }

    private BigInteger maxNumBigInteger(String firstNum, String secondNum){
        BigInteger[] arrFibo;
        arrFibo = FibonacciSeries(new BigInteger(firstNum), new BigInteger(secondNum));
        BigInteger maxNum = arrFibo[0];
        for (BigInteger j : arrFibo) {
            if(j.compareTo(maxNum)>0){
                maxNum=j;
            }
        }
        return maxNum;
    }

    private int[] seekValues(int max, int[] arrFibo){
        int[] seekValue = new int[arrFibo.length];
        for(int i=0; i<arrFibo.length; i++){
            seekValue[i] = arrFibo[i] * 100/max;
        }

        System.out.println("max" + max);
        for(int i =0; i<seekValue.length; i++){
            System.out.println("seekvalue : " + seekValue[i]);
        }
        return seekValue;
    }

    private BigInteger[] seekValuesBigInteger(BigInteger max, BigInteger[] arrFibo){
        BigInteger[] seekValue = new BigInteger[arrFibo.length];
        for(int i=0; i<arrFibo.length; i++){
            seekValue[i] = arrFibo[i].multiply(BigInteger.valueOf(100)).divide(max);
        }

        System.out.println("max" + max);
        for(int i =0; i<seekValue.length; i++){
            System.out.println("seekvalue : " + seekValue[i]);
        }
        return seekValue;
    }


}
