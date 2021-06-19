package com.chen.smartcity.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.ui.activity.LoginActivity;
import com.chen.smartcity.ui.activity.MainActivity;
import com.chen.smartcity.utils.LogUtils;

public class MineFragment extends BaseFragment {

    private LinearLayout settingLL;
    private Button loginBtn;
    private TextView loginOutBtn;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
        settingLL = rootView.findViewById(R.id.mine_setting);
        loginBtn = rootView.findViewById(R.id.mine_login);
        loginOutBtn = rootView.findViewById(R.id.mine_logout);

        if (findByKey("token") != "") {
            settingLL.setVisibility(View.VISIBLE);
            loginOutBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d(this, "login");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        loginOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeByKey("token");
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (findByKey("token") != "") {
            settingLL.setVisibility(View.VISIBLE);
            loginOutBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }
    }
}
