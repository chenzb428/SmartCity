package com.chen.smartcity.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.view.IHomeCallback;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mHomePresenter;

    private Banner mBanner;
    private RecyclerView mServerList, mRecommendList;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        mBanner = rootView.findViewById(R.id.home_banner);
        mServerList = rootView.findViewById(R.id.home_server);
        mRecommendList = rootView.findViewById(R.id.home_recommend);
    }

    @Override
    protected void initPresenter() {
        mHomePresenter = PresenterManager.getInstance().getHomePresenter();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mHomePresenter.getBanner();
        mHomePresenter.getServer();
        mHomePresenter.getRecommend();
        mHomePresenter.getNewCategory();
    }

    @Override
    public void onLoadedBanner(List<HomeBannerResult.RowsBean> result) {
        mBanner.addBannerLifecycleObserver(getViewLifecycleOwner())
                .setIndicator(new CircleIndicator(getContext()))
                .setAdapter(new BannerImageAdapter<HomeBannerResult.RowsBean>(result) {
                    @Override
                    public void onBindView(BannerImageHolder holder, HomeBannerResult.RowsBean rowsBean, int i, int i1) {
                        Glide.with(getContext()).load(Constants.BASE_URL + rowsBean.getAdvImg()).into(holder.imageView);
                    }
                });
        setUpState(State.SUCCESS);
    }

    @Override
    public void onLoadedServer(List<ServerResult.RowsBean> result) {

    }

    @Override
    public void onLoadedRecommend(List<NewList.RowsBean> result) {

    }

    @Override
    public void onLoadedNewCategory(List<NewCategory.DataBean> result) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {

    }
}
