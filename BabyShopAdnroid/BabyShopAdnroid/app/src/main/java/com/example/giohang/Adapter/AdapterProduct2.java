package com.example.giohang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.ChiTietSanPham;
import com.example.giohang.Item.ProductItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterProduct2 extends RecyclerView.Adapter<AdapterProduct2.ProductViewHolderSub>
{
    Context context;
    List<ProductItem> items;

    public AdapterProduct2(Context context, List<ProductItem> items)
    {
        this.context = context;
        this.items = items;
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @NonNull
    @Override
    public ProductViewHolderSub onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolderSub(LayoutInflater.from(context).inflate(R.layout.task_bestsell2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolderSub holder, int position)
    {
        final ProductItem temp = items.get(position);
        int resourceId = context.getResources().getIdentifier("pro" + items.get(position).getImageProduct().split("\\.")[0],"drawable", context.getPackageName());
        String UrlTemp = items.get(position).getImageProduct();
        String Url = MainLink.GlobalLink + "ImageProduct/" + UrlTemp;
        Glide.with(context).load(Url).into(holder.imageProduct);
        holder.nameProduct.setText(items.get(position).getProductName());
        holder.priceProduct.setText(getFormatedAmount(items.get(position).getPrice()));

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("ProductID", temp.getProductID());
                intent.putExtra("ProductName", temp.getProductName());
                intent.putExtra("ImageProduct", temp.getImageProduct());
                intent.putExtra("Describes", temp.getDescribes());
                intent.putExtra("Price", temp.getPrice());
                intent.putExtra("Quantity", temp.getQuantity());
                intent.putExtra("Status", temp.getStatus());
                intent.putExtra("Category_ID", temp.getCategory_ID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolderSub extends RecyclerView.ViewHolder
    {
        ImageView imageProduct;
        TextView nameProduct, priceProduct;
        FrameLayout frameLayout;

        public ProductViewHolderSub(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imProduct);
            nameProduct = itemView.findViewById(R.id.txtNameProduct);
            priceProduct = itemView.findViewById(R.id.txtPrice);
            frameLayout = itemView.findViewById(R.id.frameProduct);
        }
    }
}
