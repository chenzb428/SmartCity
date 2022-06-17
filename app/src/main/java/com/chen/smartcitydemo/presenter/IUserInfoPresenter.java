package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.view.IUserInfoCallback;

public interface IUserInfoPresenter extends IBasePresenter<IUserInfoCallback> {

    void getUserInfo();

    void updateInfo(User.UserBean user);
}
