package com.chen.smartcity.ui.fragment;

import android.view.View;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;

public class MineFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
