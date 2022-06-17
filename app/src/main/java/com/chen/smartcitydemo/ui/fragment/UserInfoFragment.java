package com.chen.smartcitydemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseFragment;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.presenter.ILoginOutPresenter;
import com.chen.smartcitydemo.presenter.IUserInfoPresenter;
import com.chen.smartcitydemo.ui.activity.LoginActivity;
import com.chen.smartcitydemo.ui.activity.FeedBackActivity;
import com.chen.smartcitydemo.ui.activity.OrderListActivity;
import com.chen.smartcitydemo.ui.activity.UpdatePasswordActivity;
import com.chen.smartcitydemo.ui.activity.UserInfoActivity;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.ILoginOutCallback;
import com.chen.smartcitydemo.view.IUserInfoCallback;

public class UserInfoFragment extends BaseFragment implements IUserInfoCallback, ILoginOutCallback {

    private static final int REQUEST_LOGIN_CODE = 1000;

    private IUserInfoPresenter userInfoPresenter;

    private Button loginBtn;
    private Button loginOutBtn;

    private ImageView userCoverIv;
    private TextView userNameTv;

    private CardView settingsLayout;
    private RelativeLayout userInfoLayout;
    private RelativeLayout orderListLayout;
    private RelativeLayout updatePasswordLayout;
    private RelativeLayout meetLayout;
    private ILoginOutPresenter loginOutPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        userCoverIv = view.findViewById(R.id.mine_user_cover_iv);
        userNameTv = view.findViewById(R.id.mine_user_name_tv);

        loginBtn = view.findViewById(R.id.mine_login_btn);

        settingsLayout = view.findViewById(R.id.mine_settings_layout);
        userInfoLayout = view.findViewById(R.id.mine_user_info_layout);
        orderListLayout = view.findViewById(R.id.mine_order_list_layout);
        updatePasswordLayout = view.findViewById(R.id.mine_update_password_layout);
        meetLayout = view.findViewById(R.id.mine_meet_layout);
        loginOutBtn = view.findViewById(R.id.mine_login_out_btn);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        userInfoPresenter = PresenterManager.getInstance().getUserInfoPresenter();
        userInfoPresenter.registerViewCallback(this);
        loginOutPresenter = PresenterManager.getInstance().getLoginOutPresenter();
        loginOutPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (!SpUtils.getToken().equals("")) {
            userInfoPresenter.getUserInfo();
        } else {
            setUpState(State.SUCCESS);
            settingsLayout.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        loginBtn.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), REQUEST_LOGIN_CODE));

        userInfoLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), UserInfoActivity.class)));

        orderListLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), OrderListActivity.class)));

        updatePasswordLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), UpdatePasswordActivity.class)));

        meetLayout.setOnClickListener(v -> startActivity(new Intent(getContext(), FeedBackActivity.class)));

        loginOutBtn.setOnClickListener(v -> { loginOutPresenter.loginOut(); });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!SpUtils.getToken().equals("")) {
            userInfoPresenter.getUserInfo();
        }
    }

    @Override
    public void onUserInfoLoadedSuccess(User.UserBean user) {
        setUpState(State.SUCCESS);

        settingsLayout.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.GONE);

        Glide.with(this)
                .load(user.getAvatar())
                .error(R.mipmap.ic_launcher_round)
                .into(userCoverIv);
        userNameTv.setText(user.getUserName());
    }

    @Override
    public void onUserInfoLoadedFailure(String msg) {
        setUpState(State.SUCCESS);

        toast("用户信息已过期，请重新登录！");
        settingsLayout.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUpdateUserInfoSuccess(String msg) {
        toast(msg);
    }

    @Override
    public void onLoginOutSuccess(String msg) {
        setUpState(State.SUCCESS);

        toast(msg);
        settingsLayout.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.SUCCESS);

        settingsLayout.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String msg) {
        setUpState(State.SUCCESS);

        toast(msg);
        settingsLayout.setVisibility(View.GONE);
        loginBtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (userInfoPresenter != null) {
            userInfoPresenter.unregisterViewCallback();
            userInfoPresenter = null;
        }
        if (loginOutPresenter != null) {
            loginOutPresenter.unregisterViewCallback();
            loginOutPresenter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (settingsLayout != null) settingsLayout = null;
        if (userCoverIv != null) userCoverIv = null;
        if (userNameTv != null) userNameTv = null;
        if (loginBtn != null) loginBtn = null;
        if (userInfoLayout != null) userInfoLayout = null;
        if (orderListLayout != null) orderListLayout = null;
        if (updatePasswordLayout != null) updatePasswordLayout = null;
        if (meetLayout != null) meetLayout = null;
        if (loginOutBtn != null) loginOutBtn = null;
    }
}
