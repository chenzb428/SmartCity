package com.chen.smartcity.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chen.smartcity.R;
import com.chen.smartcity.model.bean.ServerResult;
import com.chen.smartcity.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.InnerHolder> {

    private List<ServerResult.RowsBean> mData =new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_server_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ServerAdapter.InnerHolder holder, int position) {
        ServerResult.RowsBean rowsBean = mData.get(position);
        holder.setData(rowsBean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<ServerResult.RowsBean> result) {
        this.mData.clear();
        this.mData.addAll(result);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView title;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.server_cover);
            title = itemView.findViewById(R.id.server_title);
        }

        public void setData(ServerResult.RowsBean item) {
            Glide.with(itemView.getContext()).load(Constants.BASE_URL + item.getImgUrl()).into(cover);
            title.setText(item.getServiceName());
        }
    }
}
