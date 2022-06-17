package com.chen.smartcitydemo.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.presenter.IFeedBackPresenter;
import com.chen.smartcitydemo.ui.adapter.FeedBackAdapter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.IFeedBackCallback;
import com.google.android.material.textfield.TextInputLayout;

public class FeedBackActivity extends BaseActivity implements IFeedBackCallback {

    private IFeedBackPresenter feetBackPresenter;

    private TextInputLayout titleLayout;
    private TextInputLayout contentLayout;
    private RecyclerView feedBackListView;
    private FeedBackAdapter feedBackAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feet_back;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        menu.findItem(R.id.menu_text).setTitle("提交");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_text) {
            // 获取输入的意见
            String title = titleLayout.getEditText().getText().toString().trim();
            String content = contentLayout.getEditText().getText().toString().trim();

            LogUtils.d(this, "onOptionsItemSelected: title -> " + title + " content -> " + content);

            feetBackPresenter.doFeetBack(title, content);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.SUCCESS);

        if (toolbar != null) {
            toolbar.setTitle("意见反馈");
            setSupportActionBar(toolbar);
        }

        titleLayout = findViewById(R.id.feet_back_title_layout);
        contentLayout = findViewById(R.id.feet_back_content_layout);

        feedBackListView = findViewById(R.id.feed_back_list_view);
        feedBackListView.setLayoutManager(new LinearLayoutManager(this));
        feedBackAdapter = new FeedBackAdapter();
        feedBackListView.setAdapter(feedBackAdapter);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        feetBackPresenter = PresenterManager.getInstance().getFeetBackPresenter();
        feetBackPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void onFeetBackSuccess(String msg) {
        setUpState(State.SUCCESS);
        toast(msg);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String msg) {
        setUpState(State.SUCCESS);
        toast(msg);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (feetBackPresenter != null) {
            feetBackPresenter.unregisterViewCallback();
            feetBackPresenter = null;
        }
        if (feedBackAdapter != null) {
            feedBackListView.setAdapter(null);
            feedBackAdapter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (titleLayout != null) titleLayout = null;
        if (contentLayout != null) contentLayout = null;
        if (feedBackListView != null) feedBackListView = null;
    }
}