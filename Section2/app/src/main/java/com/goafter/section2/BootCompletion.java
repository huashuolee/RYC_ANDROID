package com.goafter.section2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by huashuolee on 2015/11/3.
 */
public class BootCompletion extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("==========", "1111111111111111");
        /*Toast.makeText(context, "Bootup completed", Toast.LENGTH_SHORT).show();*/
    }
}
