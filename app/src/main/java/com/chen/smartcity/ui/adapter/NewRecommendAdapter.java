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
import com.chen.smartcity.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewRecommendAdapter extends RecyclerView.Adapter<NewRecommendAdapter.InnerHolder> {

    private List<NewList.RowsBean> mData = new ArrayList<>();
    private int num = (int) (Math.random() * 3 + 1);

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_recommend, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewRecommendAdapter.InnerHolder holder, int position) {
        NewList.RowsBean item = mData.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NewList.RowsBean> result) {
        Collections.shuffle(result);
        this.mData.clear();
        this.mData.addAll(result.subList(0, num));
        LogUtils.d(NewRecommendAdapter.this, "setData === > size" + this.mData.size());
        LogUtils.d(NewRecommendAdapter.this, "setData === > data" + this.mData.toString());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView title;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.comment_recommend_cover);
            title = itemView.findViewById(R.id.comment_recommend_title);
        }

        public void setData(NewList.RowsBean item) {
            Glide.with(itemView.getContext()).load(Constants.BASE_URL + item.getCover()).into(cover);
            title.setText(item.getTitle());
        }
    }
}
