package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.MeetParams;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IMeetPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IMeetCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MeetPresenter implements IMeetPresenter {

    private IMeetCallback mViewCallback = null;

    @Override
    public void doMeet(String token, String title, String content) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        MeetParams params = new MeetParams(title, content);
        Call<Result> task = api.doMeet(token, params);
        task.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                int code = response.code();
                LogUtils.d(MeetPresenter.this, "doMeet code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Result result = response.body();
                    LogUtils.d(MeetPresenter.this, "doMeet result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedMeet(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IMeetCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IMeetCallback callback) {
        this.mViewCallback = null;
    }
}
