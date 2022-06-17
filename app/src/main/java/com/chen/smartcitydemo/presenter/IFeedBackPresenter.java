package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.IFeedBackCallback;

public interface IFeedBackPresenter extends IBasePresenter<IFeedBackCallback> {

    void doFeetBack(String title, String content);

    void getFeedBack();
}
