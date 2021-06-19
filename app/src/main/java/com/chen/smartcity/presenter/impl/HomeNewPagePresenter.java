package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.presenter.IHomeNewPagePresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.utils.UrlUtils;
import com.chen.smartcity.view.IHomeNewPageCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeNewPagePresenter implements IHomeNewPagePresenter {

    private List<IHomeNewPageCallback> mViewCallbacks = new ArrayList<>();

    @Override
    public void getNewPageContent(int id) {
        for (IHomeNewPageCallback viewCallback : mViewCallbacks) {
            if (viewCallback.getCategoryId() == id) {
                viewCallback.onLoading();
            }
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String targetUrl = UrlUtils.createHomeRecommendUrl(id);
        Call<NewList> task = api.getNewList(targetUrl);
        task.enqueue(new Callback<NewList>() {
            @Override
            public void onResponse(Call<NewList> call, Response<NewList> response) {
                int code = response.code();
                LogUtils.d(HomeNewPagePresenter.this, "getNewPageContent code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewList result = response.body();
                    LogUtils.d(HomeNewPagePresenter.this, "getNewPageContent result === > " + result.toString());
                    for (IHomeNewPageCallback viewCallback : mViewCallbacks) {
                        if (result == null || result.getRows().size() == 0) {
                            viewCallback.onEmpty();
                        } else {
                            viewCallback.onLoadedNewPageContent(result.getRows());
                        }
                    }
                } else {
                    for (IHomeNewPageCallback viewCallback : mViewCallbacks) {
                        if (viewCallback.getCategoryId() == id) {
                            viewCallback.onError();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewList> call, Throwable t) {
                LogUtils.d(HomeNewPagePresenter.this, "getNewPageContent error === > " + t.toString());
                for (IHomeNewPageCallback viewCallback : mViewCallbacks) {
                    if (viewCallback.getCategoryId() == id) {
                        viewCallback.onError();
                    }
                }
            }
        });
    }

    @Override
    public void reload(int id) {
        this.getNewPageContent(id);
    }

    @Override
    public void registerViewCallback(IHomeNewPageCallback callback) {
        if (!mViewCallbacks.contains(callback)) {
            mViewCallbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(IHomeNewPageCallback callback) {
        mViewCallbacks.remove(callback);
    }
}
