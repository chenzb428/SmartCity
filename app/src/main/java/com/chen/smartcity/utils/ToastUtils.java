package com.chen.smartcity.utils;

import android.widget.Toast;

import com.chen.smartcity.base.BaseApplication;

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }
}
