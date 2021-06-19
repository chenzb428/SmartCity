package com.chen.smartcity.ui.fragment;

import android.os.Bundle;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;

public class HomeNewFragment extends BaseFragment {

    public static HomeNewFragment newInstance() {
        HomeNewFragment homeNewFragment = new HomeNewFragment();
        //Bundle bundle = new Bundle();
        //bundle.putString(Constants.KEY_HOME_PAGER_TITLE, category.getTitle());
        //bundle.putInt(Constants.KEY_HOME_PAGER_ID, category.getId());
        //homePagerFragment.setArguments(bundle);
        return homeNewFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_new;
    }
}
