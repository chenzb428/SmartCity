package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.presenter.IRegisterPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.view.IRegisterCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterPresenter implements IRegisterPresenter {

    private IRegisterCallback mRegisterCallback = null;

    @Override
    public void doRegister(User.UserBean user) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("nickName", user.getNickName());
            jsonObject.put("phonenumber", user.getPhonenumber());
            jsonObject.put("sex", user.getSex());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("idCard", user.getIdCard());
            jsonObject.put("avatar", user.getAvatar());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonStr = jsonObject.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        api.doRegister(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(RegisterPresenter.this, "doRegister: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            String resultMsg = jsonObject1.getString("msg");
                            if (resultCode == 200) {
                                if (mRegisterCallback != null) {
                                    mRegisterCallback.onRegisterSuccess();
                                }
                            } else {
                                if (mRegisterCallback != null) {
                                    mRegisterCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mRegisterCallback != null) {
                                mRegisterCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mRegisterCallback != null) {
                    mRegisterCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IRegisterCallback callback) {
        this.mRegisterCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mRegisterCallback = null;
    }
}
