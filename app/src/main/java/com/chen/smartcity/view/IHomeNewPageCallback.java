package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.NewList;

import java.util.List;

public interface IHomeNewPageCallback extends IBaseCallback {

    void onLoadedNewPageContent(List<NewList.RowsBean> result);

    int getCategoryId();
}
