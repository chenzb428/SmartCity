package com.chen.smartcitydemo.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseFragment;
import com.chen.smartcitydemo.model.bean.Banner;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.model.bean.NewsCategory;
import com.chen.smartcitydemo.model.bean.Service;
import com.chen.smartcitydemo.presenter.IHomePresenter;
import com.chen.smartcitydemo.presenter.INewsPresenter;
import com.chen.smartcitydemo.presenter.IServicePresenter;
import com.chen.smartcitydemo.ui.activity.MainActivity;
import com.chen.smartcitydemo.ui.activity.NewsActivity;
import com.chen.smartcitydemo.ui.activity.SearchActivity;
import com.chen.smartcitydemo.ui.adapter.HomeHotNewsAdapter;
import com.chen.smartcitydemo.ui.adapter.HomeNewsAdapter;
import com.chen.smartcitydemo.ui.adapter.NewsContentAdapter;
import com.chen.smartcitydemo.ui.adapter.ServiceContentAdapter;
import com.chen.smartcitydemo.util.Constants;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.util.SizeUtils;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.IHomeCallback;
import com.chen.smartcitydemo.view.INewsCallback;
import com.chen.smartcitydemo.view.IServiceCallback;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends BaseFragment implements IHomeCallback, IServiceCallback, INewsCallback, ServiceContentAdapter.OnClickListener {

    private IHomePresenter homePresenter;
    private IServicePresenter allServicePresenter;
    private INewsPresenter newsPresenter;

    /**
     * 轮播图
     */
    private com.youth.banner.Banner banner;

    /**
     * 推荐服务入口
     */
    private RecyclerView serviceRecommendListView;
    private ServiceContentAdapter serviceContentAdapter;

    /**
     * 热门主题
     */
    private RecyclerView hotNewsListView;
    private HomeHotNewsAdapter homeHotNewsAdapter;

    /**
     * 新闻专栏
     */
    private TabLayout newsCategoryLayout;
    private RecyclerView newsListView;
    private HomeNewsAdapter homeNewsAdapter;
    private TabLayoutTabSelectedListener tabLayoutTabSelectedListener;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        // 轮播图
        banner = view.findViewById(R.id.home_banner);

        // 推荐服务入口
        serviceRecommendListView = view.findViewById(R.id.home_recommend_service_list);
        serviceRecommendListView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        serviceContentAdapter = new ServiceContentAdapter();
        serviceRecommendListView.setAdapter(serviceContentAdapter);

        // 热门主题
        hotNewsListView = view.findViewById(R.id.home_hot_news_list);
        hotNewsListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        homeHotNewsAdapter = new HomeHotNewsAdapter();
        hotNewsListView.setAdapter(homeHotNewsAdapter);

        // 新闻专栏
        newsCategoryLayout = view.findViewById(R.id.home_news_category);
        newsListView = view.findViewById(R.id.home_news_list);
        newsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeNewsAdapter = new HomeNewsAdapter();
        newsListView.setAdapter(homeNewsAdapter);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();

        homePresenter = PresenterManager.getInstance().getHomePresenter();
        homePresenter.registerViewCallback(this);

        allServicePresenter = PresenterManager.getInstance().getServicePresenter();
        allServicePresenter.registerViewCallback(this);

        newsPresenter = PresenterManager.getInstance().getNewsPresenter();
        newsPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        homePresenter.getBannerData();
        allServicePresenter.getAll();
        homePresenter.getHotNewsData();
        newsPresenter.getCategoryData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        tabLayoutTabSelectedListener = new TabLayoutTabSelectedListener();
        newsCategoryLayout.addOnTabSelectedListener(tabLayoutTabSelectedListener);
        serviceContentAdapter.setOnClickListener(this);

        homeHotNewsAdapter.setOnItemClickListener(new NewsContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int newsId) {
                NewsActivity.startActivity(getContext(), newsId);
            }
        });

        homeNewsAdapter.setOnItemClickListener(new NewsContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int newsId) {
                NewsActivity.startActivity(getContext(), newsId);
            }
        });

        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        ((MainActivity) getActivity()).getSearchView().setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        ((MainActivity) getActivity()).getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.startActivity(getContext(), query, SearchActivity.KEY_NEWS);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(int serviceId) {
        if (serviceId == 999) {
            ((MainActivity) getActivity()).selectTab(1);
        }
    }

    /**
     * TabLayout 的 Tab 选择监听事件
     */
    private class TabLayoutTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int id = (int) tab.getTag();

            newsPresenter.getCategoryContentData(id);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    @Override
    public void onLoadedBannerSuccess(List<Banner.RowsBean> results) {
        // 轮播图数据加载成功
        setUpState(State.SUCCESS);

        banner.addBannerLifecycleObserver(getViewLifecycleOwner())
                .setIndicator(new CircleIndicator(getContext()))
                .setAdapter(new BannerImageAdapter<Banner.RowsBean>(results) {

                    @Override
                    public void onBindView(BannerImageHolder holder, Banner.RowsBean rowsBean, int i, int i1) {
                        Glide.with(getContext())
                                .load(SpUtils.getHost(Constants.BASE_URL) + rowsBean.getAdvImg())
                                .into(holder.imageView);
                    }
                });
    }

    @Override
    public void onLoadedAllServiceSuccess(List<Service.RowsBean> results) {
        // 推荐服务数据成功
        setUpState(State.SUCCESS);

        // 按照 id 来进行降序排序
        Collections.sort(results, (o1, o2) -> o2.getId() - o1.getId());
        // 切割数组
        List<Service.RowsBean> rowsBeans = results.subList(0, 9);
        // 第二行最后一个 item 为更多服务
        rowsBeans.add(new Service.RowsBean(999, "更多服务"));

        serviceContentAdapter.setData(rowsBeans);
    }

    @Override
    public void onLoadedHotNewsSuccess(List<News.RowsBean> results) {
        // 热门主题数据加载成功
        setUpState(State.SUCCESS);

        homeHotNewsAdapter.setData(results);
    }

    @Override
    public void onLoadedNewsCategorySuccess(List<NewsCategory.DataBean> results) {
        // 新闻分类数据加载成功
        setUpState(State.SUCCESS);

        for (NewsCategory.DataBean result : results) {
            TextView textView = new TextView(getContext());
            textView.setText(result.getName());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(SizeUtils.sp2px(getContext(), 4f));
            TabLayout.Tab tab = newsCategoryLayout.newTab().setCustomView(textView).setTag(result.getId());
            newsCategoryLayout.addTab(tab);
        }
    }

    @Override
    public void onLoadedNewsCategoryContentSuccess(List<News.RowsBean> results) {
        // 新闻分类下的新闻数据加载成功
        setUpState(State.SUCCESS);

        homeNewsAdapter.setData(results);

        int containerHeight = ((MainActivity) getActivity()).getContainerHeight();
        int newCategoryHeight = newsCategoryLayout.getHeight();
        ViewGroup.LayoutParams layoutParams = newsListView.getLayoutParams();
        layoutParams.height = containerHeight - newCategoryHeight;
        newsListView.setLayoutParams(layoutParams);
    }

    @Override
    public void onLoadedAllNewsSuccess(List<News.RowsBean> results) {

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
        toast(msg);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (homePresenter != null) {
            homePresenter.unregisterViewCallback();
            homePresenter = null;
        }
        if (allServicePresenter != null) {
            allServicePresenter.unregisterViewCallback();
            allServicePresenter = null;
        }
        if (newsPresenter != null) {
            newsPresenter.unregisterViewCallback();
            newsPresenter = null;
        }
        if (serviceContentAdapter != null) {
            serviceRecommendListView.setAdapter(null);
            serviceContentAdapter = null;
        }
        if (homeHotNewsAdapter != null) {
            hotNewsListView.setAdapter(null);
            homeHotNewsAdapter = null;
        }
        if (homeNewsAdapter != null) {
            newsListView.setAdapter(null);
            homeNewsAdapter = null;
        }
        if (tabLayoutTabSelectedListener != null) {
            tabLayoutTabSelectedListener = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (banner != null) banner = null;
        if (serviceRecommendListView != null) serviceRecommendListView = null;
        if (hotNewsListView != null) hotNewsListView = null;
        if (newsCategoryLayout != null) newsCategoryLayout = null;
        if (newsListView != null) newsListView = null;
    }
}
