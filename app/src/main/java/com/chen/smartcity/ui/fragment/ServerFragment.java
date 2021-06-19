package com.chen.smartcity.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseFragment;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.presenter.IServerPresenter;
import com.chen.smartcity.ui.activity.ServerSearchActivity;
import com.chen.smartcity.ui.adapter.ServerAdapter;
import com.chen.smartcity.ui.adapter.ServerCategoryAdapter;
import com.chen.smartcity.utils.PresenterManager;
import com.chen.smartcity.view.IServerCallback;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ServerFragment extends BaseFragment implements IServerCallback, ServerCategoryAdapter.OnItemClickListener {

    private IServerPresenter mServerPresenter;

    private RecyclerView mCategoryList, mContentList;
    private ServerCategoryAdapter mCategoryAdapter;
    private ServerAdapter mServerAdapter;
    private List<ServerResult.RowsBean> serverData = new ArrayList<>();
    private EditText searchEd;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_server;
    }

    @Override
    protected View loadRootView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.base_top_search_layout, container, false);
    }

    @Override
    protected void initView(View rootView) {
        mCategoryList = rootView.findViewById(R.id.server_category_list);
        mContentList = rootView.findViewById(R.id.server_content_list);

        mCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryAdapter = new ServerCategoryAdapter();
        mCategoryList.setAdapter(mCategoryAdapter);

        mContentList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mServerAdapter = new ServerAdapter();
        mContentList.setAdapter(mServerAdapter);

        searchEd = rootView.findViewById(R.id.search_et);
        searchEd.setHint("请输入你想搜...的服务");
    }

    @Override
    protected void initPresenter() {
        mServerPresenter = PresenterManager.getInstance().getServerPresenter();
        mServerPresenter.registerViewCallback(this);
    }

    @Override
    protected void initListener() {
        mCategoryAdapter.setOnItemClickListener(this);

        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && serverData.size() > 0) {
                    String keyword = v.getText().toString().trim();
                    if (TextUtils.isEmpty(keyword)) {
                        return false;
                    }
                    List<ServerResult.RowsBean> result = search(serverData, keyword);
                    Intent intent = new Intent(getContext(), ServerSearchActivity.class);
                    intent.putExtra("server", (Serializable) result);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private List search(List<ServerResult.RowsBean> bean, String search) {
        List<ServerResult.RowsBean> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(search);
        for (int i=0; i<bean.size(); i++) {
            Matcher matcher = pattern.matcher(bean.get(i).getServiceName());
            if (matcher.find()) {
                result.add(bean.get(i));
            }
        }
        return result;
    }

    @Override
    protected void loadData() {
        mServerPresenter.getServer();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLoadedServer(List<ServerResult.RowsBean> result) {
        this.serverData = result;

        List<ServerResult.RowsBean> categoryData = result
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ServerResult.RowsBean :: getServiceType))), ArrayList::new));
        mCategoryAdapter.setData(categoryData);

        List<ServerResult.RowsBean> contentData = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getServiceType().equals(categoryData.get(0).getServiceType())) {
                contentData.add(result.get(i));
            }
        }
        mServerAdapter.setData(contentData);

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
    public void onCategoryItemClick(ServerResult.RowsBean rowsBean) {
        List<ServerResult.RowsBean> contentData = new ArrayList<>();
        for (int i = 0; i < serverData.size(); i++) {
            if (serverData.get(i).getServiceType().equals(rowsBean.getServiceType())) {
                contentData.add(serverData.get(i));
            }
        }
        mServerAdapter.setData(contentData);
    }
}
