package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.Service;
import com.chen.smartcitydemo.presenter.IServicePresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.view.IServiceCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServicePresenter implements IServicePresenter {

    private IServiceCallback mAllServiceCallback = null;

    private final Api mApi;

    private List<Service.RowsBean> services = new ArrayList<>();

    public ServicePresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public void getAll() {
        // 开始获取全部服务分类，通知视图层展示加载中界面
        if (mAllServiceCallback != null) {
            mAllServiceCallback.onLoading();
        }

        Call<ResponseBody> allServiceCategory = mApi.getServiceCategory();
        allServiceCategory.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 判断状态码
                int code = response.code();
                LogUtils.d(ServicePresenter.this, "getAllServiceCategory: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    // 状态码为200，则成功
                    if (response.body() != null) {
                        try {
                            // 通过 Gson 转换数据
                            Service result = new Gson().fromJson(response.body().string(), Service.class);
                            LogUtils.d(ServicePresenter.this, "getAllServiceCategory: result -> " + result.toString());

                            if (mAllServiceCallback != null) {
                                if (result != null && result.getRows().size() == 0) {
                                    // 数据为空，通知视图层展示空视图界面
                                    mAllServiceCallback.onEmpty();
                                    return;
                                }

                                services.addAll(result.getRows());

                                // 回调数据给视图层
                                mAllServiceCallback.onLoadedAllServiceSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mAllServiceCallback != null) {
                                mAllServiceCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mAllServiceCallback != null) {
                    mAllServiceCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IServiceCallback callback) {
        this.mAllServiceCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mAllServiceCallback = null;
    }
}
