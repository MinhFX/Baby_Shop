package com.example.giohang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.giohang.Adapter.AdapterProCategory;
import com.example.giohang.CallPHP.Product;
import com.example.giohang.CallPHP.ProductListWithSearchPhp;
import com.example.giohang.CallPHP.Product_Category;
import com.example.giohang.Item.ProCategoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DanhMuc extends AppCompatActivity {
    RecyclerView recyclerView, prorecyclerView;
    TextView txtSearch2;
    AdapterProCategory adapterProCategory;
    List<ProCategoryItem> proCategoryItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhmuc);
        recyclerView = findViewById(R.id.recyclerView);
        prorecyclerView = findViewById(R.id.prorecyclerView);
        AsyncTask<String, List<ProCategoryItem>, List<ProCategoryItem>> asyncTask = new Product_Category(1).execute();

        try {
            proCategoryItems = asyncTask.get();
            adapterProCategory = new AdapterProCategory(DanhMuc.this, proCategoryItems);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
            prorecyclerView.setLayoutManager(manager);
            prorecyclerView.setAdapter(adapterProCategory);

            if (getIntent().hasExtra("NameProduct"))
            {
                new ProductListWithSearchPhp(DanhMuc.this, recyclerView,1 , getIntent().getStringExtra("NameProduct")).execute();
            }
            else
            {
                new Product(DanhMuc.this, recyclerView, 1).execute();
            }

            adapterProCategory.setOnItemClickListener(position -> {
                ProCategoryItem proCategoryItem = proCategoryItems.get(position);
                int proCategoryID = proCategoryItem.getProCategoryID();
                if (proCategoryID == 1 || proCategoryID == 0)
                {
                    new Product(DanhMuc.this, recyclerView, 1).execute();

                }
                else
                {
                    new Product(DanhMuc.this, recyclerView, 0).execute(String.valueOf(proCategoryID));
                }
            });
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Tìm kiếm sản phẩm
        txtSearch2 = findViewById(R.id.txtSearch2);
        txtSearch2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    String txtInput = txtSearch2.getText().toString().trim();
                    new ProductListWithSearchPhp(DanhMuc.this, recyclerView,1 , txtInput).execute();
                    return true;
                }
                return false;
            }
        });
    }

    public void backProduct(View view)
    {
        finish();
    }
}