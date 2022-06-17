package com.chen.smartcity.presenter.impl;

import com.chen.smartcity.model.Api;
import com.chen.smartcity.model.NewCommentParams;
import com.chen.smartcity.model.bean.NewCommentsResult;
import com.chen.smartcity.model.bean.NewResult;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.INewInfoPresenter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.RetrofitManager;
import com.chen.smartcity.view.INewInfoCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewInfoPresenter implements INewInfoPresenter {

    private INewInfoCallback mViewCallback = null;
    private final Api mApi;

    public NewInfoPresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public void getNewInfo(int id) {
        Call<NewResult> task = mApi.getNewInfo("prod-api/press/press/" + id);
        task.enqueue(new Callback<NewResult>() {
            @Override
            public void onResponse(Call<NewResult> call, Response<NewResult> response) {
                int code = response.code();
                LogUtils.d(NewInfoPresenter.this, "getNewInfo code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewResult result = response.body();
                    LogUtils.d(NewInfoPresenter.this, "getNewInfo result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedNewInfo(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewResult> call, Throwable t) {
                LogUtils.d(NewInfoPresenter.this, "getNewInfo error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onError();
                }
            }
        });
    }

    @Override
    public void getNewComments(int id) {
        if (mViewCallback != null) {
            mViewCallback.onLoadingNewComments();
        }
        Call<NewCommentsResult> task = mApi.getNewComments(id);
        task.enqueue(new Callback<NewCommentsResult>() {
            @Override
            public void onResponse(Call<NewCommentsResult> call, Response<NewCommentsResult> response) {
                int code = response.code();
                LogUtils.d(NewInfoPresenter.this, "getNewComments code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    NewCommentsResult result = response.body();
                    LogUtils.d(NewInfoPresenter.this, "getNewComments result === > " + result.toString());
                    if (mViewCallback != null) {
                        if (result == null || result.getRows() == null || result.getRows().size() == 0) {
                            mViewCallback.onLoadedNewCommentsEmpty();
                        } else {
                            mViewCallback.onLoadedNewComments(result.getRows());
                        }
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onLoadedNewCommentsError();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewCommentsResult> call, Throwable t) {
                LogUtils.d(NewInfoPresenter.this, "getNewComments error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onLoadedNewCommentsError();
                }
            }
        });
    }

    @Override
    public void doComment(String token, int newId, String content) {
        NewCommentParams params = new NewCommentParams(newId, content);
        Call<Result> task = mApi.doComment(token, params);
        task.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                int code = response.code();
                LogUtils.d(NewInfoPresenter.this, "doComment code === > " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Result result = response.body();
                    LogUtils.d(NewInfoPresenter.this, "doComment result === > " + result.toString());
                    if (mViewCallback != null) {
                        mViewCallback.onDoComment(result);
                    }
                } else {
                    if (mViewCallback != null) {
                        mViewCallback.onDoCommentError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                LogUtils.d(NewInfoPresenter.this, "doComment error === > " + t.toString());
                if (mViewCallback != null) {
                    mViewCallback.onDoCommentError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(INewInfoCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(INewInfoCallback callback) {
        this.mViewCallback = null;
    }
}
