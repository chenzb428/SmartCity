package com.chen.smartcity.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mGuideContainer;
    private LinearLayout mGuideViewContainer;
    private Button mGuideToHomeBtn;

    private List<ImageView> imageViews;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mGuideContainer = findViewById(R.id.guide_list);
        mGuideViewContainer = findViewById(R.id.guide_view);
        mGuideToHomeBtn = findViewById(R.id.guide_home_btn);

        int ids[] = new int[]{
                R.mipmap.guide_1,
                R.mipmap.guide_1
        };
        imageViews = new ArrayList<>();
        for (int i=0; i<ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
        }

        mGuideContainer.setAdapter(new MyGuideAdapter());
        mGuideContainer.addOnPageChangeListener(this);
        ((View) mGuideViewContainer.getChildAt(0)).setBackgroundResource(R.drawable.shape_guide_viewh);
    }

    @Override
    protected void initListener() {
        mGuideToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == imageViews.size() - 1) {
            mGuideToHomeBtn.setVisibility(View.VISIBLE);
        }else {
            mGuideToHomeBtn.setVisibility(View.GONE);
        }
        for (int i=0; i<mGuideViewContainer.getChildCount(); i++) {
            if (position == i) {
                ((View) mGuideViewContainer.getChildAt(i)).setBackgroundResource(R.drawable.shape_guide_viewh);
            }else {
                ((View) mGuideViewContainer.getChildAt(i)).setBackgroundResource(R.drawable.shape_guide_view);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyGuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViews.get(position));
        }
    }
}