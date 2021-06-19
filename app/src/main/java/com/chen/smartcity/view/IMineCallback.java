package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.MineUserResult;

public interface IMineCallback extends IBaseCallback {

    void onLoadedUserInfo(MineUserResult result);
}
