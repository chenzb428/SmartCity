package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.DingdanResult;

import java.util.List;

public interface IDingdanCallback extends IBaseCallback {

    void onLoadedDingdanInfo(List<DingdanResult.RowsBean> result);
}
