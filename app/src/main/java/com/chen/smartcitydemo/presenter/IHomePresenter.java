package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.IHomeCallback;

public interface IHomePresenter extends IBasePresenter<IHomeCallback> {

    void getBannerData();

    void getHotNewsData();
}
