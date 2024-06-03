package com.example.giohang;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giohang.CallPHP.OrderIDProduct;

import java.text.NumberFormat;
import java.util.Locale;

public class DonHang extends AppCompatActivity {

    int status, orderId;
    Button txtOrderSetStatus;
    TextView txtStatus, txtStatusDetail, txtUserDetail, txtAmount, txtProductAmount, yes, no;
    LinearLayout linearLayoutStatus1;
    RecyclerView recyclerView;
    Dialog dialog;

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadon);
        txtStatus = findViewById(R.id.txtStatusOrder);
        txtUserDetail = findViewById(R.id.txtUserDetail);
        txtStatusDetail = findViewById(R.id.txtStatusDetail);
        linearLayoutStatus1 = findViewById(R.id.linearStatus1);
        txtAmount = findViewById(R.id.txtOrderTotalAmount);
        txtProductAmount = findViewById(R.id.txtOrderAmount);
        txtOrderSetStatus = findViewById(R.id.txtOrderSetStatus);
        recyclerView = findViewById(R.id.recyclerOrderID);

        dialog = new Dialog(DonHang.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_ordercancel);
        dialog.setCancelable(false);

        yes = dialog.findViewById(R.id.btnYes);
        no = dialog.findViewById(R.id.btnNo);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 1)
                {
                    Intent res = new Intent();
                    res.putExtra("OrderID", String.valueOf(orderId));
                    res.putExtra("Status", "0");
                    setResult(RESULT_OK, res);
                    finish();
                }
            }
        });

        orderId = getIntent().getIntExtra("OrderID",0);

        txtProductAmount.setText(getFormatedAmount((getIntent().getIntExtra("Total", 0)) - 10000));
        txtAmount.setText(getFormatedAmount(getIntent().getIntExtra("Total", 0)));

        status = getIntent().getIntExtra("Status", 0);

        String userDetail = getIntent().getStringExtra("Fullname") + "\n(+84)" + getIntent().getIntExtra("Phone", 0) + "\n" + getIntent().getStringExtra("Address");

        txtUserDetail.setText(userDetail);

        new OrderIDProduct(DonHang.this, recyclerView, 1).execute("" + orderId);

        switch (status)
        {
            case 0:
            {
                txtStatus.setText("Đơn hàng đã hủy");
                break;
            }
            case 1:
            {
                txtStatus.setText("Đơn hàng đang chờ duyệt");
                break;
            }
            case 2:
            {
                txtStatus.setText("Đơn hàng dã duyệt");
                break;
            }
            case 3:
            {
                txtStatus.setText("Đơn hàng đang giao");
                break;
            }
            case 4:
            {
                txtStatus.setText("Đơn hàng giao thành công");
                break;
            }
        }

        if (status == 1)
        {
            txtOrderSetStatus.setText("Hủy đơn");
        }
        else
        {
            txtOrderSetStatus.setVisibility(View.GONE);
        }

        if (status != 3 && status != 4)
        {
            linearLayoutStatus1.setVisibility(View.GONE);
        }
        else
        {
            if (status == 3)
                txtStatusDetail.setText("Đơn hàng đang được giao");
        }

        txtOrderSetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void btnbackOrder(View view)
    {
        finish();
    }
}