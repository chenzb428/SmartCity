package com.chen.smartcitydemo.base;

public interface IBaseCallback {

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 数据为空
     */
    void onEmpty();

    /**
     * 出错
     */
    void onError(String msg);
}
