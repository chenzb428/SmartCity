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
import com.chen.smartcitydemo.model.bean.News;
import com.chen.smartcitydemo.util.Constants;
import com.chen.smartcitydemo.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.InnerHolder> {

    private NewsContentAdapter.OnItemClickListener mOnItemClickListener = null;

    private List<News.RowsBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        News.RowsBean bean = mData.get(position);

        Glide.with(holder.itemView.getContext())
                .load(SpUtils.getHost(Constants.BASE_URL) + bean.getCover())
                .into(holder.cover);
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getContent());
        holder.comment.setText("评论：" + bean.getCommentNum());
        holder.time.setText(bean.getCreateTime());

        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position, bean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<News.RowsBean> results) {
        this.mData.clear();
        this.mData.addAll(results);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView cover;
        private TextView title, content, comment, time;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.home_news_cover_iv);
            title = itemView.findViewById(R.id.home_news_title_tv);
            content = itemView.findViewById(R.id.home_news_content_tv);
            comment = itemView.findViewById(R.id.home_news_comment);
            time = itemView.findViewById(R.id.home_news_time);
        }
    }

    public void setOnItemClickListener(NewsContentAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int newsId);
    }
}
