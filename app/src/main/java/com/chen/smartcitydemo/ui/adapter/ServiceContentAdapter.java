package com.chen.smartcitydemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.model.bean.Service;
import com.chen.smartcitydemo.util.Constants;
import com.chen.smartcitydemo.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class ServiceContentAdapter extends RecyclerView.Adapter<ServiceContentAdapter.InnerHolder> {

    private List<Service.RowsBean> mData = new ArrayList<>();

    private OnClickListener mOnClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Service.RowsBean result = mData.get(position);

        Glide.with(holder.itemView.getContext())
                .load(SpUtils.getHost(Constants.BASE_URL) + result.getImgUrl())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.cover);
        holder.name.setText(result.getServiceName());

        holder.itemView.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onItemClick(result.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Service.RowsBean> results) {
        this.mData.clear();
        this.mData.addAll(results);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView name;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.service_cover_iv);
            name = itemView.findViewById(R.id.service_name_tv);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public interface OnClickListener {
        void onItemClick(int serviceId);
    }
}
