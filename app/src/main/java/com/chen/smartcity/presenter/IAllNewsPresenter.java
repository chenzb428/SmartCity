package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IAllNewsCallback;

public interface IAllNewsPresenter extends IBasePresenter<IAllNewsCallback> {

    /**
     * 获取所以新闻
     */
    void getAllNews();
}
