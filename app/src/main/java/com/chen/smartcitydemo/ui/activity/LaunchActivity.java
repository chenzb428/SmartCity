package com.chen.smartcitydemo.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private LinearLayout guideLayout;
    private Button homeBtn;
    private TextView netWorkTv;

    private List<ImageView> imageViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flag);
        // 兼容水滴屏、开孔屏、刘海屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }

        setContentView(R.layout.activity_launch);

        // 判断是否是第一次启动
        if (!SpUtils.isFirstStart()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        initView();
        initData(savedInstanceState);
        initListener();
    }

    private void initView() {
        viewPager = findViewById(R.id.launch_vp);
        guideLayout = findViewById(R.id.launch_guide_view_layout);
        homeBtn = findViewById(R.id.launch_home_btn);
        netWorkTv = findViewById(R.id.launch_net_work_tv);

        ((View) guideLayout.getChildAt(0)).setBackgroundResource(R.drawable.shape_guide_view_current);
    }

    private void initData(Bundle savedInstanceState) {
        // 准备引导图片数据
        int[] ids = {
                R.drawable.guide,
                R.drawable.guide
        };

        imageViews = new ArrayList<>();
        for (int id : ids) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(id);
            imageViews.add(imageView);
        }

        viewPager.setAdapter(new PagerAdapter() {
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
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }

    protected void initListener() {
        /**
         * ViewPager 页面切换监听事件
         */
        viewPager.setOnPageChangeListener(this);

        /**
         * 进入主页点击监听事件
         */
        homeBtn.setOnClickListener(v -> {
            SpUtils.putData(SpUtils.IS_FIRST_START, false);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        /**
         * 网络设置点击监听事件
         */
        netWorkTv.setOnClickListener(v -> {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_host, null);

            EditText ipEt = view.findViewById(R.id.host_ip_et);
            EditText portEt = view.findViewById(R.id.host_port_et);
            ipEt.setText("124.93.196.45");
            portEt.setText("10001");

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("网络设置")
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .setNegativeButton(android.R.string.cancel, null);

            AlertDialog dialog = builder.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(a -> {
                String ipInput = ipEt.getText().toString().trim();
                String portInput = portEt.getText().toString().trim();

                if (!TextUtils.isEmpty(ipInput) && !TextUtils.isEmpty(portInput)) {
                    // 校检端口号输入是否合法
                    Integer port = -1;

                    try {
                        port = Integer.parseInt(portInput);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        toast("请输入正确的端口号");
                        return;
                    }

                    if (port < 0 || port > 65535) {
                        toast("端口输入错误");
                        return;
                    }

                    // 校检ip地址输入是否合法
                    if (!ipInput.matches("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$")) {
                        toast("Ip地址格式不正确");
                        return;
                    }

                    //保存网络地址
                    String host = "http://" + ipInput + ":" + portInput + "/";
                    Log.d("SplashActivity", "SaveHost:" + host);

                    SpUtils.setHost(host);
                    toast("网络设置成功！");
                    dialog.dismiss();
                } else {
                    toast("ip地址或端口号不能为空");
                }
            });
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageViews.size(); i++) {
            if (position == i) {
                ((View) guideLayout.getChildAt(i)).setBackgroundResource(R.drawable.shape_guide_view_current);
                homeBtn.setVisibility(View.VISIBLE);
                netWorkTv.setVisibility(View.VISIBLE);
            } else {
                ((View) guideLayout.getChildAt(i)).setBackgroundResource(R.drawable.shape_guide_view_normal);
                homeBtn.setVisibility(View.GONE);
                netWorkTv.setVisibility(View.GONE);
            }
        }

        if (position == imageViews.size() - 1) {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void toast(String msg) {
        runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPager != null) {
            viewPager.setAdapter(null);
            viewPager = null;
        }
        if (guideLayout != null) guideLayout = null;
        if (homeBtn != null) homeBtn = null;
        if (netWorkTv != null) netWorkTv = null;
        if (imageViews != null) {
            imageViews.clear();
            imageViews = null;
        }
    }
}