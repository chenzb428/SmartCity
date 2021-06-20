package com.chen.smartcity.ui.activity.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.DingdanResult;
import com.chen.smartcity.presenter.IDingdanPresenter;
import com.chen.smartcity.ui.adapter.DingdanAdapter;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.IDingdanCallback;

import java.util.List;

public class DingdanActivity extends BaseActivity implements IDingdanCallback {

    private TextView emptyTv;
    private RecyclerView mDingdanList;
    private DingdanAdapter mDingdanAdapter;
    private IDingdanPresenter mDingdanPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dingdan;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("订单列表");

        emptyTv = findViewById(R.id.dingdan_empty);
        mDingdanList = findViewById(R.id.dingdan_list);

        mDingdanList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mDingdanAdapter = new DingdanAdapter();
        mDingdanList.setAdapter(mDingdanAdapter);
    }

    @Override
    protected void initPresenter() {
        mDingdanPresenter = PresenterManager.getInstance().getDingdanPresenter();
        mDingdanPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        if (mDingdanPresenter != null) {
            mDingdanPresenter.getDingdanInfo(findByKey("token"));
        }
    }

    @Override
    public void onLoadedDingdanInfo(List<DingdanResult.RowsBean> result) {
        emptyTv.setVisibility(View.GONE);
        mDingdanAdapter.setData(result);
    }

    @Override
    public void onError() {
        ToastUtils.showToast("加载失败！请重新进入...");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {
        emptyTv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void release() {
        super.release();
        if (mDingdanPresenter != null) {
            mDingdanPresenter.unregisterViewCallback(this);
        }
    }
}