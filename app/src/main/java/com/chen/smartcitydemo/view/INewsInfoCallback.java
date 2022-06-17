package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;
import com.chen.smartcitydemo.model.bean.Comment;
import com.chen.smartcitydemo.model.bean.NewsInfo;

import java.util.List;

public interface INewsInfoCallback extends IBaseCallback {

    void onLoadedNewsInfoSuccess(NewsInfo.DataBean newsInfo);

    void onNewsLikeSuccess(String msg);

    void onLoadedCommentSuccess(List<Comment.RowsBean> results);

    void onNewsCommentSuccess(String msg);

    void onNewsCommentLikeSuccess(String msg);
}
