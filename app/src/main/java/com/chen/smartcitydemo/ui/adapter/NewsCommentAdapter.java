package com.chen.smartcitydemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcitydemo.R;
import com.chen.smartcitydemo.model.bean.Comment;

import java.util.ArrayList;
import java.util.List;

public class NewsCommentAdapter extends RecyclerView.Adapter<NewsCommentAdapter.InnerHolder> {

    private List<Comment.RowsBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_comment, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Comment.RowsBean bean = mData.get(position);

        holder.accountTv.setText(bean.getUserName());
        holder.contentTv.setText(bean.getContent());
        holder.timeTv.setText(bean.getCommentDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Comment.RowsBean> results) {
        this.mData.clear();
        this.mData.addAll(results);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        ImageView coverIv;
        TextView accountTv, contentTv, timeTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.comment_cover_iv);
            accountTv = itemView.findViewById(R.id.comment_account_tv);
            contentTv = itemView.findViewById(R.id.comment_content_tv);
            timeTv = itemView.findViewById(R.id.comment_time_tv);
        }
    }
}
