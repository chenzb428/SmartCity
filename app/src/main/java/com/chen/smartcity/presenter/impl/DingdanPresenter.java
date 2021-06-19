package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.DingdanResult;
import com.chen.smartcity.presenter.IDingdanPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IDingdanCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DingdanPresenter implements IDingdanPresenter {

    private IDingdanCallback mViewCallback = null;

    @Override
    public void getDingdanInfo(String token) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<DingdanResult> task = api.getDingdanInfo(token);
        task.enqueue(new Callback<DingdanResult>() {
            @Override
            public void onResponse(Call<DingdanResult> call, Response<DingdanResult> response) {
                int code = response.code();
                LogUtils.d(DingdanPresenter.this, "getDingdanInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    DingdanResult result = response.body();
                    LogUtils.d(DingdanPresenter.this, "getDingdanInfo result === > " + result.toString());
                    if (mViewCallback != null) {
                        if (result == null || result.getRows().size() == 0) {
                            mViewCallback.onEmpty();
                        } else {
                            mViewCallback.onLoadedDingdanInfo(result.getRows());
                        }
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<DingdanResult> call, Throwable t) {
                LogUtils.d(DingdanPresenter.this, "getDingdanInfo error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IDingdanCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IDingdanCallback callback) {
        this.mViewCallback = null;
    }
}
