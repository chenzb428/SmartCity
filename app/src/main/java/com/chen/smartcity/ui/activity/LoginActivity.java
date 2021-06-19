package com.chen.smartcity.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.LoginResult;
import com.chen.smartcity.presenter.ILoginPresenter;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.ILoginCallback;

public class LoginActivity extends BaseActivity implements ILoginCallback {

    private EditText accountEt, passwordEt;
    private Button loginBtn;
    private TextView registerTv;
    private ILoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("登录");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        accountEt = findViewById(R.id.login_account);
        passwordEt = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        registerTv = findViewById(R.id.login_register);
    }

    @Override
    protected void initPresenter() {
        mLoginPresenter = PresenterManager.getInstance().getLoginPresenter();
        mLoginPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoginPresenter != null) {
                    String account = accountEt.getText().toString().trim();
                    String password = passwordEt.getText().toString().trim();
                    mLoginPresenter.doLogin(account, password);
                }
            }
        });
    }

    @Override
    public void onLoadedLogin(LoginResult result) {
        if (result.getCode() == 200) {
            insertByKey("token", result.getToken());
            setResult(RESULT_OK);
            finish();
        }
        ToastUtils.showToast(result.getMsg());
    }

    @Override
    public void onError() {
        ToastUtils.showToast("登录失败！请重新登录...");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}