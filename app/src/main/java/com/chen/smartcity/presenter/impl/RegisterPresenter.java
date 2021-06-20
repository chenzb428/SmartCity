package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.UserInfoParams;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IRegisterPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IRegisterCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterPresenter implements IRegisterPresenter {

    private IRegisterCallback mViewCallback = null;

    @Override
    public void doRegister(String account, String password, String email, String phone) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        UserInfoParams params = new UserInfoParams(account, "", password, email, phone, "", "");
        Call<Result> task = api.doRegister(params);
        task.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                int code = response.code();
                LogUtils.d(RegisterPresenter.this, "doRegister code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Result result = response.body();
                    LogUtils.d(RegisterPresenter.this, "doRegister result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onRegisterSuccess(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                LogUtils.d(RegisterPresenter.this, "doRegister error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IRegisterCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IRegisterCallback callback) {
        this.mViewCallback = null;
    }
}
