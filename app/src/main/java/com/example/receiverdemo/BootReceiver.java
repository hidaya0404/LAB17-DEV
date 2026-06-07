package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            Log.d("BootReceiver", "Le téléphone a démarré : BOOT_COMPLETED reçu");

            Toast.makeText(
                    context,
                    "BootReceiver déclenché après le démarrage",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}