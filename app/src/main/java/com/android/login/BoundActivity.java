package com.android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import Service.BoundService;

public class BoundActivity extends AppCompatActivity {

    public static TextView textView;
    private Button btnBind, btnUnbind;
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);

        textView = findViewById(R.id.tvTime);
        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);
        textView.setText("Current Time: " + new Date());

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoundActivity.this, BoundService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bound) {
                    unbindService(connection);
                    bound=false;
                }
            }
        });

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound=false;
        }
    };

}