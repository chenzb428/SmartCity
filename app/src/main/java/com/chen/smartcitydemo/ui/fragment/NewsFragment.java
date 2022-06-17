package com.chen.smartcitydemo.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseFragment;
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.model.bean.NewsCategory;
import com.chen.smartcitydemo.presenter.INewsPresenter;
import com.chen.smartcitydemo.ui.activity.MainActivity;
import com.chen.smartcitydemo.ui.activity.NewsActivity;
import com.chen.smartcitydemo.ui.adapter.NewsContentAdapter;
import com.chen.smartcitydemo.util.Constants;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.util.SizeUtils;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.INewsCallback;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.Collections;
import java.util.List;

public class NewsFragment extends BaseFragment implements INewsCallback {

    private Banner banner;

    private TabLayout newsCategoryLayout;

    private RecyclerView newsListView;
    private INewsPresenter newsPresenter;
    private TabLayoutTabSelectedListener tabLayoutTabSelectedListener;
    private NewsContentAdapter newsContentAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        banner = view.findViewById(R.id.news_banner);

        newsCategoryLayout = view.findViewById(R.id.news_category);

        newsListView = view.findViewById(R.id.news_list);
        newsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsContentAdapter = new NewsContentAdapter();
        newsListView.setAdapter(newsContentAdapter);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        newsPresenter = PresenterManager.getInstance().getNewsPresenter();
        newsPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        newsPresenter.getAllData();
        newsPresenter.getCategoryData();
    }

    @Override
    protected void initListener() {
        super.initListener();

        tabLayoutTabSelectedListener = new TabLayoutTabSelectedListener();
        newsCategoryLayout.addOnTabSelectedListener(tabLayoutTabSelectedListener);

        newsContentAdapter.setOnItemClickListener(new NewsContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int newsId) {
                NewsActivity.startActivity(getContext(), newsId);
            }
        });
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
    public void onLoadedNewsCategorySuccess(List<NewsCategory.DataBean> results) {
        setUpState(State.SUCCESS);

        for (NewsCategory.DataBean result : results) {
            TextView textView = new TextView(getContext());
            textView.setText(result.getName());
            textView.setTextSize(SizeUtils.sp2px(getContext(), 4f));
            textView.setGravity(Gravity.CENTER);
            TabLayout.Tab tab = newsCategoryLayout.newTab().setCustomView(textView).setTag(result.getId());
            newsCategoryLayout.addTab(tab);
        }
    }

    @Override
    public void onLoadedNewsCategoryContentSuccess(List<News.RowsBean> results) {
        newsContentAdapter.setData(results);

        int containerHeight = ((MainActivity) getActivity()).getContainerHeight();
        int newCategoryHeight = newsCategoryLayout.getHeight();
        ViewGroup.LayoutParams layoutParams = newsListView.getLayoutParams();
        layoutParams.height = containerHeight - newCategoryHeight;
        newsListView.setLayoutParams(layoutParams);
    }

    @Override
    public void onLoadedAllNewsSuccess(List<News.RowsBean> results) {
        setUpState(State.SUCCESS);

        Collections.shuffle(results);
        List<News.RowsBean> rowsBeans = results.subList(0, 4);

        banner.addBannerLifecycleObserver(getViewLifecycleOwner())
                .setIndicator(new CircleIndicator(getContext()))
                .setAdapter(new BannerImageAdapter<News.RowsBean>(rowsBeans) {
                    @Override
                    public void onBindView(BannerImageHolder holder, News.RowsBean rowsBean, int i, int i1) {
                        Glide.with(getContext())
                                .load(SpUtils.getHost(Constants.BASE_URL) + rowsBean.getCover())
                                .into(holder.imageView);
                    }
                })
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object o, int i) {
                        NewsActivity.startActivity(getContext(), results.get(i).getId());
                    }
                });
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
        if (newsPresenter != null) {
            newsPresenter.unregisterViewCallback();
            newsPresenter = null;
        }
        if (tabLayoutTabSelectedListener != null) tabLayoutTabSelectedListener = null;
        if (newsContentAdapter != null) {
            newsListView.setAdapter(null);
            newsContentAdapter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (banner != null) banner = null;
        if (newsCategoryLayout != null) newsCategoryLayout = null;
        if (newsListView != null) newsListView = null;
    }
}
