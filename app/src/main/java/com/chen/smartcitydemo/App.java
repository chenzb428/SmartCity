package com.chen.smartcitydemo;

import android.app.Application;

import com.chen.smartcitydemo.util.SpUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpUtils.getInstance(this);
    }
}
