package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.LoginResult;

public interface ILoginCallback extends IBaseCallback {

    void onLoadedLogin(LoginResult result);
}
