package com.chen.smartcitydemo.util;

import android.util.Log;

public class LogUtils {

    private static final int INFO_LEV = 1;
    private static final int WARNING_LEV = 2;
    private static final int DEBUG_LEV = 3;
    private static final int ERROR_LEV = 4;

    private static int currentLev = 4;

    public static void i(Object o, String msg) {
        if (currentLev >= INFO_LEV) {
            Log.i(o.getClass().getSimpleName(), msg);
        }
    }

    public static void w(Object o, String msg) {
        if (currentLev >= WARNING_LEV) {
            Log.w(o.getClass().getSimpleName(), msg);
        }
    }

    public static void d(Object o, String msg) {
        if (currentLev >= DEBUG_LEV) {
            Log.d(o.getClass().getSimpleName(), msg);
        }
    }

    public static void e(Object o, String msg) {
        if (currentLev >= ERROR_LEV) {
            Log.e(o.getClass().getSimpleName(), msg);
        }
    }
}
