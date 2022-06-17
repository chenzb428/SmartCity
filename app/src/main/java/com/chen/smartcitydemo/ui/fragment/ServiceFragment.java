package com.chen.smartcitydemo.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.base.BaseFragment;
import com.chen.smartcitydemo.model.bean.Service;
import com.chen.smartcitydemo.presenter.IServicePresenter;
import com.chen.smartcitydemo.ui.activity.MainActivity;
import com.chen.smartcitydemo.ui.activity.SearchActivity;
import com.chen.smartcitydemo.ui.adapter.ServiceCategoryAdapter;
import com.chen.smartcitydemo.ui.adapter.ServiceContentAdapter;
import com.chen.smartcitydemo.util.LogUtils;
import com.chen.smartcitydemo.util.PresenterManager;
import com.chen.smartcitydemo.view.IServiceCallback;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends BaseFragment implements IServiceCallback, ServiceCategoryAdapter.OnClickListener {

    private IServicePresenter servicePresenter;

    /**
     * 服务分类
     */
    private RecyclerView categoryListView;
    private ServiceContentAdapter serviceContentAdapter;

    /**
     * 服务分类下的全部服务
     */
    private RecyclerView contentListView;
    private ServiceCategoryAdapter serviceCategoryAdapter;

    /**
     * 用于保存服务分类名称
     */
    private List<String> serviceTypes;

    /**
     * 用于保存服务分类下的全部服务
     */
    private List<Service.RowsBean> services;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        categoryListView = view.findViewById(R.id.service_category_list_view);
        categoryListView.setLayoutManager(new LinearLayoutManager(getContext()));
        serviceCategoryAdapter = new ServiceCategoryAdapter();
        categoryListView.setAdapter(serviceCategoryAdapter);

        contentListView = view.findViewById(R.id.service_content_list_view);
        contentListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        serviceContentAdapter = new ServiceContentAdapter();
        contentListView.setAdapter(serviceContentAdapter);
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        servicePresenter = PresenterManager.getInstance().getServicePresenter();
        servicePresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        serviceTypes = new ArrayList<>();
        services = new ArrayList<>();

        servicePresenter.getAll();
    }

    @Override
    protected void initListener() {
        super.initListener();
        serviceCategoryAdapter.setOnClickListener(this);

        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        ((MainActivity) getActivity()).getSearchView().setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        ((MainActivity) getActivity()).getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.startActivity(getContext(), query, SearchActivity.KEY_SERVICE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onLoadedAllServiceSuccess(List<Service.RowsBean> results) {
        setUpState(State.SUCCESS);

        services = results;

        // 在所有服务中筛选服务分类
        serviceTypes = new ArrayList<>();
        for (Service.RowsBean service : results) {
            if (!serviceTypes.contains(service.getServiceType())) {
                serviceTypes.add(service.getServiceType());
            }
        }
        serviceCategoryAdapter.setData(serviceTypes);

        onItemClick(0);
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
        toast(msg);
    }

    @Override
    public void onItemClick(int position) {
        serviceCategoryAdapter.setCurrentPosition(position);

        // 根据已筛选好的服务分类，来筛选相应服务
        List<Service.RowsBean> data = new ArrayList<>();
        for (int i = 0; i < services.size(); i++) {
            if (serviceTypes.get(position).equals(services.get(i).getServiceType())) {
                Service.RowsBean bean = new Service.RowsBean(services.get(i).getServiceName(), services.get(i).getImgUrl());
                data.add(bean);
            }
        }
        LogUtils.d(this, "onItemClick: services -> " + data.toString());
        serviceContentAdapter.setData(data);
    }

    @Override
    protected void releaseInterface() {
        super.releaseInterface();
        if (servicePresenter != null) {
            servicePresenter.unregisterViewCallback();
            servicePresenter = null;
        }
        if (serviceContentAdapter != null) {
            contentListView.setAdapter(null);
            serviceContentAdapter = null;
        }
        if (serviceCategoryAdapter != null) {
            categoryListView.setAdapter(null);
            serviceCategoryAdapter = null;
        }
    }

    @Override
    protected void releaseUI() {
        super.releaseUI();
        if (categoryListView != null) categoryListView = null;
        if (contentListView != null) contentListView = null;
    }
}
