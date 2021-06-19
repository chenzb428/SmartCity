package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.NewList;

import java.util.List;

public interface IAllNewsCallback extends IBaseCallback {

    void onLoadedAllNews(List<NewList.RowsBean> result);
}
