package com.chen.smartcitydemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.smartcitydemo.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceCategoryAdapter extends RecyclerView.Adapter<ServiceCategoryAdapter.InnerHolder> {

    private List<String> mData = new ArrayList<>();

    private int mCurrentPosition = 0;

    private OnClickListener mOnClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_category, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        String result = mData.get(position);

        holder.category.setText(result);

        holder.normalDiver.setVisibility(mCurrentPosition != position ? View.VISIBLE : View.INVISIBLE);
        holder.selectedDiver.setVisibility(mCurrentPosition == position ? View.VISIBLE : View.INVISIBLE);

        holder.itemView.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<String> results) {
        this.mData.clear();
        this.mData.addAll(results);
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int position) {
        this.mCurrentPosition = position;
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView category;
        private View normalDiver, selectedDiver;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category_tv);
            normalDiver = itemView.findViewById(R.id.category_normal_view);
            selectedDiver = itemView.findViewById(R.id.category_selected_diver_view);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
