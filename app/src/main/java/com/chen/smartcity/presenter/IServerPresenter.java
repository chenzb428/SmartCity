package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IServerCallback;

public interface IServerPresenter extends IBasePresenter<IServerCallback> {

    /**
     * 获取全部服务
     */
    void getServer();
}
