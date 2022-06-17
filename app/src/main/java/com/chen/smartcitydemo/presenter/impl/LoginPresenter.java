package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.presenter.ILoginPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.ILoginCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter implements ILoginPresenter {

    private ILoginCallback mLoginCallback = null;

    @Override
    public void doLogin(String account, String password) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", account);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        api.doLogin(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(LoginPresenter.this, "doLogin: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(LoginPresenter.this, "doLogin: result -> " + result);
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            if (resultCode == 200) {
                                String token = jsonObject1.getString("token");
                                LogUtils.d(LoginPresenter.this, "doLogin: token -> " + token);
                                // 保存 token
                                SpUtils.setToken(token);
                                if (mLoginCallback != null) {
                                    mLoginCallback.onLoginSuccess();
                                }
                            } else {
                                String msg = jsonObject1.getString("msg");
                                if (mLoginCallback != null) {
                                    mLoginCallback.onError(msg);
                                }
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                            if (mLoginCallback != null) {
                                mLoginCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mLoginCallback != null) {
                    mLoginCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(ILoginCallback callback) {
        this.mLoginCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mLoginCallback = null;
    }
}
