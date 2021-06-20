package com.chen.smartcity.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.model.bean.NewCategory;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.presenter.IAllNewsPresenter;
import com.chen.smartcity.presenter.INewPresenter;
import com.chen.smartcity.ui.activity.NewActivity;
import com.chen.smartcity.ui.adapter.NewListAdapter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.SizeUtils;
import com.chen.smartcity.view.IAllNewsCallback;
import com.chen.smartcity.view.INewCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewFragment extends BaseFragment implements IAllNewsCallback, INewCallback {

    private IAllNewsPresenter mAllNewsPresenter;

    private Banner mBanner;
    private TabLayout mTabLayout;
    private ViewPager2 mCategoryContent;
    private RecyclerView mNewList;
    private NewListAdapter mNewListAdapter;
    private INewPresenter mNewPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView(View rootView) {
        mBanner = rootView.findViewById(R.id.new_banner);
        mTabLayout = rootView.findViewById(R.id.new_category);
        mCategoryContent = rootView.findViewById(R.id.new_category_content);
        mNewList = rootView.findViewById(R.id.new_list);

        mNewList.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewListAdapter = new NewListAdapter();
        mNewList.setAdapter(mNewListAdapter);
        mNewList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                int size = SizeUtils.dip2px(getContext(), 6);
                outRect.top = size;
                outRect.bottom = size;
                outRect.left = size;
                outRect.right = size;
            }
        });
    }

    @Override
    protected void initPresenter() {
        mAllNewsPresenter = PresenterManager.getInstance().getAllNewsPresenter();
        mAllNewsPresenter.registerViewCallback(this);

        mNewPresenter = PresenterManager.getInstance().getNewPresenter();
        mNewPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        mAllNewsPresenter.getAllNews();
        mNewPresenter.getNewCategory();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLoadedAllNews(List<NewList.RowsBean> result) {
        mBanner.addBannerLifecycleObserver(getViewLifecycleOwner())
                .setIndicator(new CircleIndicator(getContext()))
                .setAdapter(new BannerImageAdapter<NewList.RowsBean>(result) {
                    @Override
                    public void onBindView(BannerImageHolder holder, NewList.RowsBean rowsBean, int i, int i1) {
                        Glide.with(getContext()).load(Constants.BASE_URL + result.get(i).getCover()).into(holder.imageView);
                    }

                    @Override
                    public int getRealCount() {
                        return 5;
                    }
                })
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object o, int i) {
                        Intent intent = new Intent(getContext(), NewActivity.class);
                        intent.putExtra("newId", result.get(i).getId());
                        getContext().startActivity(intent);
                    }
                });
        List<NewList.RowsBean> newList = result;
        newList.sort(Comparator.comparing(NewList.RowsBean :: getCreateTime, Comparator.nullsFirst(String :: compareTo)));
        Collections.reverse(newList);
        mNewListAdapter.setData(newList);

        setUpState(State.SUCCESS);
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
        super.release();
        if (mAllNewsPresenter != null) {
            mAllNewsPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onLoadedNewCategory(List<NewCategory.DataBean> category) {
        mCategoryContent.setAdapter(new FragmentStateAdapter(getChildFragmentManager(), getLifecycle()) {
            @NonNull
            @NotNull
            @Override
            public Fragment createFragment(int position) {
                return NewCategoryContentFragment.newInstance(category.get(position));
            }

            @Override
            public int getItemCount() {
                return category.size();
            }
        });
        new TabLayoutMediator(mTabLayout, mCategoryContent, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(category.get(position).getName());
            }
        }).attach();
    }
}
