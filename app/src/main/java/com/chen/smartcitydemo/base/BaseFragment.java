package com.chen.smartcitydemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chen.smartcitydemo.R;

public abstract class BaseFragment extends Fragment {

    public enum State {
        LOADING,
        SUCCESS,
        EMPTY,
        ERROR,
        NONE
    }

    protected State mCurrentState = State.NONE;

    private FrameLayout mRootContainerLayout;

    private View mLoadingView, mSuccessView, mEmptyView, mErrorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container);
        mRootContainerLayout = rootView.findViewById(R.id.container);
        initStateView(inflater, container);

        initView(rootView);
        initPresenter();
        initData(savedInstanceState);
        initListener();
        return rootView;
    }

    protected void initView(View view) {

    }

    protected void initPresenter() {

    }

    protected void initData(Bundle savedInstanceState) {

    }

    protected void initListener() {

    }

    private void initStateView(LayoutInflater inflater, ViewGroup container) {
        mLoadingView = getView(inflater, container, R.layout.layout_loading_view);
        mRootContainerLayout.addView(mLoadingView);

        mSuccessView = getView(inflater, container, getLayoutResId());
        mRootContainerLayout.addView(mSuccessView);

        mEmptyView = getView(inflater, container, R.layout.layout_empty_view);
        mRootContainerLayout.addView(mEmptyView);

        mErrorView = getView(inflater, container, R.layout.layout_error_view);
        mRootContainerLayout.addView(mErrorView);

        setUpState(State.NONE);
    }

    protected void setUpState(State state) {
        this.mCurrentState = state;
        mLoadingView.setVisibility(mCurrentState == State.LOADING ? View.VISIBLE : View.GONE);
        mSuccessView.setVisibility(mCurrentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == State.EMPTY ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == State.ERROR ? View.VISIBLE : View.GONE);
    }

    protected abstract int getLayoutResId();

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return getView(inflater, container, R.layout.layout_base_container);
    }

    protected View getView(LayoutInflater inflater, ViewGroup container, int layoutResId) {
        return inflater.inflate(layoutResId, container, false);
    }

    protected void toast(String msg) {
        requireActivity().runOnUiThread(() -> Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseInterface();
        releaseUI();
    }

    protected void releaseInterface() {

    }

    protected void releaseUI() {

    }
}
