package com.example.receiverdemo;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_CUSTOM_EVENT =
            "com.example.receiverdemo.CUSTOM_EVENT";

    private AirplaneModeReceiver airplaneModeReceiver;
    private CustomEventReceiver customEventReceiver;

    private TextView tvStatus;
    private Button btnSendCustomBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        btnSendCustomBroadcast = findViewById(R.id.btnSendCustomBroadcast);

        airplaneModeReceiver = new AirplaneModeReceiver();
        customEventReceiver = new CustomEventReceiver();

        registerAirplaneModeReceiver();
        registerCustomEventReceiver();

        btnSendCustomBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent(ACTION_CUSTOM_EVENT);
            intent.setPackage(getPackageName());
            intent.putExtra("message", "Événement envoyé depuis MainActivity");

            sendBroadcast(intent);

            tvStatus.setText("Broadcast custom envoyé depuis MainActivity");
        });
    }

    private void registerAirplaneModeReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                    airplaneModeReceiver,
                    filter,
                    Context.RECEIVER_EXPORTED
            );
        } else {
            registerReceiver(airplaneModeReceiver, filter);
        }
    }

    private void registerCustomEventReceiver() {
        IntentFilter filter = new IntentFilter(ACTION_CUSTOM_EVENT);

        ContextCompat.registerReceiver(
                this,
                customEventReceiver,
                filter,
                ContextCompat.RECEIVER_NOT_EXPORTED
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(airplaneModeReceiver);
        unregisterReceiver(customEventReceiver);
    }
}