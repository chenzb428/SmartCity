package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.Result;

public interface IMeetCallback extends IBaseCallback {

    void onLoadedMeet(Result result);
}
