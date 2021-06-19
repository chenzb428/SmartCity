package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IMineCallback;

public interface IMinePresenter extends IBasePresenter<IMineCallback> {

    void getUserInfo(String token);
}
