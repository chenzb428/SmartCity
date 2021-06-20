package com.chen.smartcity.presenter;

import com.chen.smartcity.base.IBasePresenter;
import com.chen.smartcity.view.INewInfoCallback;

public interface INewInfoPresenter extends IBasePresenter<INewInfoCallback> {

    void getNewInfo(int newId);

    void getNewComments(int newId);

    void doComment(String token, int newId, String content);
}
