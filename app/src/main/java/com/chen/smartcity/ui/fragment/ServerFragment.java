package com.chen.smartcity.ui.fragment;

import android.view.View;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;

public class ServerFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_server;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
