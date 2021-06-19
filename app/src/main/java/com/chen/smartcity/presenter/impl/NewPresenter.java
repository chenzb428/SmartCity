package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.presenter.INewPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.INewCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewPresenter implements INewPresenter {

    private INewCallback mViewCallback = null;

    @Override
    public void getNewCategory() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<NewCategory> task = api.getNewCategory();
        task.enqueue(new Callback<NewCategory>() {
            @Override
            public void onResponse(Call<NewCategory> call, Response<NewCategory> response) {
                int code = response.code();
                LogUtils.d(NewPresenter.this, "getNewCategory code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewCategory result = response.body();
                    LogUtils.d(NewPresenter.this, "getNewCategory result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedNewCategory(result.getData());
                    }
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<NewCategory> call, Throwable t) {

            }
        });
    }

    @Override
    public void registerViewCallback(INewCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(INewCallback callback) {
        this.mViewCallback = null;
    }
}
