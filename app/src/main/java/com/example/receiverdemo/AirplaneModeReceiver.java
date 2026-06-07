package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {

            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

            String message = isAirplaneModeOn
                    ? "Mode avion activé"
                    : "Mode avion désactivé";

            Log.d("AirplaneReceiver", message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}