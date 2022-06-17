package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.Banner;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.presenter.IHomePresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.view.IHomeCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenter implements IHomePresenter {

    private IHomeCallback mHomeCallback = null;

    private final Api api;

    public HomePresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getBannerData() {
        if (mHomeCallback != null) {
            mHomeCallback.onLoading();
        }

        api.getHomeBanner().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getBannerData: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            Banner result = new Gson().fromJson(response.body().string(), Banner.class);
                            LogUtils.d(HomePresenter.this, "getBannerData: result -> " + result.toString());

                            if (mHomeCallback != null) {
                                mHomeCallback.onLoadedBannerSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mHomeCallback != null) {
                                mHomeCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mHomeCallback != null) {
                    mHomeCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void getHotNewsData() {
        if (mHomeCallback != null) {
            mHomeCallback.onLoading();
        }

        api.getHomeHotNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getHotNewsData: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            News result = new Gson().fromJson(response.body().string(), News.class);
                            LogUtils.d(HomePresenter.this, "getBannerData: result -> " + result.toString());

                            if (mHomeCallback != null) {
                                mHomeCallback.onLoadedHotNewsSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mHomeCallback != null) {
                                mHomeCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mHomeCallback != null) {
                    mHomeCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mHomeCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mHomeCallback = null;
    }
}
