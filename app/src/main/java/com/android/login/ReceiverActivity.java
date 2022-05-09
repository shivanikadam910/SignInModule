package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import Receiver.FirstBroadcastReceiver;

public class ReceiverActivity extends AppCompatActivity {
    private TextView tvBattery;
    private FirstBroadcastReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        initViews();
        onShowBroadcast();
    }
    private void initViews(){
        tvBattery = findViewById(R.id.tvMessage);
    }

    private void onShowBroadcast() {
        batteryReceiver = new FirstBroadcastReceiver(tvBattery);
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryReceiver);
    }
}