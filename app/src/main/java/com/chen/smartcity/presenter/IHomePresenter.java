package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {

    /**
     * 获取主页轮播图
     */
    void getBanner();

    /**
     * 获取主页服务分类
     */
    void getServer();

    /**
     * 获取主页热门主题
     */
    void getRecommend();

    /**
     * 获取主页新闻分类
     */
    void getNewCategory();
}
