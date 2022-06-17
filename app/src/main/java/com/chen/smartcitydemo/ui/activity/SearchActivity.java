package com.chen.smartcitydemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.model.bean.NewsCategory;
import com.chen.smartcitydemo.model.bean.Service;
import com.chen.smartcitydemo.presenter.INewsPresenter;
import com.chen.smartcitydemo.presenter.IServicePresenter;
import com.chen.smartcitydemo.ui.adapter.NewsContentAdapter;
import com.chen.smartcitydemo.ui.adapter.ServiceContentAdapter;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.INewsCallback;
import com.chen.smartcitydemo.view.IServiceCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchActivity extends BaseActivity implements INewsCallback, IServiceCallback {

    private static final String SEARCH_CONTENT = "search_content";
    private static final String SEARCH_TYPE = "search_type";
    public static final String KEY_NEWS = "key_news";
    public static final String KEY_SERVICE = "key_service";

    private INewsPresenter newsPresenter;
    private IServicePresenter servicePresenter;

    private String content;

    private TextView hintTv;
    private RecyclerView searchListView;

    private NewsContentAdapter newsContentAdapter;
    private ServiceContentAdapter serviceContentAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected View loadRootView() {
        return getView(R.layout.layout_base_toolbar_container);
    }

    public static void startActivity(Context context, String content, String type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(SEARCH_CONTENT, content);
        intent.putExtra(SEARCH_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        if (toolbar != null) {
            toolbar.setTitle("搜索详情");
        }

        hintTv = findViewById(R.id.search_hint_tv);

        searchListView = findViewById(R.id.search_list_view);
        searchListView.setLayoutManager(new LinearLayoutManager(this));
        if (getIntent().getStringExtra(SEARCH_TYPE).equals(KEY_NEWS)) {
            newsContentAdapter = new NewsContentAdapter();
            searchListView.setAdapter(newsContentAdapter);
        }
        if (getIntent().getStringExtra(SEARCH_TYPE).equals(KEY_SERVICE)) {
            serviceContentAdapter = new ServiceContentAdapter();
            searchListView.setAdapter(serviceContentAdapter);
        }

    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        newsPresenter = PresenterManager.getInstance().getNewsPresenter();
        newsPresenter.registerViewCallback(this);
        servicePresenter = PresenterManager.getInstance().getServicePresenter();
        servicePresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        content = getIntent().getStringExtra(SEARCH_CONTENT);

        if (getIntent().getStringExtra(SEARCH_TYPE).equals(KEY_NEWS)) {
            newsPresenter.getAllData();
        }

        if (getIntent().getStringExtra(SEARCH_TYPE).equals(KEY_SERVICE)) {
            servicePresenter.getAll();
        }
    }

    @Override
    public void onLoadedNewsCategorySuccess(List<NewsCategory.DataBean> results) {

    }

    @Override
    public void onLoadedNewsCategoryContentSuccess(List<News.RowsBean> results) {

    }

    @Override
    public void onLoadedAllNewsSuccess(List<News.RowsBean> results) {
        List<News.RowsBean> rowsBeans = searchNews(results);
        if (rowsBeans.size() > 0) {
            setUpState(State.SUCCESS);
            newsContentAdapter.setData(rowsBeans);
        } else {
            setUpState(State.EMPTY);
        }
    }

    @Override
    public void onLoadedAllServiceSuccess(List<Service.RowsBean> results) {
        List<Service.RowsBean> rowsBeans = searchServices(results);
        if (rowsBeans.size() > 0) {
            setUpState(State.SUCCESS);
            serviceContentAdapter.setData(rowsBeans);
        } else {
            setUpState(State.EMPTY);
        }
    }

    private List<Service.RowsBean> searchServices(List<Service.RowsBean> beans) {
        List<Service.RowsBean> results = new ArrayList<>();

        Pattern pattern = Pattern.compile(content);
        for (Service.RowsBean bean : beans) {
            Matcher matcher = pattern.matcher(bean.getServiceName());
            if (matcher.find()) {
                results.add(bean);
            }
        }

        return results;
    }

    private List<News.RowsBean> searchNews(List<News.RowsBean> beans) {
        List<News.RowsBean> results = new ArrayList<>();

        Pattern pattern = Pattern.compile(content);
        for (News.RowsBean bean : beans) {
            Matcher matcher = pattern.matcher(bean.getTitle());
            if (matcher.find()) {
                results.add(bean);
            }
        }

        return results;
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    public void onError(String msg) {
        setUpState(State.ERROR);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (newsPresenter != null) {
            newsPresenter.unregisterViewCallback();
            newsPresenter = null;
        }
        if (servicePresenter != null) {
            servicePresenter.unregisterViewCallback();
            servicePresenter = null;
        }
        if (content != null) content = null;
        if (newsContentAdapter != null) {
            searchListView.setAdapter(null);
            newsContentAdapter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (hintTv != null) hintTv = null;
        if (searchListView != null) searchListView = null;
    }
}