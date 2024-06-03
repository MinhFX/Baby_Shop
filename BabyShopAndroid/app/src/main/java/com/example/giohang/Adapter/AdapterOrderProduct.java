package com.example.giohang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterOrderProduct extends RecyclerView.Adapter<AdapterOrderProduct.OrderProductHolder>
{
    Context context;
    List<ProductCartItem> items;

    public AdapterOrderProduct(Context context, List<ProductCartItem> items) {
        this.context = context;
        this.items = items;
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @NonNull
    @Override
    public OrderProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderProductHolder(LayoutInflater.from(context).inflate(R.layout.cart_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductHolder holder, int position) {
        holder.name.setText(items.get(position).getProductName());
        holder.quantity.setText("x" + items.get(position).getQuantity());
        holder.price.setText(getFormatedAmount(items.get(position).getPrice()));

        String UrlTemp = items.get(position).getImageProduct();
        String Url = MainLink.GlobalLink + "ImageProduct/" + UrlTemp;
        Glide.with(context).load(Url).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OrderProductHolder extends RecyclerView.ViewHolder
    {
        ImageView imgProduct;
        TextView price, name, quantity;
        public OrderProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.txtOrderProductImage);
            price = itemView.findViewById(R.id.txtOrderProductPrice);
            name = itemView.findViewById(R.id.txtOrderProductName);
            quantity = itemView.findViewById(R.id.txtOrderProductQuantity);
        }
    }
}
