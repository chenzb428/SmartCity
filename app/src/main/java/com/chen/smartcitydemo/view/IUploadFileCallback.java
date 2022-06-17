package com.chen.smartcitydemo.view;

import com.chen.smartcitydemo.base.IBaseCallback;

public interface IUploadFileCallback extends IBaseCallback {

    void onUploadFileSuccess(String url);
}
