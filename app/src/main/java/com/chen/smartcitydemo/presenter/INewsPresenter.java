package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.INewsCallback;

public interface INewsPresenter extends IBasePresenter<INewsCallback> {

    void getCategoryData();

    void getCategoryContentData(int id);

    void getAllData();
}
