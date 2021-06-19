package com.chen.smartcity.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.model.bean.DingdanResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DingdanAdapter extends RecyclerView.Adapter<DingdanAdapter.InnerHolder> {

    private List<DingdanResult.RowsBean> mData = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dingdan, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DingdanAdapter.InnerHolder holder, int position) {
        DingdanResult.RowsBean item = mData.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<DingdanResult.RowsBean> result) {
        this.mData.clear();
        this.mData.addAll(result);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView id, name, amount, orderStatus, orderTypeName, time;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.dingdan_id);
            name = itemView.findViewById(R.id.dingdan_name);
            amount = itemView.findViewById(R.id.dingdan_amount);
            orderStatus = itemView.findViewById(R.id.dingdan_orderStatus);
            orderTypeName = itemView.findViewById(R.id.dingdan_orderTypeName);
            time = itemView.findViewById(R.id.dingdan_time);
        }

        public void setData(DingdanResult.RowsBean item) {
            id.setText("订单号：" + item.getId());
            name.setText("商家：" + item.getName());
            amount.setText("订单金额：" + item.getAmount());
            orderStatus.setText("订单状态：" + item.getOrderStatus());
            orderTypeName.setText("订单类型：" + item.getOrderTypeName());
            time.setText("时间：" + item.getId());
        }
    }
}
