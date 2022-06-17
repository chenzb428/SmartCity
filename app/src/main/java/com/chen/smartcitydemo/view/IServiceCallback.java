package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;
import com.chen.smartcitydemo.model.bean.Service;

import java.util.List;

public interface IServiceCallback extends IBaseCallback {

    /**
     * 全都服务分类获取完成
     * @param results 结果
     */
    void onLoadedAllServiceSuccess(List<Service.RowsBean> results);
}
