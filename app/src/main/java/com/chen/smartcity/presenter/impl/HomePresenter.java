package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.utils.UrlUtils;
import com.chen.smartcity.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenter implements IHomePresenter {

    private final Api mApi;

    public HomePresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private IHomeCallback mViewCallback = null;

    @Override
    public void getBanner() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Call<HomeBannerResult> task = mApi.getHomeBannerResult();
        task.enqueue(new Callback<HomeBannerResult>() {
            @Override
            public void onResponse(Call<HomeBannerResult> call, Response<HomeBannerResult> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getBanner code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    HomeBannerResult result = response.body();
                    LogUtils.d(HomePresenter.this, "getBanner result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedBanner(result.getRows());
                    }
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<HomeBannerResult> call, Throwable throwable) {
                LogUtils.d(HomePresenter.this, "getBanner error === > " + throwable.toString());
            }
        });
    }

    @Override
    public void getServer() {
        Call<ServerResult> task = mApi.getServerResult();
        task.enqueue(new Callback<ServerResult>() {
            @Override
            public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getServer code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    ServerResult result = response.body();
                    LogUtils.d(HomePresenter.this, "getServer result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedServer(result.getRows().subList(0, 9));
                    }
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<ServerResult> call, Throwable t) {
                LogUtils.d(HomePresenter.this, "getServer error === > " + t.toString());
            }
        });
    }

    @Override
    public void getRecommend() {
        String targetUrl = UrlUtils.createHomeRecommendUrl(9);
        Call<NewList> task = mApi.getNewList(targetUrl);
        task.enqueue(new Callback<NewList>() {
            @Override
            public void onResponse(Call<NewList> call, Response<NewList> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getRecommend code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewList result = response.body();
                    LogUtils.d(HomePresenter.this, "getRecommend result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedRecommend(result.getRows().subList(0, 4));
                    }
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<NewList> call, Throwable t) {
                LogUtils.d(HomePresenter.this, "getRecommend error === > " + t.toString());
            }
        });
    }

    @Override
    public void getNewCategory() {
        Call<NewCategory> task = mApi.getNewCategory();
        task.enqueue(new Callback<NewCategory>() {
            @Override
            public void onResponse(Call<NewCategory> call, Response<NewCategory> response) {
                int code = response.code();
                LogUtils.d(HomePresenter.this, "getNewCategory code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewCategory result = response.body();
                    LogUtils.d(HomePresenter.this, "getNewCategory result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedNewCategory(result.getData());
                    }
                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<NewCategory> call, Throwable t) {
                LogUtils.d(HomePresenter.this, "getNewCategory error === > " + t.toString());
            }
        });
    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {
        this.mViewCallback = null;
    }
}
