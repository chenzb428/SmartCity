package com.chen.smartcity.utils;

import com.chen.smartcity.presenter.IHomeNewPagePresenter;
import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.presenter.IServerPresenter;
import com.chen.smartcity.presenter.impl.HomeNewPagePresenter;
import com.chen.smartcity.presenter.impl.HomePresenter;
import com.chen.smartcity.presenter.impl.ServerPresent;

public class PresenterManager {

    private static final PresenterManager outInstance = new PresenterManager();
    private final IHomePresenter mHomePresenter;
    private final IHomeNewPagePresenter mHomeNewPagePresenter;
    private final IServerPresenter mServerPresenter;

    public static PresenterManager getInstance() {
        return outInstance;
    }

    private PresenterManager() {
        mHomePresenter = new HomePresenter();
        mHomeNewPagePresenter = new HomeNewPagePresenter();
        mServerPresenter = new ServerPresent();
    }

    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }

    public IHomeNewPagePresenter getHomeNewPagePresenter() {
        return mHomeNewPagePresenter;
    }

    public IServerPresenter getServerPresenter() {
        return mServerPresenter;
    }
}
