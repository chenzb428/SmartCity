package com.chen.smartcitydemo.presenter.impl;

import com.chen.smartcitydemo.model.Api;
import com.chen.smartcitydemo.model.bean.Comment;
import com.chen.smartcitydemo.model.bean.NewsInfo;
import com.chen.smartcitydemo.presenter.INewsInfoPresenter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.RetrofitManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.INewsInfoCallback;
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

public class NewsInfoPresenter implements INewsInfoPresenter {

    private INewsInfoCallback mNewsInfoCallback = null;
    private final Api api;

    public NewsInfoPresenter() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        api = retrofit.create(Api.class);
    }

    @Override
    public void getNewsInfo(int id) {
        if (mNewsInfoCallback != null) {
            mNewsInfoCallback.onLoading();
        }

        api.getNewsInfo(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsInfoPresenter.this, "getNewsInfo: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        Gson gson = new Gson();
                        try {
                            NewsInfo newsInfo = gson.fromJson(response.body().string(), NewsInfo.class);
                            LogUtils.d(NewsInfoPresenter.this, "getNewsInfo: newsInfo -> " + newsInfo.toString());
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onLoadedNewsInfoSuccess(newsInfo.getData());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onError(e.toString());
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsInfoCallback != null) {
                    mNewsInfoCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void doNewsLike(int newsId) {
        api.doNewsLike(SpUtils.getToken(), newsId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsInfoPresenter.this, "doNewsLike: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(NewsInfoPresenter.this, "doNewsLike: result -> " + result);
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            String resultMsg = jsonObject1.getString("msg");
                            if (resultCode == 200) {
                                if (mNewsInfoCallback != null) {
                                    mNewsInfoCallback.onNewsLikeSuccess(resultMsg);
                                }
                            } else {
                                mNewsInfoCallback.onError(resultMsg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsInfoCallback != null) {
                    mNewsInfoCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void getCommentList(int newsId) {
        api.getNewsInfoComment(newsId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsInfoPresenter.this, "getCommentList: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        Gson gson = new Gson();
                        try {
                            Comment result = gson.fromJson(response.body().string(), Comment.class);
                            LogUtils.d(NewsInfoPresenter.this, "getCommentList: result -> " + result);
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onLoadedCommentSuccess(result.getRows());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsInfoCallback != null) {
                    mNewsInfoCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void doComment(int newsId, String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newsId", newsId);
            jsonObject.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonStr = jsonObject.toString();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        api.doNewsComment(SpUtils.getToken(), body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                LogUtils.d(NewsInfoPresenter.this, "doComment: code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        try {
                            String result = response.body().string();
                            LogUtils.d(NewsInfoPresenter.this, "doComment: result -> " + result);
                            JSONObject jsonObject1 = new JSONObject(result);
                            int resultCode = jsonObject1.getInt("code");
                            String resultMsg = jsonObject1.getString("msg");
                            if (resultCode == 200) {
                                if (mNewsInfoCallback != null) {
                                    mNewsInfoCallback.onNewsCommentSuccess(resultMsg);
                                }
                            } else {
                                mNewsInfoCallback.onError(resultMsg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mNewsInfoCallback != null) {
                                mNewsInfoCallback.onError(e.toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mNewsInfoCallback != null) {
                    mNewsInfoCallback.onError(throwable.toString());
                }
            }
        });
    }

    @Override
    public void doCommentLike(int commentId) {

    }

    @Override
    public void registerViewCallback(INewsInfoCallback callback) {
        this.mNewsInfoCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        this.mNewsInfoCallback = null;
    }
}
