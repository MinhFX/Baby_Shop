package com.example.giohang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.ChiTietSanPham;
import com.example.giohang.Cart.CartHolder;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.Item.ProductItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder>
{
    Context context;
    List<ProductItem> items;

    public AdapterProduct(Context context, List<ProductItem> items)
    {
        this.context = context;
        this.items = items;
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position)
    {
        final ProductItem temp = items.get(position);
        String UrlTemp = items.get(position).getImageProduct();
        String Url = MainLink.GlobalLink + "ImageProduct/" + UrlTemp;
        Glide.with(context).load(Url).into(holder.imageProduct);
        holder.nameProduct.setText(items.get(position).getProductName());
        holder.quantityProduct.setText(""+items.get(position).getQuantity() + " chiếc");
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

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.getQuantity() > 0)
                {
                    ProductCartItem productToAdd = new ProductCartItem(temp.getProductID(), 1, temp.getPrice(), temp.getProductName(), temp.getImageProduct());
                    ArrayList<ProductCartItem> currentItems = CartHolder.loadCartItems(context.getApplicationContext());

                    if (currentItems == null) {
                        currentItems = new ArrayList<>();
                    }

                    if (!check(currentItems, temp.getProductID()))
                    {
                        currentItems.add(productToAdd);
                    }

                    CartHolder.saveCartItems(context.getApplicationContext(), currentItems);
                    Toast.makeText(context, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Sản phẩm hiện đang hết hàng vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check(ArrayList<ProductCartItem> currentItems, int id)
    {
        for (ProductCartItem productToAdd : currentItems) {
            if (productToAdd.getProductID() == id) {
                if (productToAdd.getQuantity() + 1 <= 100)
                {
                    productToAdd.setQuantity(productToAdd.getQuantity() + 1);
                    return true;
                }
                else
                {
                    Toast.makeText(context, "Số lượng sản phẩm đã đạt giới hạn!", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder
    {
        Button btnadd;
        ImageView imageProduct;
        TextView nameProduct, priceProduct, quantityProduct;
        RelativeLayout frameLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.txtOrderProductImage);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            priceProduct = itemView.findViewById(R.id.priceProduct);
            quantityProduct = itemView.findViewById(R.id.quantityProduct);
            frameLayout = itemView.findViewById(R.id.RelativeLay);
            btnadd = itemView.findViewById(R.id.buttonAdd);
        }
    }
}
