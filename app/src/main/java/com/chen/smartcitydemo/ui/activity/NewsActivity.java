package com.chen.smartcitydemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseActivity;
import com.chen.smartcitydemo.model.bean.Comment;
import com.chen.smartcitydemo.model.bean.NewsInfo;
import com.chen.smartcitydemo.presenter.INewsInfoPresenter;
import com.chen.smartcitydemo.ui.adapter.NewsCommentAdapter;
import com.chen.smartcitydemo.util.Constants;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.util.SpUtils;
import com.chen.smartcitydemo.view.INewsInfoCallback;

import java.util.Collections;
import java.util.List;

public class NewsActivity extends BaseActivity implements INewsInfoCallback {

    private static final String NEWS_ID = "news_id";

    private INewsInfoPresenter newsInfoPresenter;
    private TextView titleTv;
    private ImageView coverIv;
    private WebView contentWv;
    private RecyclerView commentListView;

    private NewsCommentAdapter newsCommentAdapter;
    private int newsId;
    private EditText commentEt;
    private Button commentBt;

    public static void startActivity(Context context, int newsId) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra(NEWS_ID, newsId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_news;
    }

    @Override
    protected View loadRootView() {
        return getView(R.layout.layout_base_toolbar_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        menu.findItem(R.id.menu_text).setTitle("点赞");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_text) {
            newsInfoPresenter.doNewsLike(newsId);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        super.initView();

        if (toolbar != null) {
            toolbar.setTitle("新闻详情");
            setSupportActionBar(toolbar);
        }

        titleTv = findViewById(R.id.news_title_tv);
        coverIv = findViewById(R.id.news_cover_iv);
        contentWv = findViewById(R.id.news_content_wv);

        commentListView = findViewById(R.id.news_comment_list_view);
        commentListView.setLayoutManager(new LinearLayoutManager(this));
        newsCommentAdapter = new NewsCommentAdapter();
        commentListView.setAdapter(newsCommentAdapter);

        commentEt = findViewById(R.id.news_comment_et);
        commentBt = findViewById(R.id.news_comment_btn);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        newsInfoPresenter = PresenterManager.getInstance().getNewsInfoPresenter();
        newsInfoPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        newsId = getIntent().getIntExtra(NEWS_ID, -1);
        if (newsId != -1) {
            newsInfoPresenter.getNewsInfo(newsId);
            newsInfoPresenter.getCommentList(newsId);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        commentBt.setOnClickListener(v -> {
            String content = commentEt.getText().toString();

            newsInfoPresenter.doComment(newsId, content);
        });
    }

    @Override
    public void onLoadedNewsInfoSuccess(NewsInfo.DataBean newsInfo) {
        setUpState(State.SUCCESS);

        titleTv.setText(newsInfo.getTitle());
        Glide.with(this).load(SpUtils.getHost(Constants.BASE_URL) + newsInfo.getCover()).into(coverIv);
        contentWv.getSettings().setJavaScriptEnabled(true);
        String varJs =" <script type='text/javascript'> window.onload = function(){var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
        contentWv.loadDataWithBaseURL(Constants.BASE_URL2, varJs + newsInfo.getContent(), "text/html", "UTF-8", null);
    }

    @Override
    public void onNewsLikeSuccess(String msg) {
        toast("新闻点赞成功！");
    }

    @Override
    public void onLoadedCommentSuccess(List<Comment.RowsBean> results) {
        if (results != null) {
            LogUtils.d(this, "onLoadedCommentSuccess: results -> " + results.toString());
            Collections.reverse(results);
            newsCommentAdapter.setData(results);
        }
    }

    @Override
    public void onNewsCommentSuccess(String msg) {
        toast("发表成功！");
        newsInfoPresenter.getCommentList(newsId);
    }

    @Override
    public void onNewsCommentLikeSuccess(String msg) {
        toast("评论点赞成功！");
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
    public void onError(String msg) {
        setUpState(State.ERROR);
        toast(msg);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (newsInfoPresenter != null) {
            newsInfoPresenter.unregisterViewCallback();
            newsInfoPresenter = null;
        }
        if (newsCommentAdapter != null) {
            commentListView.setAdapter(null);
            newsCommentAdapter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (titleTv != null) titleTv = null;
        if (coverIv != null) coverIv = null;
        if (contentWv != null) contentWv = null;
        if (commentListView != null) commentListView = null;
        if (commentEt != null) commentEt = null;
        if (commentBt != null) commentBt = null;
    }
}