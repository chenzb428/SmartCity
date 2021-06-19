package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IUserInfoCallback;

public interface IUserInfoPresenter extends IBasePresenter<IUserInfoCallback> {

    void getUserInfo(String token);
}
