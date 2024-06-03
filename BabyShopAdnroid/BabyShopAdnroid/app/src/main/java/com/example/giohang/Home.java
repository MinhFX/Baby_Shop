package com.example.giohang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.CallPHP.CategoriesHomePhp;
import com.example.giohang.CallPHP.Product2;
import com.example.giohang.CallPHP.Product3;
import com.example.giohang.CallPHP.VoucherPhp;
import com.example.giohang.Link.MainLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Home extends AppCompatActivity {
    ImageView imageButton3;
    RecyclerView recyclerView, recylerView1,rvCategoriesName,rvProducts2;
    ViewFlipper vfSliderImage;
    TextView txtSearch;
    int width = 955;
    int height = 451;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        recyclerView = findViewById(R.id.rvVoucher);
        recylerView1 = findViewById(R.id.rvProducts);
        rvCategoriesName = findViewById(R.id.rvCategoriesName);
        rvProducts2 = findViewById(R.id.rvProducts2);


        new VoucherPhp(Home.this, recyclerView,1).execute();
        new Product3(Home.this,recylerView1,1).execute();
        new CategoriesHomePhp(Home.this,rvCategoriesName,1).execute();
        new Product2(Home.this,rvProducts2,1).execute();

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_rating = new Intent(Home.this, Rating.class);
                startActivity(intent_rating);
            }
        });

        //Chạy SliderImage

        vfSliderImage = findViewById(R.id.vfSliderImage);

        // Execute AsyncTask to fetch JSON from the URL
        new FetchJsonTask().execute(MainLink.GlobalLink + "sliderimage.php");

        //Tìm kiếm sản phẩm
        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    String txtInput = txtSearch.getText().toString().trim();
                    Intent intent = new Intent(Home.this, DanhMuc.class);
                    intent.putExtra("NameProduct", txtInput);
                    startActivity(intent);
                    return true;
                }
                return false; //Không xử lý sự kiện
            }
        });

    }

    //SliderImage
    private class FetchJsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonFromServer) {
            if (jsonFromServer != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonFromServer);
                    JSONArray sliderBanners = jsonObject.getJSONArray("sliderimage");

                    for (int i = 0; i < sliderBanners.length(); i++) {
                        JSONObject banner = sliderBanners.getJSONObject(i);

                        // Lấy URL của ảnh từ JSON
                        String imageUrl = banner.getString("Image");

                        // Tạo ImageView
                        ImageView imageView = new ImageView(Home.this);

                        // Sử dụng Glide để tải ảnh từ URL và đặt vào ImageView
                        Glide.with(Home.this)
                                .load(MainLink.GlobalLink + "images/slider_banner/" + imageUrl)
                                .placeholder(R.drawable.banner_main)
                                .override(width, height)
                                .into(imageView);

                        // Thêm ImageView vào ViewFlipper
                        vfSliderImage.addView(imageView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Home.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Home.this, "Không thể kết nối đến server", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clickGiohang(View view)
    {
        Intent intent = new Intent(Home.this, Chitietdonhang.class);
        startActivity(intent);
    }

    public void clickNews(View view)
    {
        Intent intent = new Intent(Home.this, News.class);
        startActivity(intent);
    }

    public void clickCart(View view)
    {
        Intent intent = new Intent(Home.this, Giohang.class);
        startActivity(intent);
    }

    public void clickProfile(View view)
    {
        Intent intent = new Intent(Home.this, AccountEdit.class);
        startActivityForResult(intent,10);
    }

    public void btnGoVoucherAll(View view)
    {
        Intent intent = new Intent(Home.this, voucherall.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == RESULT_OK)
        {
            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}