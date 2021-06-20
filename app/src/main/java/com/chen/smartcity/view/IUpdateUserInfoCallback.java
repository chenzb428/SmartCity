package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.AvatarResult;
import com.chen.smartcity.model.bean.Result;

public interface IUpdateUserInfoCallback extends IBaseCallback {

    void onUpdateUserInfoSuccess(Result result);

    void onUpdateUserAvatarSuccess(AvatarResult result);

    void onUpdateUserAvatarError(String result);
}
