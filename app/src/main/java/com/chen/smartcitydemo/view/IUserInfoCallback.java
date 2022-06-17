package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;
import com.chen.smartcitydemo.model.bean.User;

public interface IUserInfoCallback extends IBaseCallback {

    void onUserInfoLoadedSuccess(User.UserBean user);

    void onUserInfoLoadedFailure(String msg);

    void onUpdateUserInfoSuccess(String msg);
}
