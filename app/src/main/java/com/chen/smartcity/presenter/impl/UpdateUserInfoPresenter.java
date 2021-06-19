package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.UpdateUserInfoParams;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IUpdateUserInfoPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IUpdateUserInfoCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateUserInfoPresenter implements IUpdateUserInfoPresenter {

    private IUpdateUserInfoCallback mViewCallback = null;

    @Override
    public void updateUserInfo(String token, String nickname, String email, String phone, String idCard, String sex) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        UpdateUserInfoParams params = new UpdateUserInfoParams(nickname, email, phone, idCard, sex);
        Call<Result> task = api.updateUserInfo(token, params);
        task.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                int code = response.code();
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Result result = response.body();
                    LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onUpdateUserInfoSuccess(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUpdateUserInfoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IUpdateUserInfoCallback callback) {
        this.mViewCallback = null;
    }
}
