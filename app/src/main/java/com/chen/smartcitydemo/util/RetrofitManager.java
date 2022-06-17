package com.chen.smartcitydemo.util;

import retrofit2.Retrofit;

public class RetrofitManager {

    private static final RetrofitManager retrofitManager = new RetrofitManager();

    private final Retrofit retrofit;

    public static RetrofitManager getInstance() {
        return retrofitManager;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SpUtils.getHost(Constants.BASE_URL)) // 设置服务器地址
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
