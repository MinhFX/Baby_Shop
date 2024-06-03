package com.example.giohang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.giohang.Link.MainLink;

public class ChiTietTinTuc extends AppCompatActivity {
    ImageView img;
    TextView title, content, date, staffname, titleMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiettintuc);

        img = findViewById(R.id.imgNew);
        title = findViewById(R.id.newName);
        content = findViewById(R.id.txtcontentNew);
        date = findViewById(R.id.txtDate);
        staffname = findViewById(R.id.txtNguoidang);
        titleMain = findViewById(R.id.titleMainNew);

        String UrlTemp = getIntent().getStringExtra("ImageNews");
        String Url = MainLink.GlobalLink + "ImageNews/" + UrlTemp;
        Glide.with(this).load(Url).into(img);

        content.setText(getIntent().getStringExtra("Content"));
        title.setText(getIntent().getStringExtra("Title"));
        titleMain.setText(getIntent().getStringExtra("Title"));
        date.setText(getIntent().getStringExtra("Date"));
        staffname.setText(getIntent().getStringExtra("Staffname"));
    }

    public void backNews(View view)
    {
        finish();
    }
}