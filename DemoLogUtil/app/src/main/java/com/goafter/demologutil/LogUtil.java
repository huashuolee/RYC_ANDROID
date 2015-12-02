package com.goafter.demologutil;

import android.util.Log;

/**
 * Created by huashuolee on 2015/12/2.
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int  DEBUG= 2;
    public static final int INFO = 3;
    public static final int WARNING = 4;
    public static final int  ERROR= 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;

    public void v(String tag, String msg){
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public void d(String tag, String msg){
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public void i(String tag, String msg){
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public void w(String tag, String msg){
        if (LEVEL <= WARNING) {
            Log.w(tag, msg);
        }
    }

    public void e(String tag, String msg){
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}
