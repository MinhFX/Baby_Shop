package com.example.giohang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giohang.Item.ProCategoryItem;
import com.example.giohang.R;

import java.util.List;

public class AdapterProCategory extends RecyclerView.Adapter<AdapterProCategory.ProCategoryHolder>
{
    Context context;
    List<ProCategoryItem> items;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public AdapterProCategory(Context context, List<ProCategoryItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProCategoryHolder(LayoutInflater.from(context).inflate(R.layout.procategory_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProCategoryHolder holder, int position) {
        holder.txtProCategory.setText(items.get(position).getProCategoryName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProCategoryHolder extends RecyclerView.ViewHolder
    {
        TextView txtProCategory;
        LinearLayout linearProCategory;
        public ProCategoryHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            txtProCategory = itemView.findViewById(R.id.txtProCategory);
            linearProCategory = itemView.findViewById(R.id.linearProCategory);
            linearProCategory.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }
    }
}
