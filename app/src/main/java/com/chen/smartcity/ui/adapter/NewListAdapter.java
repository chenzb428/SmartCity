package com.chen.smartcity.ui.adapter;

import android.content.Intent;
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
import com.chen.smartcity.ui.activity.NewActivity;
import com.chen.smartcity.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.InnerHolder> {

    private List<NewList.RowsBean> mData = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_list, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewListAdapter.InnerHolder holder, int position) {
        NewList.RowsBean item = mData.get(position);
        holder.setData(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), NewActivity.class);
                intent.putExtra("newId", item.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
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

        private TextView title, readNum, likeNum, time;
        private ImageView cover;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.new_list_title);
            readNum = itemView.findViewById(R.id.new_list_read_num);
            likeNum = itemView.findViewById(R.id.new_list_like_num);
            time = itemView.findViewById(R.id.new_list_time);
            cover = itemView.findViewById(R.id.new_list_cover);
        }

        public void setData(NewList.RowsBean item) {
            Glide.with(itemView.getContext()).load(Constants.BASE_URL + item.getCover()).into(cover);
            title.setText(item.getTitle());
            readNum.setText("阅读" + item.getReadNum());
            likeNum.setText("点赞" + item.getLikeNum());
            time.setText(item.getCreateTime());
        }
    }
}
