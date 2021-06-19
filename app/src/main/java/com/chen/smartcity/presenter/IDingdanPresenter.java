package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IDingdanCallback;

public interface IDingdanPresenter extends IBasePresenter<IDingdanCallback> {

    void getDingdanInfo(String token);
}
