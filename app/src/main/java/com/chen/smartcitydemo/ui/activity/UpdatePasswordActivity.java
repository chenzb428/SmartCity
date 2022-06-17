package com.chen.smartcitydemo.ui.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.presenter.IUpdatePasswordPresenter;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.IUpdatePasswordCallback;

public class UpdatePasswordActivity extends BaseActivity implements IUpdatePasswordCallback {

    private IUpdatePasswordPresenter updatePasswordPresenter;

    private EditText oldPswEt;
    private EditText newPswEt;
    private EditText newPsw2Et;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_password;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        menu.findItem(R.id.menu_text).setTitle("修改");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_text) {
            String oldPsw = oldPswEt.getText().toString();
            String newPsw = newPswEt.getText().toString();
            String new2Psw = newPsw2Et.getText().toString();

            if (!newPsw.equals(new2Psw)) {
                toast("两次输入新密码不一致，请重新输入！");
                return true;
            }

            updatePasswordPresenter.updatePassword(oldPsw, newPsw);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.SUCCESS);

        if (toolbar != null) {
            toolbar.setTitle("修改密码");
            setSupportActionBar(toolbar);
        }

        oldPswEt = findViewById(R.id.old_psw_et);
        newPswEt = findViewById(R.id.new_psw_et);
        newPsw2Et = findViewById(R.id.new_psw2_et);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        updatePasswordPresenter = PresenterManager.getInstance().getUpdatePasswordPresenter();
        updatePasswordPresenter.registerViewCallback(this);
    }

    @Override
    public void onUpdatePasswordSuccess(String msg) {
        toast(msg);
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
//        setUpState(State.ERROR);
        toast(msg);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (updatePasswordPresenter != null) {
            updatePasswordPresenter.unregisterViewCallback();
            updatePasswordPresenter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (oldPswEt != null) oldPswEt = null;
        if (newPswEt != null) oldPswEt = null;
        if (newPsw2Et != null) oldPswEt = null;
    }
}