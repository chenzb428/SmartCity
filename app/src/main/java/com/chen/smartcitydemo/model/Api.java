package com.chen.smartcitydemo.model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    /**
     * 主页轮播图
     */
    @GET("prod-api/api/rotation/list?type=2")
    Call<ResponseBody> getHomeBanner();

    /**
     * 主页热门主题
     */
    @GET("prod-api/press/press/list?hot=Y")
    Call<ResponseBody> getHomeHotNews();

    /**
     * 新闻分类
     */
    @GET("prod-api/press/category/list")
    Call<ResponseBody> getNewsCategory();

    /**
     * 该新闻分类下的全部新闻
     * @param id 新闻分类 id
     */
    @GET("prod-api/press/press/list")
    Call<ResponseBody> getNewsCategoryContent(@Query("type") int id);

    /**
     * 全部新闻
     */
    @GET("prod-api/press/press/list")
    Call<ResponseBody> getAllNews();

    /**
     * 获取新闻详情
     * @param newsId 新闻 id
     */
    @GET("prod-api/press/press/{id}")
    Call<ResponseBody> getNewsInfo(@Path("id") int newsId);

    /**
     * 获取新闻评论
     * @param newsId 新闻 id
     * @return
     */
    @GET("prod-api/press/comments/list")
    Call<ResponseBody> getNewsInfoComment(@Query("newsId") int newsId);

    /**
     * 发表新闻评论
     * @param token 认证信息
     */
    @POST("prod-api/press/pressComment")
    Call<ResponseBody> doNewsComment(@Header("Authorization") String token, @Body RequestBody commentBody);

    /**
     * 新闻点赞
     * @param token 认证信息
     * @param newsId 新闻 id
     */
    @PUT("prod-api/press/press/like/{id}")
    Call<ResponseBody> doNewsLike(@Header("Authorization") String token, @Path("id") int newsId);

    /**
     * 全部服务
     */
    @GET("prod-api/api/service/list")
    Call<ResponseBody> getServiceCategory();

    /**
     * 登录
     */
    @POST("prod-api/api/login")
    Call<ResponseBody> doLogin(@Body RequestBody loginBody);

    /**
     * 注册
     */
    @POST("prod-api/api/register")
    Call<ResponseBody> doRegister(@Body RequestBody registerBody);

    /**
     * 退出登录
     */
    @POST("prod-api/logout")
    Call<ResponseBody> doLoginOut();

    /**
     * 上传文件
     */
    @Multipart
    @POST("prod-api/common/upload")
    Call<ResponseBody> uploadFile(@Header("Authorization") String token, @Part MultipartBody.Part uploadBody);

    /**
     * 获取用户信息
     * @param token 认证信息
     */
    @GET("prod-api/api/common/user/getInfo")
    Call<ResponseBody> getUserInfo(@Header("Authorization") String token);

    /**
     * 更新用户信息
     * @param token 认证信息
     */
    @PUT("prod-api/api/common/user")
    Call<ResponseBody> updateUserInfo(@Header("Authorization") String token, @Body RequestBody updateUserInfoBody);

    /**
     * 修改密码
     * @param token 认证信息
     */
    @PUT("prod-api/api/common/user/resetPwd")
    Call<ResponseBody> updatePassword(@Header("Authorization") String token, @Body RequestBody updatePasswordBody);

    /**
     * 提交意见反馈
     * @param token 认证信息
     */
    @POST("prod-api/api/common/feedback")
    Call<ResponseBody> doFeedBack(@Header("Authorization") String token, @Body RequestBody feetBackBody);
}
