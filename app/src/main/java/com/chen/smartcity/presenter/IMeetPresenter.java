package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IMeetCallback;

public interface IMeetPresenter extends IBasePresenter<IMeetCallback> {

    void doMeet(String token, String title, String content);
}
