package com.chen.smartcity.base;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chen.smartcity.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
        initPresenter();
        initListener();
        loadData();
    }

    protected void setSupportActionBar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void setActionBarIcon(int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void loadData() {
    }

    protected void initPresenter() {
    }

    protected void initListener() {
    }

    protected void initView() {
    }

    protected abstract int getLayoutResId();

    public void insertByKey(String key, String value) {
        SharedPreferences sp = getSharedPreferences("sp_SmartCity", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String findByKey(String key) {
        SharedPreferences sp = getSharedPreferences("sp_SmartCity", MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public void removeByKey(String key) {
        SharedPreferences sp = getSharedPreferences("sp_SmartCity", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    protected void release() {
    }
}
