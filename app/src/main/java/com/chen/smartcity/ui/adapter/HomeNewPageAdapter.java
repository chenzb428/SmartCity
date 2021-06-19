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
import com.chen.smartcity.model.bean.NewList;
import com.chen.smartcity.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeNewPageAdapter extends RecyclerView.Adapter<HomeNewPageAdapter.InnerHolder> {

    private List<NewList.RowsBean> mData = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeNewPageAdapter.InnerHolder holder, int position) {
        NewList.RowsBean item = mData.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NewList.RowsBean> result) {
        this.mData.clear();
        this.mData.addAll(result);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView title, comment, time;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.home_new_cover);
            title = itemView.findViewById(R.id.home_new_title);
            comment = itemView.findViewById(R.id.home_new_comment);
            time = itemView.findViewById(R.id.home_new_time);
        }

        public void setData(NewList.RowsBean item) {
            Glide.with(itemView.getContext()).load(Constants.BASE_URL + item.getCover()).into(cover);
            title.setText(item.getTitle());
            comment.setText("评论：" + item.getCommentNum());
            time.setText(item.getCreateTime());
        }
    }
}
