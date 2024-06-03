package com.example.giohang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterCart;
import com.example.giohang.CallPHP.CheckQuantity;
import com.example.giohang.CallPHP.CreateOrder;
import com.example.giohang.CallPHP.CreateOrderDetail;
import com.example.giohang.Cart.CartHolder;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.Item.OrderItem;
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class XacNhanGioHang extends AppCompatActivity
{
    private static final int ShippingCost = 10000;
    int total;
    Button sendOrder;
    TextView totalAmount, totalAmountProduct, ttPoint, orderID, fullName, phone, address;
    RecyclerView recycler;
    AdapterCart adapterCart;
    ArrayList<ProductCartItem> currentItems;
    ArrayList<UserItem> userItems;

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    private int calculateTotal(ArrayList<ProductCartItem> items)
    {
        int total = 0;

        for (ProductCartItem current : items)
        {
            total += current.getPrice() * current.getQuantity();
        }

        return total;
    }

    private void setTextLayout()
    {
        total = calculateTotal(currentItems);
        ttPoint.setText("+"+(total/1000));
        totalAmountProduct.setText(getFormatedAmount(total));
        totalAmount.setText(getFormatedAmount(total+ShippingCost));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cofirm_cart);
        currentItems = CartHolder.loadCartItems(getApplicationContext());
        userItems = UserHolder.loadUserItem(getApplicationContext());

        UserItem currentUser = userItems.get(0);

        fullName = findViewById(R.id.txtUserFullname);
        phone = findViewById(R.id.txtUserPhone);
        address = findViewById(R.id.txtUserAddress);
        totalAmountProduct = findViewById(R.id.txtOrderAmount);
        totalAmount = findViewById(R.id.txtOrderTotalAmount);
        ttPoint = findViewById(R.id.txtPointProduct);
        sendOrder = findViewById(R.id.buttonSendOrder);
        orderID = findViewById(R.id.txtOrderID);

        adapterCart = new AdapterCart(this, currentItems);
        recycler = findViewById(R.id.recyclerConfirmCart);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapterCart);

        fullName.setText(currentUser.getFullName());
        phone.setText(""+currentUser.getPhone());
        address.setText(currentUser.getAddress());

        setTextLayout();

        new CreateOrder(XacNhanGioHang.this, orderID,2).execute("","","","","");

        sendOrder.setOnClickListener(v -> {
            AsyncTask<String, List<OrderItem>, String> asyncTask;
            String check = "";
            for (ProductCartItem item : currentItems)
            {
                asyncTask = new CheckQuantity(XacNhanGioHang.this, 1).execute(String.valueOf(item.getProductID()), String.valueOf(item.getQuantity()));
                try {
                    check = asyncTask.get();
                    if (!check.equals("success"))
                        break;
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (check.equals("success"))
            {
                new CreateOrder(XacNhanGioHang.this, orderID,1).execute(currentUser.getUsername(), String.valueOf((total+ShippingCost)), currentUser.getFullName(), currentUser.getAddress(), String.valueOf(currentUser.getPhone()));
                for (ProductCartItem item : currentItems)
                {
                    new CreateOrderDetail(XacNhanGioHang.this, 1).execute(String.valueOf(item.getProductID()), String.valueOf(item.getPrice()), String.valueOf(item.getQuantity()));
                }
                CartHolder.clearCart(XacNhanGioHang.this);
                Toast.makeText(XacNhanGioHang.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(XacNhanGioHang.this, "Trong giỏ hàng có sản phẩm đã hết hàng hoặc số lượng đặt quá số lượng tồn kho", Toast.LENGTH_LONG).show();
            }
        });

        adapterCart.setOnItemClickListener(new AdapterCart.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                currentItems.remove(position);
                CartHolder.saveCartItems(getApplicationContext(), currentItems);
                adapterCart.notifyItemRemoved(position);
                if (currentItems.size() == 0) {
                    CartHolder.clearCart(XacNhanGioHang.this);
                    setContentView(R.layout.activity_giohang_trong);
                    return;
                }
                setTextLayout();
            }

            @Override
            public void onPlusClick(int position, TextView Quantity) {
                ProductCartItem current = currentItems.get(position);
                if (current.getQuantity() < 100)
                {
                    current.setQuantity(current.getQuantity() + 1);
                    Quantity.setText(""+current.getQuantity());
                    CartHolder.saveCartItems(XacNhanGioHang.this, currentItems);
                    setTextLayout();
                }
            }

            @Override
            public void onMinusClick(int position, TextView Quantity) {
                ProductCartItem current = currentItems.get(position);
                if (current.getQuantity() > 1)
                {
                    current.setQuantity(current.getQuantity() - 1);
                    Quantity.setText(""+current.getQuantity());
                    CartHolder.saveCartItems(XacNhanGioHang.this, currentItems);
                    setTextLayout();
                }
            }
        });
    }
    public void returnHome(View view)
    {
        finish();
    }

    public void clickgoToProduct(View view)
    {
        Intent intent = new Intent(XacNhanGioHang.this, DanhMuc.class);
        startActivity(intent);
        finish();
    }

    public void goProduct(View view)
    {
        Intent intent = new Intent(XacNhanGioHang.this, DanhMuc.class);
        startActivity(intent);
        finish();
    }
}