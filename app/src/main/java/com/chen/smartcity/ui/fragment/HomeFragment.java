package com.chen.smartcity.ui.fragment;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.model.bean.HomeBannerResult;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IHomePresenter;
import com.chen.smartcity.ui.adapter.HomeRecommendAdapter;
import com.chen.smartcity.ui.adapter.ServerAdapter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.SizeUtils;
import com.chen.smartcity.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mHomePresenter;

    private Banner mBanner;
    private RecyclerView mServerList, mRecommendList;
    private TabLayout mNewTabLayout;
    private ViewPager2 mNewContent;
    private ServerAdapter mServerAdapter;
    private HomeRecommendAdapter mRecommendAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        mBanner = rootView.findViewById(R.id.home_banner);
        mServerList = rootView.findViewById(R.id.home_server_list);
        mRecommendList = rootView.findViewById(R.id.home_recommend_list);
        mNewContent = rootView.findViewById(R.id.home_new_content);
        mNewTabLayout = rootView.findViewById(R.id.home_new_tab);

        mServerList.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mServerAdapter = new ServerAdapter();
        mServerList.setAdapter(mServerAdapter);

        mRecommendList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecommendAdapter = new HomeRecommendAdapter();
        mRecommendList.setAdapter(mRecommendAdapter);
        mRecommendList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                int sizeLR = SizeUtils.dip2px(getContext(), 5);
                int sizeTB = SizeUtils.dip2px(getContext(), 6);
                outRect.left = sizeLR;
                outRect.right = sizeLR;
                outRect.top = sizeTB;
                outRect.bottom = sizeTB;
            }
        });
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
        mServerAdapter.setData(result);
    }

    @Override
    public void onLoadedRecommend(List<NewList.RowsBean> result) {
        mRecommendAdapter.setData(result);
    }

    @Override
    public void onLoadedNewCategory(List<NewCategory.DataBean> result) {
        mNewContent.setAdapter(new FragmentStateAdapter(getChildFragmentManager(), getLifecycle()) {
            @NonNull
            @NotNull
            @Override
            public Fragment createFragment(int position) {
                return HomeNewFragment.newInstance(result.get(position));
            }

            @Override
            public int getItemCount() {
                return result.size();
            }
        });
        new TabLayoutMediator(mNewTabLayout, mNewContent, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(result.get(position).getName());
            }
        }).attach();
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

    @Override
    protected void release() {
        if (mHomePresenter != null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }
}
