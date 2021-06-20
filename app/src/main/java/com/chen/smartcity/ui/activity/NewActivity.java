package com.chen.smartcity.ui.activity;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.NewCommentsResult;
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.model.bean.NewResult;
import com.chen.smartcity.model.bean.Result;
import com.chen.smartcity.presenter.IAllNewsPresenter;
import com.chen.smartcity.presenter.INewInfoPresenter;
import com.chen.smartcity.ui.adapter.NewCommentsAdapter;
import com.chen.smartcity.ui.adapter.NewRecommendAdapter;
import com.chen.smartcity.utils.Constants;
import com.chen.smartcity.utils.LogUtils;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.utils.ToastUtils;
import com.chen.smartcity.view.IAllNewsCallback;
import com.chen.smartcity.view.INewInfoCallback;

import java.util.List;


public class NewActivity extends BaseActivity implements INewInfoCallback, IAllNewsCallback {

    private int mNewId;
    private INewInfoPresenter mNewInfoPresenter;

    private TextView titleTv, contentTv, viewNumTv, likeNumTv, timeTv, noCommentDataTipTv;
    private ImageView coverIv;
    private EditText commentEt;
    private Button commentBt;
    private RecyclerView mRecommendList, mCommentList;
    private NewCommentsAdapter mCommentsAdapter;
    private IAllNewsPresenter mAllNewsPresenter;
    private NewRecommendAdapter mRecommendAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.mipmap.back);
        setTitle("新闻详情");

        mNewId = getIntent().getIntExtra("newId", 0);

        titleTv = findViewById(R.id.new_title);
        contentTv = findViewById(R.id.new_content);
        viewNumTv = findViewById(R.id.new_viewNum);
        likeNumTv = findViewById(R.id.new_likeNum);
        timeTv = findViewById(R.id.new_time);
        coverIv = findViewById(R.id.new_cover);
        commentEt = findViewById(R.id.new_comment_et);
        commentBt = findViewById(R.id.new_comment_ok);
        mRecommendList = findViewById(R.id.new_recommend_list);
        mCommentList = findViewById(R.id.new_comment_list);
        noCommentDataTipTv = findViewById(R.id.comment_no_data_tip);

        mCommentList.setLayoutManager(new LinearLayoutManager(this));
        mCommentsAdapter = new NewCommentsAdapter();
        mCommentList.setAdapter(mCommentsAdapter);

        mRecommendList.setLayoutManager(new LinearLayoutManager(this));
        mRecommendAdapter = new NewRecommendAdapter();
        mRecommendList.setAdapter(mRecommendAdapter);
    }

    @Override
    protected void initPresenter() {
        mNewInfoPresenter = PresenterManager.getInstance().getNewInfoPresenter();
        mNewInfoPresenter.registerViewCallback(this);

        mAllNewsPresenter = PresenterManager.getInstance().getAllNewsPresenter();
        mAllNewsPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNewInfoPresenter != null) {
                    String content = commentEt.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showToast("不能为空");
                        return;
                    }
                    mNewInfoPresenter.doComment(findByKey("token"), mNewId, content);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        LogUtils.d(this, "NewActivity newId === > " + mNewId);
        mNewInfoPresenter.getNewInfo(mNewId);
        mNewInfoPresenter.getNewComments(mNewId);
        mAllNewsPresenter.getAllNews();
    }

    @Override
    public void onLoadedNewInfo(NewResult result) {
        NewResult.DataBean data = result.getData();
        Glide.with(getBaseContext()).load(Constants.BASE_URL + data.getCover()).into(coverIv);
        titleTv.setText(data.getTitle());
        contentTv.setText(Html.fromHtml(data.getContent()));
        viewNumTv.setText("阅读：" + data.getReadNum());
        likeNumTv.setText("点赞：" + data.getLikeNum());
        timeTv.setText(data.getCreateTime());
    }

    @Override
    public void onLoadedNewComments(List<NewCommentsResult.RowsBean> result) {
        LogUtils.d(this, "onLoadedNewComments result === > " + result.toString());
        noCommentDataTipTv.setVisibility(View.GONE);
        mCommentsAdapter.setData(result);
    }

    @Override
    public void onLoadingNewComments() {

    }

    @Override
    public void onLoadedNewCommentsError() {
        ToastUtils.showToast("暂无新闻评论...");
    }

    @Override
    public void onLoadedNewCommentsEmpty() {
        ToastUtils.showToast("暂无新闻评论...");
    }

    @Override
    public void onDoComment(Result result) {
        ToastUtils.showToast(result.getMsg());
        this.mNewInfoPresenter.getNewComments(mNewId);
    }

    @Override
    public void onDoCommentError() {
        ToastUtils.showToast("发表新闻评论失败...");
    }

    @Override
    public void onLoadedAllNews(List<NewList.RowsBean> result) {
        mRecommendAdapter.setData(result);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    protected void release() {
        super.release();
        if (mNewInfoPresenter != null) {
            mNewInfoPresenter.unregisterViewCallback(this);
        }
        if (mAllNewsPresenter != null) {
            mAllNewsPresenter.unregisterViewCallback(this);
        }
    }
}