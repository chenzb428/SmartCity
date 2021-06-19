package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.ILoginCallback;

public interface ILoginPresenter extends IBasePresenter<ILoginCallback> {

    void doLogin(String account, String password);
}
