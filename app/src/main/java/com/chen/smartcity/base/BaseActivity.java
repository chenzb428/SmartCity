package com.chen.smartcity.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
        initListener();
    }

    protected void initListener() {
    }

    protected void initView() {
    }

    protected abstract int getLayoutResId();
}
