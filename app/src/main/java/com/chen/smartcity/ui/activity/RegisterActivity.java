package com.chen.smartcity.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IRegisterPresenter;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.IRegisterCallback;

public class RegisterActivity extends BaseActivity implements IRegisterCallback {

    private EditText usernameEt, passwordEt, emailEt, phoneEt;
    private Button registerBt;
    private IRegisterPresenter mRegisterPresenter;

    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("注册");

        usernameEt = findViewById(R.id.register_account);
        passwordEt = findViewById(R.id.register_password);
        emailEt = findViewById(R.id.register_email);
        phoneEt = findViewById(R.id.register_phone);
        registerBt = findViewById(R.id.register_btn);
    }

    @Override
    protected void initPresenter() {
        mRegisterPresenter = PresenterManager.getInstance().getRegisterPresenter();
        mRegisterPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRegisterPresenter != null) {
                    String username = usernameEt.getText().toString().trim();
                    String password = passwordEt.getText().toString().trim();
                    String email = emailEt.getText().toString().trim();
                    String phone = phoneEt.getText().toString().trim();
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)) {
                        ToastUtils.showToast("不能为空！");
                        return;
                    }
                    mRegisterPresenter.doRegister(username, password, email, phone);
                }
            }
        });
    }

    @Override
    public void onRegisterSuccess(Result result) {
        ToastUtils.showToast(result.getMsg());
    }

    @Override
    public void onError() {
        ToastUtils.showToast("注册失败！请重试...");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    protected void release() {
        super.release();
        if (mRegisterPresenter != null) {
            mRegisterPresenter.unregisterViewCallback(this);
        }
    }
}