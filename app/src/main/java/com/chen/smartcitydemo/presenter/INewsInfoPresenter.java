package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.INewsInfoCallback;

public interface INewsInfoPresenter extends IBasePresenter<INewsInfoCallback> {

    void getNewsInfo(int newsId);

    void doNewsLike(int newsId);

    void getCommentList(int newsId);

    void doComment(int newsId, String content);

    void doCommentLike(int commentId);
}
