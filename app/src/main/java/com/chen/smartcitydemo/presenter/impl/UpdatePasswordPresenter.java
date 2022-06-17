package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.presenter.IUpdatePasswordPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.IUpdatePasswordCallback;

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

public class UpdatePasswordPresenter implements IUpdatePasswordPresenter {

    private IUpdatePasswordCallback mUpdatePasswordCallback = null;

    @Override
    public void updatePassword(String oldPsw, String newPsw) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("oldPassword", oldPsw);
            jsonObject.put("newPassword", newPsw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);

        api.updatePassword(SpUtils.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(UpdatePasswordPresenter.this, "updatePassword: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject = new JSONObject(result);
                            int resultCode = jsonObject.getInt("code");
                            LogUtils.d(UpdatePasswordPresenter.this, "updatePassword: resultCode -> " + resultCode);
                            String resultMsg = jsonObject.getString("msg");
                            LogUtils.d(UpdatePasswordPresenter.this, "updatePassword: resultMsg -> " + resultMsg);
                            if (resultCode == 200) {
                                if (mUpdatePasswordCallback != null) {
                                    mUpdatePasswordCallback.onUpdatePasswordSuccess(resultMsg);
                                }
                            } else {
                                if (mUpdatePasswordCallback != null) {
                                    mUpdatePasswordCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mUpdatePasswordCallback != null) {
                                mUpdatePasswordCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mUpdatePasswordCallback != null) {
                    mUpdatePasswordCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUpdatePasswordCallback callback) {
        this.mUpdatePasswordCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mUpdatePasswordCallback = null;
    }
}
