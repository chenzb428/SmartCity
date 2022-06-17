package com.chen.smartcitydemo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.base.BaseFragment;
import com.chen.smartcitydemo.ui.fragment.ServiceFragment;
import com.chen.smartcitydemo.ui.fragment.HomeFragment;
import com.chen.smartcitydemo.ui.fragment.UserInfoFragment;
import com.chen.smartcitydemo.ui.fragment.NewsFragment;
import com.chen.smartcitydemo.ui.fragment.SmartBuildFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private TextView titleTv;
    private CardView searchLayout;
    private FrameLayout containerLayout;
    private SearchView searchView;

    private BaseFragment mHomeFragment, mAllServiceFragment, mSmartBuildFragment, mNewsFragment, mMineFragment;

    private TabLayout.Tab[] tabs;

    private TabLayoutTabSelectedListener tabLayoutTabSelectedListener;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.SUCCESS);

        tabLayout = findViewById(R.id.main_tab_layout);
        titleTv = findViewById(R.id.main_title_tv);
        searchLayout = findViewById(R.id.main_search_layout);
        containerLayout = findViewById(R.id.main_container_layout);
        searchView = findViewById(R.id.main_search_view);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        // 初始化 Tab
        TabLayout.Tab home = tabLayout.newTab().setIcon(R.mipmap.ic_launcher_round).setText(R.string.main_tab_home);
        TabLayout.Tab allService = tabLayout.newTab().setIcon(R.mipmap.ic_launcher_round).setText(R.string.main_tab_all_service);
        TabLayout.Tab smartBuild = tabLayout.newTab().setIcon(R.mipmap.ic_launcher_round).setText(R.string.main_tab_smart_build);
        TabLayout.Tab news = tabLayout.newTab().setIcon(R.mipmap.ic_launcher_round).setText(R.string.main_tab_news);
        TabLayout.Tab mine = tabLayout.newTab().setIcon(R.mipmap.ic_launcher_round).setText(R.string.main_tab_mine);

        tabs = new TabLayout.Tab[] {
                home,
                allService,
                smartBuild,
                news,
                mine
        };

        tabLayout.addTab(home, true);
        tabLayout.addTab(allService);
        tabLayout.addTab(smartBuild);
        tabLayout.addTab(news);
        tabLayout.addTab(mine);

        // 初始化各页面 Fragment
        mHomeFragment = new HomeFragment();
        mAllServiceFragment = new ServiceFragment();
        mSmartBuildFragment = new SmartBuildFragment();
        mNewsFragment = new NewsFragment();
        mMineFragment = new UserInfoFragment();

        // 默认选择第一个tab
        selectTab(0);
    }

    @Override
    protected void initListener() {
        super.initListener();

        tabLayoutTabSelectedListener = new TabLayoutTabSelectedListener();
        tabLayout.addOnTabSelectedListener(tabLayoutTabSelectedListener);
    }

    /**
     * TabLayout 的 Tab 选择监听事件
     */
    private class TabLayoutTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            String tabText = tab.getText().toString();
            titleTv.setText(tabText);

            if (tabText.equals(getString(R.string.main_tab_home))) {
                titleTv.setText(getString(R.string.app_name));
                searchLayout.setVisibility(View.VISIBLE);
                replaceFragment(mHomeFragment);
            } else if (tabText.equals(getString(R.string.main_tab_all_service))) {
                searchLayout.setVisibility(View.VISIBLE);
                replaceFragment(mAllServiceFragment);
            } else if (tabText.equals(getString(R.string.main_tab_smart_build))) {
                searchLayout.setVisibility(View.GONE);
                replaceFragment(mSmartBuildFragment);
            } else if (tabText.equals(getString(R.string.main_tab_news))) {
                searchLayout.setVisibility(View.GONE);
                replaceFragment(mNewsFragment);
            } else if (tabText.equals(getString(R.string.main_tab_mine))) {
                searchLayout.setVisibility(View.GONE);
                replaceFragment(mMineFragment);
            } else {
                // TODO
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    public int getContainerHeight() {
        return containerLayout.getHeight();
    }

    /**
     * 选择tab，切换显示 Fragment
     * @param position 选择位置
     */
    public void selectTab(int position) {
        tabs[position].select();

        if (position == 0) {
            replaceFragment(mHomeFragment);
        } else if (position == 1) {
            replaceFragment(mAllServiceFragment);
        } else if (position == 2) {
            replaceFragment(mSmartBuildFragment);
        } else if (position == 3) {
            replaceFragment(mNewsFragment);
        } else if (position == 4) {
            replaceFragment(mMineFragment);
        } else {

        }
    }

    private void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_layout, fragment).commit();
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (tabLayoutTabSelectedListener != null) tabLayoutTabSelectedListener = null;
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (tabLayout != null) tabLayout = null;
        if (titleTv != null) titleTv = null;
        if (searchLayout != null) searchLayout = null;
        if (containerLayout != null) containerLayout = null;
        if (searchView != null) searchView = null;
        if (mHomeFragment != null) mHomeFragment = null;
        if (mAllServiceFragment != null) mAllServiceFragment = null;
        if (mSmartBuildFragment != null) mSmartBuildFragment = null;
        if (mNewsFragment != null) mNewsFragment = null;
        if (mMineFragment != null) mMineFragment = null;
    }

    public SearchView getSearchView() {
        return searchView;
    }
}