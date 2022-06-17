package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.presenter.IUserInfoPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.IUserInfoCallback;
import com.google.gson.Gson;

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

public class UserInfoPresenter implements IUserInfoPresenter {

    private IUserInfoCallback mMineCallback = null;

    private final Api api;

    public UserInfoPresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getUserInfo() {
        if (mMineCallback != null) {
            mMineCallback.onLoading();
        }

        api.getUserInfo(SpUtils.getToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(UserInfoPresenter.this, "getUserInfo: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            User user = new Gson().fromJson(response.body().string(), User.class);
                            LogUtils.d(UserInfoPresenter.this, "getUserInfo: user -> " + user.toString());

                            if (user.getCode() == 200) {
                                if (mMineCallback != null) {
                                    mMineCallback.onUserInfoLoadedSuccess(user.getUser());
                                }
                            } else {
                                if (mMineCallback != null) {
                                    mMineCallback.onUserInfoLoadedFailure(user.getMsg());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mMineCallback != null) {
                                mMineCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mMineCallback != null) {
                    mMineCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void updateInfo(User.UserBean user) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("avatar", user.getAvatar());
            jsonObject.put("nickName", user.getNickName());
            jsonObject.put("phonenumber", user.getPhonenumber());
            jsonObject.put("sex", user.getSex());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("idCard", user.getIdCard());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonStr = jsonObject.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        api.updateUserInfo(SpUtils.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(UserInfoPresenter.this, "updateInfo: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(UserInfoPresenter.this, "updateInfo: result -> " + result);
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            String resultMsg = jsonObject1.getString("msg");
                            if (resultCode == 200) {
                                if (mMineCallback != null) {
                                    mMineCallback.onUpdateUserInfoSuccess(resultMsg);
                                }
                            } else {
                                if (mMineCallback != null) {
                                    mMineCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mMineCallback != null) {
                                mMineCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mMineCallback != null) {
                    mMineCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUserInfoCallback callback) {
        this.mMineCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mMineCallback = null;
    }
}
