package com.chen.smartcity.model;

import com.chen.smartcity.model.bean.AvatarResult;
import com.chen.smartcity.model.bean.DingdanResult;
import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.LoginResult;
import com.chen.smartcity.model.bean.NewCommentsResult;
import com.chen.smartcity.model.bean.NewResult;
import com.chen.smartcity.model.bean.UserInfoResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.model.bean.ServerResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
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
    Call<UserInfoResult> getUserInfo(@Header("Authorization") String header);

    @POST("prod-api/api/common/feedback")
    Call<Result> doMeet(@Header("Authorization") String header, @Body MeetParams params);

    @GET("prod-api/api/allorder/list")
    Call<DingdanResult> getDingdanInfo(@Header("Authorization") String header);

    @PUT("prod-api/api/common/user")
    Call<Result> updateUserInfo(@Header("Authorization") String header, @Body UpdateUserInfoParams params);

    @Multipart
    @POST("common/upload")
    Call<AvatarResult> uploadUserAvatar(@Header("Authorization") String header, @Part MultipartBody.Part file);

    @GET
    Call<NewResult> getNewInfo(@Url String url); //prod-api/press/press/{id}

    @GET("prod-api/press/comments/list")
    Call<NewCommentsResult> getNewComments(@Query("newsId") int id);

    @POST("prod-api/press/pressComment")
    Call<Result> doComment(@Header("Authorization") String header, @Body NewCommentParams params);
}
