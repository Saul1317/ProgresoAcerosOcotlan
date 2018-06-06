package com.acerosocotlan.progresoacerosocotlan.Modelo;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ValidarDescarga extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
            Toast.makeText(context,"Descarga completada", Toast.LENGTH_LONG).show();
        }
    }
}
