package com.chen.smartcitydemo.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.presenter.IRegisterPresenter;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.IRegisterCallback;

public class RegisterActivity extends BaseActivity implements IRegisterCallback {

    private IRegisterPresenter registerPresenter;

    private EditText accountEt;
    private EditText passwordEt;
    private EditText phoneNumEt;
    private RadioGroup sexGroup;
    private Button registerBtn;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
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
        phoneNumEt = findViewById(R.id.phone_num_et);
        sexGroup = findViewById(R.id.sex_layout_rg);
        registerBtn = findViewById(R.id.register_btn);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        registerPresenter = PresenterManager.getInstance().getRegisterPresenter();
        registerPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        registerBtn.setOnClickListener(v -> {
            String account = accountEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            String phoneNum = phoneNumEt.getText().toString().trim();
            String sex = (sexGroup.getCheckedRadioButtonId() == R.id.sex_man_rb) ? "0" : "1";

            User.UserBean user = new User.UserBean(account, password, "", "", phoneNum, sex, "", "", 0, 0);
            registerPresenter.doRegister(user);
        });
    }

    @Override
    public void onRegisterSuccess() {
        toast("注册成功！");

        Intent intent = new Intent();
        intent.putExtra(LoginActivity.RESULT_KEY_ACCOUNT, accountEt.getText().toString().trim());
        intent.putExtra(LoginActivity.RESULT_KEY_PASSWORD, passwordEt.getText().toString().trim());
        setResult(RESULT_OK, intent);
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
    protected void releaseInterface() {
        super.releaseInterface();
        if (registerPresenter != null) {
            registerPresenter.unregisterViewCallback();
            registerPresenter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (accountEt != null) accountEt = null;
        if (passwordEt != null) passwordEt = null;
        if (phoneNumEt != null) phoneNumEt = null;
        if (sexGroup != null) sexGroup = null;
        if (registerBtn != null) registerBtn = null;
    }
}