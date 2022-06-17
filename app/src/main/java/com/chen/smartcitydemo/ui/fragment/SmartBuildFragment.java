package com.chen.smartcitydemo.ui.fragment;

import android.view.View;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseFragment;

public class SmartBuildFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_smart_build;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setUpState(State.SUCCESS);
    }
}
