package com.chen.smartcitydemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chen.smartcitydemo.R;

public abstract class BaseActivity extends AppCompatActivity {

    public enum State {
        LOADING, // 加载中
        SUCCESS, // 成功
        EMPTY,   // 内容空
        ERROR,   // 出错
        NONE     // 啥都没有
    }

    /**
     * 根视图
     */
    private View mRootView;

    /**
     * 加载中、成功、空、错误视图
     */
    private View mLoadingView, mSuccessView, mEmptyView, mErrorView;

    /**
     * 视图容器
     */
    private FrameLayout mBaseContainer;

    /**
     * 视图当前状态
     */
    protected State mCurrentState = State.NONE;

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = loadRootView();
        mBaseContainer = mRootView.findViewById(R.id.container);
        initStateView();
        initWindow();
        setContentView(mRootView);

        initView();
        initPresenter();
        initData(savedInstanceState);
        initListener();
    }

    /**
     * 初始化各种视图状态
     */
    private void initStateView() {
        // 加载中视图
        mLoadingView = loadingView();
        mBaseContainer.addView(mLoadingView);

        // 成功视图
        mSuccessView = loadSuccessView();
        mBaseContainer.addView(mSuccessView);

        // 空视图
        mEmptyView = loadEmptyView();
        mBaseContainer.addView(mEmptyView);

        // 错误视图
        mErrorView = loadErrorView();
        mBaseContainer.addView(mErrorView);

        setUpState(State.NONE);
    }

    protected View loadingView() {
        return getView(R.layout.layout_loading_view);
    }

    protected View loadSuccessView() {
        return getView(getLayoutResId());
    }

    protected View loadEmptyView() {
        return getView(R.layout.layout_empty_view);
    }

    protected View loadErrorView() {
        return getView(R.layout.layout_error_view);
    }

    protected View loadRootView() {
        return getView(R.layout.layout_base_container);
    }

    protected View getView(int layoutResId) {
        return LayoutInflater.from(this).inflate(layoutResId, null);
    }

    /**
     * 设置视图状态
     * @param state 状态
     */
    protected void setUpState(State state) {
        this.mCurrentState = state;
        mLoadingView.setVisibility(mCurrentState == State.LOADING ? View.VISIBLE : View.GONE);
        mSuccessView.setVisibility(mCurrentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == State.EMPTY ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == State.ERROR ? View.VISIBLE : View.GONE);
    }

    protected void initWindow() {

    }

    protected abstract int getLayoutResId();

    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
    }

    protected void initPresenter() {

    }

    protected void initData(Bundle savedInstanceState) {

    }

    protected void initListener() {
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> { finish(); });
        }
    }

    /**
     * 吐司  提示
     * @param msg 需要提示的消息
     */
    protected void toast(String msg) {
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseInterface();
        releaseUI();
    }

    /**
     * 释放资源，接口
     */
    protected void releaseInterface() {

    }

    /**
     * 释放资源，view相关
     */
    protected void releaseUI() {
        if (toolbar != null) toolbar = null;
    }
}
