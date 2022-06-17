package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.model.bean.NewsCategory;
import com.chen.smartcitydemo.presenter.INewsPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.view.INewsCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsPresenter implements INewsPresenter {

    private INewsCallback mNewsCallback = null;

    private final Api api;

    public NewsPresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getCategoryData() {
        if (mNewsCallback != null) {
            mNewsCallback.onLoading();
        }

        api.getNewsCategory().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsPresenter.this, "getCategoryData: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            NewsCategory result = new Gson().fromJson(response.body().string(), NewsCategory.class);
                            LogUtils.d(NewsPresenter.this, "getCategoryData: result -> " + result.toString());

                            if (mNewsCallback != null) {
                                mNewsCallback.onLoadedNewsCategorySuccess(result.getData());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mNewsCallback != null) {
                                mNewsCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsCallback != null) {
                    mNewsCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void getCategoryContentData(int id) {

        api.getNewsCategoryContent(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsPresenter.this, "getCategoryContentData: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            News result = new Gson().fromJson(response.body().string(), News.class);
                            LogUtils.d(NewsPresenter.this, "getCategoryContentData: result -> " + result.toString());

                            if (mNewsCallback != null) {
                                mNewsCallback.onLoadedNewsCategoryContentSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mNewsCallback != null) {
                                mNewsCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsCallback != null) {
                    mNewsCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void getAllData() {
        if (mNewsCallback != null) {
            mNewsCallback.onLoading();
        }

        api.getAllNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsPresenter.this, "getAllData: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            News result = new Gson().fromJson(response.body().string(), News.class);
                            LogUtils.d(NewsPresenter.this, "getAllData: result -> " + result.toString());

                            if (mNewsCallback != null) {
                                mNewsCallback.onLoadedAllNewsSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mNewsCallback != null) {
                                mNewsCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsCallback != null) {
                    mNewsCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(INewsCallback callback) {
        this.mNewsCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mNewsCallback = null;
    }
}
