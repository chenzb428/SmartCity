package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.model.bean.NewsCategory;

import java.util.List;

public interface INewsCallback extends IBaseCallback {

    void onLoadedNewsCategorySuccess(List<NewsCategory.DataBean> results);

    void onLoadedNewsCategoryContentSuccess(List<News.RowsBean> results);

    void onLoadedAllNewsSuccess(List<News.RowsBean> results);
}
