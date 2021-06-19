package com.chen.smartcity.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.presenter.IHomeNewPagePresenter;
import com.chen.smartcity.ui.adapter.HomeNewPageAdapter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.SizeUtils;
import com.chen.smartcity.view.IHomeNewPageCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeNewFragment extends BaseFragment implements IHomeNewPageCallback {

    private int mNewId;
    private IHomeNewPagePresenter mHomeNewPagePresenter;

    private RecyclerView mNewList;
    private HomeNewPageAdapter mNewPageAdapter;

    public static HomeNewFragment newInstance(NewCategory.DataBean result) {
        HomeNewFragment homeNewFragment = new HomeNewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_HOME_NEW_ID, result.getId());
        homeNewFragment.setArguments(bundle);
        return homeNewFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_new;
    }

    @Override
    protected void initView(View rootView) {
        mNewList = rootView.findViewById(R.id.home_new_list);

        mNewList.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewPageAdapter = new HomeNewPageAdapter();
        mNewList.setAdapter(mNewPageAdapter);
        mNewList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                int size = SizeUtils.dip2px(getContext(), 5);
                outRect.top = size;
                outRect.bottom = size;
                outRect.left = size;
                outRect.right = size;
            }
        });
    }

    @Override
    protected void initPresenter() {
        mHomeNewPagePresenter = PresenterManager.getInstance().getHomeNewPagePresenter();
        mHomeNewPagePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        mNewId = bundle.getInt(Constants.KEY_HOME_NEW_ID);
        LogUtils.d(this, "new id === > " + mNewId);
        if (mHomeNewPagePresenter != null) {
            mHomeNewPagePresenter.getNewPageContent(mNewId);
        }
    }

    @Override
    public void onLoadedNewPageContent(List<NewList.RowsBean> result) {
        mNewPageAdapter.setData(result);
        setUpState(State.SUCCESS);
    }

    @Override
    public int getCategoryId() {
        return mNewId;
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
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
    protected void release() {
        if (mHomeNewPagePresenter != null) {
            mHomeNewPagePresenter.unregisterViewCallback(this);
        }
    }
}
