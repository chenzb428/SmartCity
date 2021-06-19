package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.INewCallback;

public interface INewPresenter extends IBasePresenter<INewCallback> {

    /**
     * 获取全部分类
     */
    void getNewCategory();
}
