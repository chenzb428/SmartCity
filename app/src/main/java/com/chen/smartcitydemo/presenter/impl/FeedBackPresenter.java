package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.presenter.IFeedBackPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.IFeedBackCallback;

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

public class FeedBackPresenter implements IFeedBackPresenter {

    private IFeedBackCallback mFeetBackCallback = null;

    @Override
    public void doFeetBack(String title, String content) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        api.doFeedBack(SpUtils.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(FeedBackPresenter.this, "feetBack: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(FeedBackPresenter.this, "feetBack: result -> " + result);
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            String resultMsg = jsonObject1.getString("msg");
                            if (resultCode == 200) {
                                if (mFeetBackCallback != null) {
                                    mFeetBackCallback.onFeetBackSuccess(resultMsg);
                                }
                            } else {
                                if (mFeetBackCallback != null) {
                                    mFeetBackCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mFeetBackCallback != null) {
                                mFeetBackCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mFeetBackCallback != null) {
                    mFeetBackCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void getFeedBack() {

    }

    @Override
    public void registerViewCallback(IFeedBackCallback callback) {
        this.mFeetBackCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mFeetBackCallback = null;
    }
}
