package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IRegisterCallback;

public interface IRegisterPresenter extends IBasePresenter<IRegisterCallback> {

    void doRegister(String account, String password, String email, String phone);
}
