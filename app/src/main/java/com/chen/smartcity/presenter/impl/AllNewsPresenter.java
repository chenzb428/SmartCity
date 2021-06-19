package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.presenter.IAllNewsPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IAllNewsCallback;

import java.net.HttpURLConnection;
import java.net.IDN;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllNewsPresenter implements IAllNewsPresenter {

    private IAllNewsCallback mViewCallback = null;

    @Override
    public void getAllNews() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<NewList> task = api.getAllNews();
        task.enqueue(new Callback<NewList>() {
            @Override
            public void onResponse(Call<NewList> call, Response<NewList> response) {
                int code = response.code();
                LogUtils.d(AllNewsPresenter.this, "getAllNews code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewList result = response.body();
                    LogUtils.d(AllNewsPresenter.this, "getAllNews result === > " + result.toString());
                    if (mViewCallback != null) {
                        if (result == null && result.getRows().size() == 0) {
                            mViewCallback.onEmpty();
                        } else {
                            mViewCallback.onLoadedAllNews(result.getRows());
                        }
                    } else {
                        if (mViewCallback != null) {
                            mViewCallback.onError();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewList> call, Throwable t) {
                LogUtils.d(AllNewsPresenter.this, "getAllNews error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IAllNewsCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IAllNewsCallback callback) {
        this.mViewCallback = null;
    }
}
