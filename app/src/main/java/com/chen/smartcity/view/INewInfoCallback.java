package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.NewCommentsResult;
import com.chen.smartcity.model.bean.NewResult;
import com.chen.smartcity.model.bean.Result;

import java.util.List;

public interface INewInfoCallback extends IBaseCallback {

    void onLoadedNewInfo(NewResult result);

    void onLoadedNewComments(List<NewCommentsResult.RowsBean> result);

    void onLoadingNewComments();

    void onLoadedNewCommentsError();

    void onLoadedNewCommentsEmpty();

    void onDoComment(Result result);

    void onDoCommentError();
}
