package com.chen.smartcitydemo.base;

public interface IBasePresenter<T> {

    /**
     * 注册UI通知接口
     * @param callback 视图层回调接口
     */
    void registerViewCallback(T callback);

    /**
     * 取消注册UI通知接口
     */
    void unregisterViewCallback();
}
