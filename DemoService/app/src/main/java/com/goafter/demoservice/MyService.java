package com.goafter.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
       return new CustomBinder();
    }

    class CustomBinder extends Binder {
        public void pringlog() {
            Log.e("tttttttttttttttt", "start bind");
        }


    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("My Service", "Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ttttttttttt", "Service started .............");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tttttttttttt", "Service is stopped");
    }

}
