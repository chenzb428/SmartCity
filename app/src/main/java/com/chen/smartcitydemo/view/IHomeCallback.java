package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;
import com.chen.smartcitydemo.model.bean.Banner;
import com.chen.smartcitydemo.model.bean.News;

import java.util.List;

public interface IHomeCallback extends IBaseCallback {

    void onLoadedBannerSuccess(List<Banner.RowsBean> results);

    void onLoadedHotNewsSuccess(List<News.RowsBean> results);
}
