package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.ServerResult;

import java.util.List;

public interface IServerCallback extends IBaseCallback {

    void onLoadedServer(List<ServerResult.RowsBean> result);
}
