package com.example.giohang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GiohangViewHolder> {
    Context context;
    List<GiohangItem> giohangList;

    public GioHangAdapter(Context context, List<GiohangItem> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public GiohangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.giohang_item, parent, false);
        return new GiohangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiohangViewHolder holder, int position) {
        GiohangItem giohang = giohangList.get(position);
        holder.item_giohang_tensp.setText(giohang.getProductName());
        holder.item_giohang_soluong.setText(giohang.getQuantity());
        int resourceId = context.getResources().getIdentifier("pro" + giohangList.get(position).getImageProduct().split("\\.")[0],"drawable", context.getPackageName());
        holder.imageProduct.setImageResource(resourceId);
        holder.item_giohang_gia.setText(giohang.getPrice());
    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public class GiohangViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageProduct;
        ImageView item_giohang_hinhanh;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong;

        public GiohangViewHolder(@NonNull View itemview){
            super(itemview);
            item_giohang_hinhanh = itemview.findViewById(R.id.item_giohang_hinhanh);
            item_giohang_tensp = itemview.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia= itemview.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemview.findViewById(R.id.item_giohang_soluong);
        }

    }
}
