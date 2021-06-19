package com.chen.smartcity.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.model.bean.ServerResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServerCategoryAdapter extends RecyclerView.Adapter<ServerCategoryAdapter.InnerHolder> {

    private List<ServerResult.RowsBean> mData = new ArrayList();
    private int mCurrentPosition = 0;
    private OnItemClickListener mItemClickListener = null;

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_server_category, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ServerCategoryAdapter.InnerHolder holder, int position) {
        TextView title = holder.itemView.findViewById(R.id.server_category_title);
        title.setText(mData.get(position).getServiceType());

        if (position == mCurrentPosition) {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.color_page_bg));
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.white));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null && mCurrentPosition != position) {
                    mCurrentPosition = position;
                    mItemClickListener.onCategoryItemClick(mData.get(position));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<ServerResult.RowsBean> categoryData) {
        this.mData.clear();
        this.mData.addAll(categoryData);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onCategoryItemClick(ServerResult.RowsBean rowsBean);
    }
}
