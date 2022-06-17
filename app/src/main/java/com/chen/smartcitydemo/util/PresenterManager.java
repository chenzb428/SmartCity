package com.chen.smartcitydemo.util;

import com.chen.smartcitydemo.presenter.IFeedBackPresenter;
import com.chen.smartcitydemo.presenter.ILoginOutPresenter;
import com.chen.smartcitydemo.presenter.ILoginPresenter;
import com.chen.smartcitydemo.presenter.INewsInfoPresenter;
import com.chen.smartcitydemo.presenter.IUpdatePasswordPresenter;
import com.chen.smartcitydemo.presenter.IUserInfoPresenter;
import com.chen.smartcitydemo.presenter.INewsPresenter;
import com.chen.smartcitydemo.presenter.IRegisterPresenter;
import com.chen.smartcitydemo.presenter.IServicePresenter;
import com.chen.smartcitydemo.presenter.IHomePresenter;
import com.chen.smartcitydemo.presenter.IUploadFilePresenter;
import com.chen.smartcitydemo.presenter.impl.FeedBackPresenter;
import com.chen.smartcitydemo.presenter.impl.LoginOutPresenter;
import com.chen.smartcitydemo.presenter.impl.LoginPresenter;
import com.chen.smartcitydemo.presenter.impl.NewsInfoPresenter;
import com.chen.smartcitydemo.presenter.impl.UpdatePasswordPresenter;
import com.chen.smartcitydemo.presenter.impl.UserInfoPresenter;
import com.chen.smartcitydemo.presenter.impl.NewsPresenter;
import com.chen.smartcitydemo.presenter.impl.RegisterPresenter;
import com.chen.smartcitydemo.presenter.impl.ServicePresenter;
import com.chen.smartcitydemo.presenter.impl.HomePresenter;
import com.chen.smartcitydemo.presenter.impl.UploadFilePresenter;

public class PresenterManager {

    private static PresenterManager presenterManager;

    private final IServicePresenter servicePresenter;
    private final IHomePresenter homePresenter;
    private final INewsPresenter newsPresenter;
    private final IUserInfoPresenter userInfoPresenter;
    private final ILoginPresenter loginPresenter;
    private final IRegisterPresenter registerPresenter;
    private final ILoginOutPresenter loginOutPresenter;
    private final IUploadFilePresenter uploadFilePresenter;
    private final IUpdatePasswordPresenter updatePasswordPresenter;
    private final IFeedBackPresenter feetBackPresenter;
    private final INewsInfoPresenter newsInfoPresenter;

    public static PresenterManager getInstance() {
        if (presenterManager == null) {
            presenterManager = new PresenterManager();
        }
        return presenterManager;
    }

    private PresenterManager() {
        servicePresenter = new ServicePresenter();
        homePresenter = new HomePresenter();
        newsPresenter = new NewsPresenter();
        userInfoPresenter = new UserInfoPresenter();
        loginPresenter = new LoginPresenter();
        registerPresenter = new RegisterPresenter();
        loginOutPresenter = new LoginOutPresenter();
        uploadFilePresenter = new UploadFilePresenter();
        updatePasswordPresenter = new UpdatePasswordPresenter();
        feetBackPresenter = new FeedBackPresenter();
        newsInfoPresenter = new NewsInfoPresenter();
    }

    public IHomePresenter getHomePresenter() {
        return homePresenter;
    }

    public IServicePresenter getServicePresenter() {
        return servicePresenter;
    }

    public INewsPresenter getNewsPresenter() {
        return newsPresenter;
    }

    public IUserInfoPresenter getUserInfoPresenter() {
        return userInfoPresenter;
    }

    public ILoginPresenter getLoginPresenter() {
        return loginPresenter;
    }

    public IRegisterPresenter getRegisterPresenter() {
        return registerPresenter;
    }

    public ILoginOutPresenter getLoginOutPresenter() {
        return loginOutPresenter;
    }

    public IUploadFilePresenter getUploadFilePresenter() {
        return uploadFilePresenter;
    }

    public IUpdatePasswordPresenter getUpdatePasswordPresenter() {
        return updatePasswordPresenter;
    }

    public IFeedBackPresenter getFeetBackPresenter() {
        return feetBackPresenter;
    }

    public INewsInfoPresenter getNewsInfoPresenter() {
        return newsInfoPresenter;
    }
}
