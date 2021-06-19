package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.IHomeNewPageCallback;

public interface IHomeNewPagePresenter extends IBasePresenter<IHomeNewPageCallback> {

    /**
     * 根据分类id去加载对应新闻内容
     * @param id
     */
    void getNewPageContent(int id);

    /**
     * 重新加载
     * @param id
     */
    void reload(int id);
}
