package com.chen.smartcity.ui.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.ui.fragment.DangJianFragment;
import com.chen.smartcity.ui.fragment.HomeFragment;
import com.chen.smartcity.ui.fragment.UserInfoFragment;
import com.chen.smartcity.ui.fragment.NewFragment;
import com.chen.smartcity.ui.fragment.ServerFragment;
import com.chen.smartcity.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;
    private HomeFragment mHomeFragment;
    private ServerFragment mServerFragment;
    private DangJianFragment mDangJianFragment;
    private NewFragment mNewFragment;
    private UserInfoFragment mMineFragment;
    private FragmentManager mManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mBottomNavigationView = findViewById(R.id.main_nav);

        mHomeFragment = new HomeFragment();
        mServerFragment = new ServerFragment();
        mDangJianFragment = new DangJianFragment();
        mNewFragment = new NewFragment();
        mMineFragment = new UserInfoFragment();

        mManager = getSupportFragmentManager();

        switchFragment(mHomeFragment);
    }

    @Override
    protected void initListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                LogUtils.d(MainActivity.this, "OnNavigationItemSelectedListener === > " + item.getTitle());
                if (item.getItemId() == R.id.nav_home) {
                    switchFragment(mHomeFragment);
                } else if (item.getItemId() == R.id.nav_server) {
                    switchFragment(mServerFragment);
                } else if (item.getItemId() == R.id.nav_dangjian) {
                    switchFragment(mDangJianFragment);
                } else if (item.getItemId() == R.id.nav_new) {
                    switchFragment(mNewFragment);
                } else if (item.getItemId() == R.id.nav_mine) {

                    switchFragment(mMineFragment);
                }
                return true;
            }
        });
    }

    private BaseFragment lastOneFragment = null;

    private void switchFragment(BaseFragment targetFragment) {
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(R.id.main_container, targetFragment);
        } else {
            transaction.show(targetFragment);
        }
        if (lastOneFragment != null && lastOneFragment != targetFragment) {
            transaction.hide(lastOneFragment);
        }
        lastOneFragment = targetFragment;
        transaction.commit();
    }
}