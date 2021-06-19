package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IServerPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IServerCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServerPresent implements IServerPresenter {

    private IServerCallback mViewCallback = null;

    @Override
    public void getServer() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<ServerResult> task = api.getServerResult();
        task.enqueue(new Callback<ServerResult>() {
            @Override
            public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                int code = response.code();
                LogUtils.d(ServerPresent.this, "getServer code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    ServerResult result = response.body();
                    LogUtils.d(ServerPresent.this, "getServer result === > " + result.toString());
                    if (mViewCallback != null) {
                        if (result == null || result.getRows().size() == 0) {
                            mViewCallback.onEmpty();
                        } else {
                            mViewCallback.onLoadedServer(result.getRows());
                        }
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResult> call, Throwable t) {
                LogUtils.d(ServerPresent.this, "getServer error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IServerCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IServerCallback callback) {
        this.mViewCallback = null;
    }
}
