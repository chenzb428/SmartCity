package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.ILoginCallback;

public interface ILoginPresenter extends IBasePresenter<ILoginCallback> {

    void doLogin(String account, String password);
}
