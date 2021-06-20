package com.chen.smartcity.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcity.R;
import com.chen.smartcity.model.bean.NewCommentsResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewCommentsAdapter extends RecyclerView.Adapter<NewCommentsAdapter.InnerHolder> {

    private List<NewCommentsResult.RowsBean> mData = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_comment, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewCommentsAdapter.InnerHolder holder, int position) {
        NewCommentsResult.RowsBean item = mData.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NewCommentsResult.RowsBean> result) {
        this.mData.clear();
        this.mData.addAll(result);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView username, content, time;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.comment_cover);
            username = itemView.findViewById(R.id.comment_username);
            content = itemView.findViewById(R.id.comment_content);
            time = itemView.findViewById(R.id.comment_time);
        }

        public void setData(NewCommentsResult.RowsBean item) {
            username.setText(item.getUserName());
            content.setText(item.getContent());
            time.setText(item.getCommentDate() + "");
        }
    }
}
