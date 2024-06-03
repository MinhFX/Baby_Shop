package com.example.giohang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterCart;
import com.example.giohang.Cart.CartHolder;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import java.util.ArrayList;

public class Giohang extends AppCompatActivity {

    RecyclerView recycler;
    AdapterCart adapterCart;
    UserItem currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<ProductCartItem> currentItems = CartHolder.loadCartItems(getApplicationContext());
        currentUser = UserHolder.loadUserItem(Giohang.this).get(0);

        if (currentItems == null || currentItems.size() == 0) {
            setContentView(R.layout.activity_giohang_trong);
        }
        else
        {
            setContentView(R.layout.activity_cart);
            adapterCart = new AdapterCart(this, currentItems);
            recycler = findViewById(R.id.recyclerCart);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(manager);
            recycler.setAdapter(adapterCart);

            adapterCart.setOnItemClickListener(new AdapterCart.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    currentItems.remove(position);
                    CartHolder.saveCartItems(getApplicationContext(), currentItems);
                    adapterCart.notifyItemRemoved(position);
                    if (currentItems.size() == 0) {
                        CartHolder.clearCart(Giohang.this);
                        setContentView(R.layout.activity_giohang_trong);
                    }
                }

                @Override
                public void onPlusClick(int position, TextView Quantity) {
                    ProductCartItem current = currentItems.get(position);
                    if (current.getQuantity() < 100)
                    {
                        current.setQuantity(current.getQuantity() + 1);
                        Quantity.setText(""+current.getQuantity());
                        CartHolder.saveCartItems(Giohang.this, currentItems);
                    }
                }

                @Override
                public void onMinusClick(int position, TextView Quantity) {
                    ProductCartItem current = currentItems.get(position);
                    if (current.getQuantity() > 1)
                    {
                        current.setQuantity(current.getQuantity() - 1);
                        Quantity.setText(""+current.getQuantity());
                        CartHolder.saveCartItems(Giohang.this, currentItems);
                    }
                }
            });
        }
    }

    public void btnGoConfirmCart(View view)
    {
        if (!currentUser.getFullName().isEmpty() && !currentUser.getAddress().isEmpty() && currentUser.getPhone() != 0)
        {
            Intent intent = new Intent(Giohang.this, XacNhanGioHang.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(Giohang.this, "Vui lòng điền đủ thông tin tài khoản người dùng", Toast.LENGTH_SHORT).show();
        }
    }

    public void goProduct(View view)
    {
        Intent intent = new Intent(Giohang.this, DanhMuc.class);
        startActivity(intent);
        finish();
    }

    public void clickGobackCart(View view)
    {
        finish();
    }
}