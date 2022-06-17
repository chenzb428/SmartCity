package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.IUpdatePasswordCallback;

public interface IUpdatePasswordPresenter extends IBasePresenter<IUpdatePasswordCallback> {

    void updatePassword(String oldPsw, String newPsw);
}
