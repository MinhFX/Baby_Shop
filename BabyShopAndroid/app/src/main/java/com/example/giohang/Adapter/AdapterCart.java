package com.example.giohang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartHolder> {

    Context context;
    ArrayList<ProductCartItem> items;
    OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
        void onPlusClick(int position, TextView Quantity);
        void onMinusClick(int position, TextView Quantity);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public AdapterCart(Context context, ArrayList<ProductCartItem> items) {
        this.context = context;
        this.items = items;
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartHolder(LayoutInflater.from(context).inflate(R.layout.cart_product_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        String UrlTemp = items.get(position).getImageProduct();
        String Url = MainLink.GlobalLink + "ImageProduct/" + UrlTemp;
        Glide.with(context).load(Url).into(holder.imgProduct);

        holder.proName.setText(items.get(position).getProductName());
        holder.proQuantity.setText("" + items.get(position).getQuantity());
        holder.proPrice.setText(getFormatedAmount(items.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CartHolder extends  RecyclerView.ViewHolder
    {
        FrameLayout proFrame;
        ImageView imgProduct, minus, plus;
        TextView proName, proPrice, proQuantity, proDelete;
        public CartHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            proFrame = itemView.findViewById(R.id.FrameProductCart);
            imgProduct = itemView.findViewById(R.id.txtOrderProductImage);
            proName = itemView.findViewById(R.id.txtOrderProductName);
            proPrice = itemView.findViewById(R.id.txtPrice);
            proQuantity = itemView.findViewById(R.id.txtProductQuantity);
            minus = itemView.findViewById(R.id.btnMinusQuantity);
            plus = itemView.findViewById(R.id.btnPlusQuantity);
            proDelete = itemView.findViewById(R.id.txtDelete);

            proDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMinusClick(getAdapterPosition(), proQuantity);
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlusClick(getAdapterPosition(), proQuantity);
                }
            });
        }
    }
}
