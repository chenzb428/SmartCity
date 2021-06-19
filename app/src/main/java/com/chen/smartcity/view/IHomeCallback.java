package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.ServerResult;

import java.util.List;

public interface IHomeCallback extends IBaseCallback {

    void onLoadedBanner(List<HomeBannerResult.RowsBean> result);

    void onLoadedServer(List<ServerResult.RowsBean> result);

    void onLoadedRecommend(List<NewList.RowsBean> result);

    void onLoadedNewCategory(List<NewCategory.DataBean> result);
}
