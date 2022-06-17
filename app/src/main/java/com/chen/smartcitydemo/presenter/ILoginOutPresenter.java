package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.ILoginOutCallback;

public interface ILoginOutPresenter extends IBasePresenter<ILoginOutCallback> {

    void loginOut();
}
