package com.chen.smartcity.model;

import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.LoginResult;
import com.chen.smartcity.model.bean.MeetParams;
import com.chen.smartcity.model.bean.MineUserResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.model.bean.ServerResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {

    @GET("prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2")
    Call<HomeBannerResult> getHomeBannerResult();

    @GET("prod-api/api/service/list")
    Call<ServerResult> getServerResult();

    @GET("prod-api/press/category/list")
    Call<NewCategory> getNewCategory();

    @GET
    Call<NewList> getNewList(@Url String url);  //prod-api/press/press/list

    @GET("prod-api/press/press/list")
    Call<NewList> getAllNews();

    @POST("prod-api/api/login")
    Call<LoginResult> doLogin(@Body LoginParams params);

    @GET("prod-api/api/common/user/getInfo")
    Call<MineUserResult> getUserInfo(@Header("Authorization") String header);

    @POST("prod-api/api/common/feedback")
    Call<Result> doMeet(@Header("Authorization") String header, @Body MeetParams params);
}
