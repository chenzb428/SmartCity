package com.chen.smartcity.ui.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IAllNewsPresenter;
import com.chen.smartcity.presenter.IHomeNewPagePresenter;
import com.chen.smartcity.ui.adapter.HomeNewPageAdapter;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.view.IAllNewsCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeSearchActivity extends BaseActivity implements IAllNewsCallback {

    private RecyclerView mRecyclerView;
    private IAllNewsPresenter mAllNewsPresenter;
    private String mKeyword;
    private HomeNewPageAdapter mNewPageAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.recycler_view_layout;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mNewPageAdapter = new HomeNewPageAdapter();
        mRecyclerView.setAdapter(mNewPageAdapter);
    }

    @Override
    protected void initPresenter() {
        mAllNewsPresenter = PresenterManager.getInstance().getAllNewsPresenter();
        mAllNewsPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mKeyword = getIntent().getStringExtra("homeSearch");
        mAllNewsPresenter.getAllNews();
    }

    @Override
    public void onLoadedAllNews(List<NewList.RowsBean> result) {
        List<NewList.RowsBean> searchData = search(result, mKeyword);
        mNewPageAdapter.setData(searchData);
        LogUtils.d(HomeSearchActivity.this, "onLoadedAllNews searchData === > " + searchData.toString());
    }

    private List search(List<NewList.RowsBean> bean, String search) {
        List<NewList.RowsBean> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(search);
        for (int i=0; i<bean.size(); i++) {
            Matcher matcher = pattern.matcher(bean.get(i).getTitle());
            if (matcher.find()) {
                result.add(bean.get(i));
            }
        }
        return result;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    protected void release() {
        if (mAllNewsPresenter != null) {
            mAllNewsPresenter.unregisterViewCallback(this);
        }
    }
}