package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.MineUserResult;
import com.chen.smartcity.presenter.IMinePresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IMineCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MineUserInfoPresenter implements IMinePresenter {

    private IMineCallback mViewCallback = null;

    @Override
    public void getUserInfo(String token) {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<MineUserResult> task = api.getUserInfo(token);
        task.enqueue(new Callback<MineUserResult>() {
            @Override
            public void onResponse(Call<MineUserResult> call, Response<MineUserResult> response) {
                int code = response.code();
                LogUtils.d(MineUserInfoPresenter.this, "getUserInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    MineUserResult result = response.body();
                    LogUtils.d(MineUserInfoPresenter.this, "getUserInfo result === > " + result.toString());
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
            public void onFailure(Call<MineUserResult> call, Throwable t) {
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IMineCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IMineCallback callback) {
        this.mViewCallback = null;
    }
}
