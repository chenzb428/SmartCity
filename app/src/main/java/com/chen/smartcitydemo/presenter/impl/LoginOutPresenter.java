package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.presenter.ILoginOutPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.view.ILoginOutCallback;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginOutPresenter implements ILoginOutPresenter {

    private ILoginOutCallback mLoginOutCallback = null;

    @Override
    public void loginOut() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        api.doLoginOut().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoginOutCallback != null) {
                    mLoginOutCallback.onLoading();
                }

                int code = response.code();
                LogUtils.d(LoginOutPresenter.this, "loginOut: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(LoginOutPresenter.this, "loginOut: result -> " + result);
                            JSONObject jsonObject = new JSONObject(result);
                            int resultCode = jsonObject.getInt("code");
                            String resultMsg = jsonObject.getString("msg");

                            if (resultCode == 200) {
                                if (mLoginOutCallback != null) {
                                    mLoginOutCallback.onLoginOutSuccess(resultMsg);
                                }
                            } else {
                                if (mLoginOutCallback != null) {
                                    mLoginOutCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mLoginOutCallback != null) {
                                mLoginOutCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mLoginOutCallback != null) {
                    mLoginOutCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(ILoginOutCallback callback) {
        this.mLoginOutCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mLoginOutCallback = null;
    }
}
