package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.presenter.IUploadFilePresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.IUploadFileCallback;

import org.json.JSONObject;

import java.io.File;
import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadFilePresenter implements IUploadFilePresenter {

    private IUploadFileCallback mUploadFileCallback = null;

    @Override
    public void uploadFile(String path) {
        if (mUploadFileCallback != null) {
            mUploadFileCallback.onLoading();
        }

        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);

        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);

        api.uploadFile(SpUtils.getToken(), part).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(UploadFilePresenter.this, "uploadFile: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject = new JSONObject(result);
                            int resultCode = jsonObject.getInt("code");
                            LogUtils.d(UploadFilePresenter.this, "uploadFile: resultCode -> " + resultCode);
                            String resultMsg = jsonObject.getString("msg");
                            LogUtils.d(UploadFilePresenter.this, "uploadFile: resultMsg -> " + resultMsg);
                            if (resultCode == 200) {
                                String url = jsonObject.getString("url");
                                LogUtils.d(UploadFilePresenter.this, "uploadFile: url -> " + url);
                                if (mUploadFileCallback != null) {
                                    mUploadFileCallback.onUploadFileSuccess(url);
                                }
                            } else {
                                if (mUploadFileCallback != null) {
                                    mUploadFileCallback.onError(resultMsg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mUploadFileCallback != null) {
                                mUploadFileCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mUploadFileCallback != null) {
                    mUploadFileCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUploadFileCallback callback) {
        this.mUploadFileCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mUploadFileCallback = null;
    }
}
