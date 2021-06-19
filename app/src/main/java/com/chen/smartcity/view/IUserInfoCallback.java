package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.UserInfoResult;

public interface IUserInfoCallback extends IBaseCallback {

    void onLoadedUserInfo(UserInfoResult result);
}
