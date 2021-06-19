package com.chen.smartcity.ui.activity.mine;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IMeetPresenter;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.IMeetCallback;

public class MeetActivity extends BaseActivity implements IMeetCallback {

    private EditText contentEt, titleEt;
    private Button meetBtn;
    private IMeetPresenter mMeetPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_meet;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("意见反馈");

        titleEt = findViewById(R.id.meet_title_et);
        contentEt = findViewById(R.id.meet_content_et);
        meetBtn = findViewById(R.id.meet_ok);
    }

    @Override
    protected void initPresenter() {
        mMeetPresenter = PresenterManager.getInstance().getMeetPresenter();
        mMeetPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        meetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEt.getText().toString();
                String content = contentEt.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    ToastUtils.showToast("不能为空！");
                } else if (mMeetPresenter != null) {
                    mMeetPresenter.doMeet(findByKey("token"), title, content);
                }
            }
        });
    }

    @Override
    public void onLoadedMeet(Result result) {
        ToastUtils.showToast(result.getMsg());
    }

    @Override
    public void onError() {
        ToastUtils.showToast("提交失败！请重试...");
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}