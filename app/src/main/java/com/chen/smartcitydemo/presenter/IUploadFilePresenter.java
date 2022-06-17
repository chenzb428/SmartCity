package com.chen.smartcitydemo.presenter;

import com.chen.smartcitydemo.base.IBasePresenter;
import com.chen.smartcitydemo.view.IUploadFileCallback;

public interface IUploadFilePresenter extends IBasePresenter<IUploadFileCallback> {

    void uploadFile(String path);
}
