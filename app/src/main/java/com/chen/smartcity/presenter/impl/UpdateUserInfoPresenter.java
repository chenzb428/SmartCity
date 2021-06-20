package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.UserInfoParams;
import com.chen.smartcity.model.bean.AvatarResult;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IUpdateUserInfoPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.IUpdateUserInfoCallback;

import java.io.File;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateUserInfoPresenter implements IUpdateUserInfoPresenter {

    private IUpdateUserInfoCallback mViewCallback = null;
    private final Api mApi;

    public UpdateUserInfoPresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public void updateUserInfo(String token, String nickname, String email, String phone, String idCard, String sex) {

        UserInfoParams params = new UserInfoParams(null, nickname, null, email, phone, idCard, sex);
        Call<Result> task = mApi.updateUserInfo(token, params);
        task.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                int code = response.code();
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Result result = response.body();
                    LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onUpdateUserInfoSuccess(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserInfo error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void updateUserAvatar(String token, String picPath) {
        LogUtils.d(UpdateUserInfoPresenter.this, "updateUserAvatar picPath === > " + picPath);
        File file = new File(picPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Call<AvatarResult> task = mApi.uploadUserAvatar(token, body);
        task.enqueue(new Callback<AvatarResult>() {

            @Override
            public void onResponse(Call<AvatarResult> call, Response<AvatarResult> response) {
                int code = response.code();
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserAvatar code === > " + code);
                if (code == HttpsURLConnection.HTTP_OK) {
                    AvatarResult mResult = response.body();
                    LogUtils.d(UpdateUserInfoPresenter.this, "updateUserAvatar result === > " + mResult.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onUpdateUserAvatarSuccess(mResult);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onUpdateUserAvatarError("error! 上传失败！");
                    }
                }
            }

            @Override
            public void onFailure(Call<AvatarResult> call, Throwable t) {
                LogUtils.d(UpdateUserInfoPresenter.this, "updateUserAvatar error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onUpdateUserAvatarError(t.toString());
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IUpdateUserInfoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IUpdateUserInfoCallback callback) {
        this.mViewCallback = null;
    }
}
