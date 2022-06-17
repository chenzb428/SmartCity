package com.chen.smartcitydemo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.model.bean.User;
import com.chen.smartcitydemo.presenter.IUploadFilePresenter;
import com.chen.smartcitydemo.presenter.IUserInfoPresenter;
import com.chen.smartcitydemo.util.FileUtils;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.IUploadFileCallback;
import com.chen.smartcitydemo.view.IUserInfoCallback;

public class UserInfoActivity extends BaseActivity implements IUploadFileCallback, IUserInfoCallback {

    private static final int REQUEST_CODE_PHOTO = 1000;
    private static final int PERMISSION_READ_SDCARD = 100;

    private IUploadFilePresenter uploadFilePresenter;

    private ImageView coverIv;
    private TextView usernameTv;
    private EditText nicknameEt;
    private EditText phoneNumEt;
    private EditText emailEt;
    private EditText idCardEt;
    private RadioGroup sexGroup;

    private String imgPath = "";
    private IUserInfoPresenter userInfoPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_info;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        menu.findItem(R.id.menu_text).setTitle("保存");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_text) {
            String nickName = nicknameEt.getText().toString();
            String email = emailEt.getText().toString().trim();
            String phoneNum = phoneNumEt.getText().toString().trim();
            String sex = sexGroup.getCheckedRadioButtonId() == R.id.sex_man_rb ? "0" : "1";
            String idCard = idCardEt.getText().toString().trim();
            User.UserBean user = new User.UserBean("", "", nickName, email, phoneNum, sex, imgPath, idCard, 0, 0);
            userInfoPresenter.updateInfo(user);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        super.initView();
        setUpState(State.SUCCESS);

        if (toolbar != null) {
            toolbar.setTitle("个人信息");
            setSupportActionBar(toolbar);
        }

        coverIv = findViewById(R.id.user_cover_iv);
        usernameTv = findViewById(R.id.user_name_tv);
        nicknameEt = findViewById(R.id.user_nick_name_et);
        phoneNumEt = findViewById(R.id.user_phone_num_et);
        emailEt = findViewById(R.id.user_email_et);
        idCardEt = findViewById(R.id.user_id_card_et);
        sexGroup = findViewById(R.id.sex_layout_rg);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        uploadFilePresenter = PresenterManager.getInstance().getUploadFilePresenter();
        uploadFilePresenter.registerViewCallback(this);
        userInfoPresenter = PresenterManager.getInstance().getUserInfoPresenter();
        userInfoPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        userInfoPresenter.getUserInfo();
    }

    @Override
    protected void initListener() {
        super.initListener();
        coverIv.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // 打开手机中的相册
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, PERMISSION_READ_SDCARD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri photoUri;
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK && data != null && (photoUri = data.getData()) != null) {
                LogUtils.d(this, "onActivityResult: photoUri -> " + photoUri);
                Glide.with(this).load(photoUri).into(coverIv);

                String path = FileUtils.getRealPath(this, photoUri);
                LogUtils.d(this, "onActivityResult: path -> " + path);
                uploadFilePresenter.uploadFile(path);
            }
        }
    }

    @Override
    public void onUploadFileSuccess(String url) {
        setUpState(State.SUCCESS);
        toast("图片上传成功");
        this.imgPath = url;
    }

    @Override
    public void onUserInfoLoadedSuccess(User.UserBean user) {
        setUpState(State.SUCCESS);

        Glide.with(this)
                .load(user.getAvatar())
                .error(R.mipmap.ic_launcher_round)
                .into(coverIv);
        usernameTv.setText(user.getUserName());
        nicknameEt.setText(user.getNickName());
        phoneNumEt.setText(user.getPhonenumber());
        emailEt.setText(user.getEmail());
        idCardEt.setText(user.getIdCard());
        if (user.getSex().equals("0")) {
            ((RadioButton) sexGroup.getChildAt(0)).setChecked(true);
        } else {
            ((RadioButton) sexGroup.getChildAt(1)).setChecked(true);
        }
    }

    @Override
    public void onUserInfoLoadedFailure(String msg) {
        setUpState(State.SUCCESS);
        toast("用户信息已过期，请重新登录！");
    }

    @Override
    public void onUpdateUserInfoSuccess(String msg) {
        setUpState(State.SUCCESS);
        toast(msg);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError(String msg) {
        setUpState(State.ERROR);
        toast(msg);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (uploadFilePresenter != null) {
            uploadFilePresenter.unregisterViewCallback();
            uploadFilePresenter = null;
        }
        if (userInfoPresenter != null) {
            userInfoPresenter.unregisterViewCallback();
            userInfoPresenter = null;
        }
        if (imgPath != null) {
            imgPath = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (coverIv != null) coverIv = null;
        if (usernameTv != null) coverIv = null;
        if (nicknameEt != null) coverIv = null;
        if (phoneNumEt != null) coverIv = null;
        if (emailEt != null) coverIv = null;
        if (idCardEt != null) coverIv = null;
        if (sexGroup != null) coverIv = null;
    }
}