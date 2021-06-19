package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.UserInfoResult;
import com.chen.smartcity.presenter.IUserInfoPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IUserInfoCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoInfoPresenter implements IUserInfoPresenter {

    private IUserInfoCallback mViewCallback = null;

    @Override
    public void getUserInfo(String token) {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<UserInfoResult> task = api.getUserInfo(token);
        task.enqueue(new Callback<UserInfoResult>() {
            @Override
            public void onResponse(Call<UserInfoResult> call, Response<UserInfoResult> response) {
                int code = response.code();
                LogUtils.d(UserInfoInfoPresenter.this, "getUserInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    UserInfoResult result = response.body();
                    LogUtils.d(UserInfoInfoPresenter.this, "getUserInfo result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedUserInfo(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResult> call, Throwable t) {
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUserInfoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IUserInfoCallback callback) {
        this.mViewCallback = null;
    }
}
