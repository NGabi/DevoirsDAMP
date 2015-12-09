package com.example.ibagn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.ibagn.servicies.R;

/**
 * Created by gabi on 07.12.2015.
 */
public class MyService extends Service {
    private static int i=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        i++;
        Toast.makeText(this, "Login for the "+i+"th time!", Toast.LENGTH_SHORT).show();

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        super.onDestroy();

        // Tell the user we stopped.
        Toast.makeText(this, R.string.action_logout, Toast.LENGTH_SHORT).show();
    }


}
