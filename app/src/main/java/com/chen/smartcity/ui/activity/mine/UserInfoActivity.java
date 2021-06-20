package com.chen.smartcity.ui.activity.mine;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.AvatarResult;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.model.bean.UserInfoResult;
import com.chen.smartcity.presenter.IUpdateUserInfoPresenter;
import com.chen.smartcity.presenter.IUserInfoPresenter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.IUpdateUserInfoCallback;
import com.chen.smartcity.view.IUserInfoCallback;

public class UserInfoActivity extends BaseActivity implements IUserInfoCallback, IUpdateUserInfoCallback {

    private ImageView coverIv;
    private TextView nameTv;
    private EditText nicknameEt, emailEt, phoneEt, idCardEt, moneyEt, scoreEt;
    private RadioButton sexManRb, sexWomanRb;
    private Button updateBtn;
    private IUserInfoPresenter mUserInfoPresenter;
    private IUpdateUserInfoPresenter mUpdateUserInfoPresenter;
    private String mPicPath;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("个人信息");

        coverIv = findViewById(R.id.user_info_cover);
        nameTv = findViewById(R.id.user_info_name);
        nicknameEt = findViewById(R.id.user_info_nickname);
        emailEt = findViewById(R.id.user_info_email);
        phoneEt = findViewById(R.id.user_info_phone);
        sexManRb = findViewById(R.id.user_info_man);
        sexWomanRb = findViewById(R.id.user_info_woman);
        idCardEt = findViewById(R.id.user_info_id_card);
        moneyEt = findViewById(R.id.user_info_money);
        scoreEt = findViewById(R.id.user_info_score);
        updateBtn = findViewById(R.id.user_info_update);
    }

    @Override
    protected void initPresenter() {
        mUserInfoPresenter = PresenterManager.getInstance().getMinePresenter();
        mUserInfoPresenter.registerViewCallback(this);

        mUpdateUserInfoPresenter = PresenterManager.getInstance().getUpdateUserInfoPresenter();
        mUpdateUserInfoPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        coverIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUpdateUserInfoPresenter != null) {
                    String nickname = nicknameEt.getText().toString();
                    String email = emailEt.getText().toString();
                    String phone = phoneEt.getText().toString();
                    String idCard = idCardEt.getText().toString();
                    String sex = sexManRb.isChecked() ? "0" : "1";
                    mUpdateUserInfoPresenter.updateUserInfo(findByKey("token"), nickname, email, phone, idCard, sex);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        if (mUserInfoPresenter != null) {
            mUserInfoPresenter.getUserInfo(findByKey("token"));
        }
    }

    @Override
    public void onLoadedUserInfo(UserInfoResult result) {
        if (result.getUser().getAvatar() != "") {
            Glide.with(getBaseContext()).load(Constants.BASE_URL + result.getUser().getAvatar()).into(coverIv);
            nameTv.setText(result.getUser().getUserName());
            nicknameEt.setText(result.getUser().getNickName());
            emailEt.setText(result.getUser().getEmail());
            phoneEt.setText(result.getUser().getPhonenumber());
            idCardEt.setText(result.getUser().getIdCard());
            moneyEt.setText(result.getUser().getBalance() + "");
            scoreEt.setText(result.getUser().getScore() + "");
        }
    }

    @Override
    public void onError() {
        ToastUtils.showToast("失败！请重新进入...");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onUpdateUserInfoSuccess(Result result) {
        ToastUtils.showToast(result.getMsg());
    }

    @Override
    public void onUpdateUserAvatarSuccess(AvatarResult result) {
        ToastUtils.showToast(result.getMsg());
        //
    }

    @Override
    public void onUpdateUserAvatarError(String result) {
        ToastUtils.showToast("error! 上传失败！");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            Uri uri = data.getData();
            String col[] = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(uri, col,
                    null, null, null);
            cursor.moveToFirst();
            int colIndex = cursor.getColumnIndex(col[0]);
            mPicPath = cursor.getString(colIndex);

            if (mUpdateUserInfoPresenter != null) {
                mUpdateUserInfoPresenter.updateUserAvatar(findByKey("token"), mPicPath);
            }

            coverIv.setImageBitmap(BitmapFactory.decodeFile(mPicPath));
        }
    }
}