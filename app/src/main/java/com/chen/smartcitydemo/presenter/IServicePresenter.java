package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.IServiceCallback;

public interface IServicePresenter extends IBasePresenter<IServiceCallback> {

    /**
     * 获取全部服务
     */
    void getAll();
}
