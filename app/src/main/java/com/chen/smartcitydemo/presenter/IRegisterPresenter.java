package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.view.IRegisterCallback;

public interface IRegisterPresenter extends IBasePresenter<IRegisterCallback> {

    void doRegister(User.UserBean user);
}
