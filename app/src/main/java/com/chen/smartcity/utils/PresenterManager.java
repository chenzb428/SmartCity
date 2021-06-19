package com.chen.smartcity.utils;

import com.chen.smartcity.presenter.IAllNewsPresenter;
import com.chen.smartcity.presenter.IHomeNewPagePresenter;
import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.presenter.ILoginPresenter;
import com.chen.smartcity.presenter.INewPresenter;
import com.chen.smartcity.presenter.IServerPresenter;
import com.chen.smartcity.presenter.impl.AllNewsPresenter;
import com.chen.smartcity.presenter.impl.HomeNewPagePresenter;
import com.chen.smartcity.presenter.impl.HomePresenter;
import com.chen.smartcity.presenter.impl.LoginPresenter;
import com.chen.smartcity.presenter.impl.NewPresenter;
import com.chen.smartcity.presenter.impl.ServerPresent;

public class PresenterManager {

    private static final PresenterManager outInstance = new PresenterManager();
    private final IHomePresenter mHomePresenter;
    private final IHomeNewPagePresenter mHomeNewPagePresenter;
    private final IServerPresenter mServerPresenter;
    private final IAllNewsPresenter mAllNewsPresenter;
    private final INewPresenter mNewPresenter;
    private final ILoginPresenter mLoginPresenter;

    public static PresenterManager getInstance() {
        return outInstance;
    }

    private PresenterManager() {
        mHomePresenter = new HomePresenter();
        mHomeNewPagePresenter = new HomeNewPagePresenter();
        mServerPresenter = new ServerPresent();
        mAllNewsPresenter = new AllNewsPresenter();
        mNewPresenter = new NewPresenter();
        mLoginPresenter = new LoginPresenter();
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

    public IAllNewsPresenter getAllNewsPresenter() {
        return mAllNewsPresenter;
    }

    public INewPresenter getNewPresenter() {
        return mNewPresenter;
    }

    public ILoginPresenter getLoginPresenter() {
        return mLoginPresenter;
    }
}
