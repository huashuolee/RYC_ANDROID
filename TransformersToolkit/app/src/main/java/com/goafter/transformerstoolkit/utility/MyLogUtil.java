package com.goafter.transformerstoolkit.utility;

import android.util.Log;

/**
 * Created by huashuolee on 2015/12/2.
 */
public class MyLogUtil {
    public static final int LOGON = 1;
    public static final int  LOGOFF= 2;
    public static final int LEVEL = LOGON;


    public void e(String tag, String msg){
        if (LEVEL == LOGON ) {
            Log.e(tag, msg);
        }
    }

}
