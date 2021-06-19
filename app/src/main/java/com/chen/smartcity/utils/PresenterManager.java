package com.chen.smartcity.utils;

import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.presenter.impl.HomePresenter;

public class PresenterManager {

    private static final PresenterManager outInstance = new PresenterManager();
    private final IHomePresenter mHomePresenter;

    public static PresenterManager getInstance() {
        return outInstance;
    }

    private PresenterManager() {
        mHomePresenter = new HomePresenter();
    }

    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }
}
