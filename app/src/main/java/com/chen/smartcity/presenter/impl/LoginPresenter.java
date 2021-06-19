package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.LoginParams;
import com.chen.smartcity.model.bean.LoginResult;
import com.chen.smartcity.presenter.ILoginPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.ILoginCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter implements ILoginPresenter {

    private ILoginCallback mViewCallback = null;

    @Override
    public void doLogin(String account, String password) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        LoginParams params = new LoginParams(account, password);
        Call<LoginResult> task = api.doLogin(params);
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                int code = response.code();
                LogUtils.d(LoginPresenter.this, "doLogin code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    LoginResult result = response.body();
                    LogUtils.d(LoginPresenter.this, "doLogin result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedLogin(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                LogUtils.d(LoginPresenter.this, "doLogin result === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(ILoginCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ILoginCallback callback) {
        this.mViewCallback = null;
    }
}
