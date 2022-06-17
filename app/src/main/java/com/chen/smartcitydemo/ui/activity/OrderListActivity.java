package com.chen.smartcitydemo.ui.activity;

import android.view.View;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;

public class OrderListActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected View loadRootView() {
        return getView(R.layout.layout_base_toolbar_container);
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.EMPTY);

        if (toolbar != null) {
            toolbar.setTitle("订单列表");
        }
    }
}