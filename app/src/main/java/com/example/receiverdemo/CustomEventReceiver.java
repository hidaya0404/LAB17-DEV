package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (MainActivity.ACTION_CUSTOM_EVENT.equals(intent.getAction())) {

            String message = intent.getStringExtra("message");

            Log.d("CustomReceiver", "Broadcast custom reçu : " + message);

            Toast.makeText(
                    context,
                    "Custom Broadcast reçu : " + message,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}