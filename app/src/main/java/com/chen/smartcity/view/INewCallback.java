package com.chen.smartcity.view;

import com.chen.smartcity.base.IBaseCallback;
import com.chen.smartcity.model.bean.NewCategory;

import java.util.List;

public interface INewCallback extends IBaseCallback, IAllNewsCallback {

    void onLoadedNewCategory(List<NewCategory.DataBean> category);
}
