package com.chen.smartcity.ui.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.base.BaseActivity;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.ui.adapter.ServerAdapter;
import com.chen.smartcity.utils.LogUtils;

import java.util.List;


public class ServerSearchActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_server_search;
    }

    @Override
    protected void initView() {
        List<ServerResult.RowsBean> server = (List<ServerResult.RowsBean>) getIntent().getSerializableExtra("server");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
        ServerAdapter serverAdapter = new ServerAdapter();
        mRecyclerView.setAdapter(serverAdapter);
        serverAdapter.setData(server);
    }
}