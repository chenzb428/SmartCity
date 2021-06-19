package com.chen.smartcity.ui.activity.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;

public class DingdanActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dingdan;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("订单列表");
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}