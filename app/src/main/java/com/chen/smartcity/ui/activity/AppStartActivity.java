package com.chen.smartcity.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;

public class AppStartActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_app_start;
    }

    @Override
    protected void initView() {
        super.initView();
        startActivity();
    }

    private void startActivity() {
        SharedPreferences sp = getSharedPreferences("sp_SmartCity", MODE_PRIVATE);
        Boolean isFirstLoading = sp.getBoolean("isFirstLoading", true);

        if (isFirstLoading) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirstLoading", false);
            editor.commit();
            startActivity(new Intent(getBaseContext(), GuideActivity.class));
            finish();
        }else {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }
}