package com.example.giohang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterOrder;
import com.example.giohang.CallPHP.Order;
import com.example.giohang.CallPHP.OrderStatus;
import com.example.giohang.CallPHP.UpdateOrderStatus;
import com.example.giohang.Item.OrderItem;
import com.example.giohang.User.UserHolder;
import com.example.giohang.User.UserItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Chitietdonhang extends AppCompatActivity {

    RecyclerView recycler;
    AdapterOrder adapterOrder;
    List<OrderItem> orderItems = new ArrayList<>();
    List<String> list;
    ArrayAdapter adapter;
    ArrayList<UserItem> userItems;
    Spinner spinnerOrder;
    AsyncTask<String, List<OrderItem>, List<OrderItem>> asyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsuhoadon);
        recycler = findViewById(R.id.list_donhang);
        spinnerOrder = findViewById(R.id.spinnerOrder);

        userItems = UserHolder.loadUserItem(this);

        try {
            list = new ArrayList<>();
            list.add("Tất cả");
            list.add("Chờ duyệt");
            list.add("Đã hủy");
            list.add("Đã duyệt");
            list.add("Đang giao");
            list.add("Giao thành công");
            adapter = new ArrayAdapter(Chitietdonhang.this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOrder.setAdapter(adapter);
            spinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(parent.getItemAtPosition(position).toString().equals("Tất cả"))
                    {
                        asyncTask = new Order(1).execute(userItems.get(0).getUsername());
                    }
                    else
                    {
                        int status = 0;
                        if (parent.getItemAtPosition(position).toString().equals("Chờ duyệt"))
                        {
                            status = 1;
                        }
                        else if (parent.getItemAtPosition(position).toString().equals("Đã duyệt"))
                        {
                            status = 2;
                        }
                        else if (parent.getItemAtPosition(position).toString().equals("Đang giao"))
                        {
                            status = 3;
                        }
                        else if (parent.getItemAtPosition(position).toString().equals("Giao thành công"))
                        {
                            status = 4;
                        }
                        asyncTask = new OrderStatus(1).execute(userItems.get(0).getUsername(), String.valueOf(status));
                    }
                    try {
                        orderItems = asyncTask.get();
                        adapterOrder = new AdapterOrder(Chitietdonhang.this, orderItems);
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                        recycler.setLayoutManager(manager);
                        recycler.setAdapter(adapterOrder);
                        adapterOrder.setOnItemClickListener(position1 -> {
                            OrderItem temp = orderItems.get(position1);
                            Intent intent = new Intent(Chitietdonhang.this, DonHang.class);
                            intent.putExtra("OrderID", temp.getOrderID());
                            intent.putExtra("StaffID", temp.getStaffID());
                            intent.putExtra("Username", temp.getUsername());
                            intent.putExtra("Fullname", temp.getFullName());
                            intent.putExtra("Phone", temp.getPhone());
                            intent.putExtra("Address", temp.getAddress());
                            intent.putExtra("Total", temp.getTotal());
                            intent.putExtra("OrderDate", temp.getOrderDate());
                            intent.putExtra("TimeOrder", temp.getTimeOrder());
                            intent.putExtra("Status", temp.getStatus());
                            startActivityForResult(intent, 12);
                        });
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(Chitietdonhang.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK)
        {
            String orderID = data.getStringExtra("OrderID"), status = data.getStringExtra("Status");
            new UpdateOrderStatus(Chitietdonhang.this, 1).execute(orderID, status);
        }
    }

    public void btngobackorderall(View view)
    {
        finish();
    }
}