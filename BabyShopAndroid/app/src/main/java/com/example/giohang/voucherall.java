package com.example.giohang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.giohang.CallPHP.VoucherAllPhp;
import com.example.giohang.CallPHP.VoucherPhp;
import com.example.giohang.R;

public class voucherall extends AppCompatActivity {
    RecyclerView rvVoucherAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucherall);
        rvVoucherAll = findViewById(R.id.rvVoucherAll);
        new VoucherAllPhp(voucherall.this, rvVoucherAll,1).execute();

    }

    public void clickGobackVoucher(View view)
    {
        finish();
    }
}