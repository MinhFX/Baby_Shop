package com.example.giohang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.giohang.Cart.CartHolder;
import com.example.giohang.Cart.ProductCartItem;
import com.example.giohang.Link.MainLink;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChiTietSanPham extends AppCompatActivity {
    ImageView img, minus, plus;
    TextView des, name, price, quantity;
    Button add;
    private int numQuantity = 1;
    private int proDuctID;
    private ArrayList<ProductCartItem> currentItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        img = findViewById(R.id.imgPro);
        des = findViewById(R.id.proDes);
        name = findViewById(R.id.proName);
        price = findViewById(R.id.proPrice);
        add = findViewById(R.id.buttonAddDeteilProduct);
        quantity = findViewById(R.id.txtQuantityProduct);
        minus = findViewById(R.id.buttonMinusDetailProduct);
        plus = findViewById(R.id.buttonPlusDetailProduct);

        String UrlTemp = getIntent().getStringExtra("ImageProduct");
        String Url = MainLink.GlobalLink + "ImageProduct/" + UrlTemp;
        Glide.with(this).load(Url).into(img);

        proDuctID = getIntent().getIntExtra("ProductID", 0);
        quantity.setText("" + numQuantity);
        name.setText(getIntent().getStringExtra("ProductName"));
        des.setText(getIntent().getStringExtra("Describes"));
        price.setText(getFormatedAmount(getIntent().getIntExtra("Price", 0)));

        currentItems = CartHolder.loadCartItems(getApplicationContext());

        if (currentItems == null)
        {
            currentItems = new ArrayList<>();
        }

        minus.setOnClickListener(v -> {
            if (numQuantity > 1)
            {
                numQuantity -= 1;
                quantity.setText("" + numQuantity);
            }
        });

        plus.setOnClickListener(v -> {
            if (numQuantity < 100)
            {
                numQuantity += 1;
                quantity.setText("" + numQuantity);
            }
        });

        add.setOnClickListener(v -> {
            if (getIntent().getIntExtra("Quantity", 0) > 0)
            {
                if (!check(currentItems))
                {
                    ProductCartItem productToAdd = new ProductCartItem(proDuctID, numQuantity, getIntent().getIntExtra("Price", 0), getIntent().getStringExtra("ProductName"), UrlTemp);
                    currentItems.add(productToAdd);
                    Toast.makeText(ChiTietSanPham.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                }
                CartHolder.saveCartItems(getApplicationContext(), currentItems);
            }
            else
            {
                Toast.makeText(ChiTietSanPham.this, "Sản phẩm hiện đang hết hàng vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean check(ArrayList<ProductCartItem> currentItems)
    {
        for (ProductCartItem productToAdd : currentItems) {
            if (productToAdd.getProductID() == proDuctID) {
                if (productToAdd.getQuantity() + numQuantity <= 100)
                {
                    productToAdd.setQuantity(productToAdd.getQuantity() + numQuantity);
                    Toast.makeText(ChiTietSanPham.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ChiTietSanPham.this, "Số lượng sản phẩm đã đạt giới hạn!", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        }
        return false;
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    public void backChiTietProduct(View view)
    {
        finish();
    }
}