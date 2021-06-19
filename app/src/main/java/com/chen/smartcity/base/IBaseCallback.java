package com.chen.smartcity.base;

public interface IBaseCallback {

    /**
     * 处理错误
     */
    void onError();

    /**
     * 处理加载中
     */
    void onLoading();

    /**
     * 处理内容为空
     */
    void onEmpty();
}
