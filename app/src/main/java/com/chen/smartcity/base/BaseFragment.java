package com.chen.smartcity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chen.smartcity.R;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {

    public enum State { LOADING, SUCCESS, ERROR, EMPTY, NONE }

    private State mCurrentState = State.NONE;
    private View mRootView, mLoadingView, mSuccessView, mErrorView, mEmptyView;
    private FrameLayout mBaseContainer;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mRootView = loadRootView(inflater, container);
        mBaseContainer = mRootView.findViewById(R.id.base_container);
        loadStateView(inflater, container);
        initView(mRootView);
        initPresenter();
        initListener();
        loadData();
        return mRootView;
    }

    protected void loadData() {
    }

    protected void initListener() {
    }

    protected void initPresenter() {
    }

    protected void initView(View rootView) {
    }

    private void loadStateView(LayoutInflater inflater, ViewGroup container) {
        mLoadingView = loadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);

        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);

        mEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);

        setUpState(State.NONE);
    }

    protected void setUpState(State state) {
        this.mCurrentState = state;
        mSuccessView.setVisibility(mCurrentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(mCurrentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == State.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == State.EMPTY ? View.VISIBLE : View.GONE);
    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty_view, container, false);
    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        View errorView = inflater.inflate(R.layout.fragment_error_view, container, false);
        errorView.findViewById(R.id.network_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry();
            }
        });
        return errorView;
    }

    protected void onRetry() {
    }

    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        int resId = getLayoutResId();
        return inflater.inflate(resId, container, false);
    }

    protected abstract int getLayoutResId();

    protected View loadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading_view, container, false);
    }

    protected View loadRootView(@NotNull LayoutInflater inflater, @org.jetbrains.annotations.Nullable ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    protected void release() {
    }
}
