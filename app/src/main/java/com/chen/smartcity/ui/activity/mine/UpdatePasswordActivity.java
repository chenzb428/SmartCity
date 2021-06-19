package com.chen.smartcity.ui.activity.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.utils.ToastUtils;

public class UpdatePasswordActivity extends BaseActivity {

    private EditText oldEt, newEt;
    private Button okBtn;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("修改密码");

        oldEt = findViewById(R.id.password_old_et);
        newEt = findViewById(R.id.password_new_et);
        okBtn = findViewById(R.id.password_ok);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPsw = oldEt.getText().toString().trim();
                String newPsw = newEt.getText().toString().trim();
                if (TextUtils.isEmpty(oldPsw) || TextUtils.isEmpty(newPsw)) {
                    ToastUtils.showToast("不能为空！");
                } else {
                    ToastUtils.showToast("为方便大家使用，暂不提供...");
                }
            }
        });
    }
}