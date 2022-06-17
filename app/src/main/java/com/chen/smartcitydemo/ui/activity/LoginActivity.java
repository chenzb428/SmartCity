package com.chen.smartcitydemo.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.presenter.ILoginPresenter;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.ILoginCallback;

public class LoginActivity extends BaseActivity implements ILoginCallback {

    private static final int REQUEST_CODE_REGISTER = 1000;

    public static final String RESULT_KEY_ACCOUNT = "result_key_account";
    public static final String RESULT_KEY_PASSWORD = "result_key_password";

    private EditText accountEt;
    private EditText passwordEt;
    private Button loginBtn;
    private TextView goRegisterTv;
    private ILoginPresenter loginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.SUCCESS);

        if (toolbar != null) {
            toolbar.setTitle("注册");
        }

        accountEt = findViewById(R.id.account_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        goRegisterTv = findViewById(R.id.login_go_register_tv);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        loginPresenter = PresenterManager.getInstance().getLoginPresenter();
        loginPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        super.initListener();

        loginBtn.setOnClickListener(v -> {
            String account = accountEt.getText().toString();
            String password = passwordEt.getText().toString();
            loginPresenter.doLogin(account, password);
        });

        goRegisterTv.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_CODE_REGISTER);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER && resultCode == RESULT_OK && data != null) {
            accountEt.setText(data.getStringExtra(RESULT_KEY_ACCOUNT));
            passwordEt.setText(data.getStringExtra(RESULT_KEY_PASSWORD));
        }
    }

    @Override
    public void onLoginSuccess() {
        toast("登录成功！");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String msg) {
        toast(msg);
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (accountEt != null) accountEt = null;
        if (passwordEt != null) passwordEt = null;
        if (loginBtn != null) loginBtn = null;
        if (goRegisterTv != null) goRegisterTv = null;
    }
}