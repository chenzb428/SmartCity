package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IUpdateUserInfoCallback;

public interface IUpdateUserInfoPresenter extends IBasePresenter<IUpdateUserInfoCallback> {

    void updateUserInfo(String token, String nickname, String email, String phone, String idCard, String sex);

    void updateUserAvatar(String token, String picPath);
}
