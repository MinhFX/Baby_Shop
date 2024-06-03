package com.example.giohang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.ChiTietTinTuc;
import com.example.giohang.Item.NewsItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsHolder>
{
    Context context;
    List<NewsItem> items;

    public AdapterNews(Context context, List<NewsItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        final NewsItem temp = items.get(position);

        String UrlTemp = items.get(position).getImageNews();
        String Url = MainLink.GlobalLink + "ImageNews/" + UrlTemp;
        Glide.with(context).load(Url).into(holder.imgNews);

        holder.titleNews.setText(items.get(position).getTitle());
        holder.contentNews.setText(items.get(position).getContent());
        holder.dateNews.setText(items.get(position).getDate());
        holder.frameNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietTinTuc.class);
                intent.putExtra("NewsID", temp.getNewsID());
                intent.putExtra("StaffID", temp.getStaffID());
                intent.putExtra("Title", temp.getTitle());
                intent.putExtra("Content", temp.getContent());
                intent.putExtra("Date", temp.getDate());
                intent.putExtra("Status", temp.getStatus());
                intent.putExtra("ImageNews", temp.getImageNews());
                intent.putExtra("Staffname", temp.getStaffname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder
    {
        FrameLayout frameNews;
        ImageView imgNews;
        TextView titleNews, contentNews, dateNews;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            frameNews = itemView.findViewById(R.id.frameNews);
            imgNews = itemView.findViewById(R.id.imgNews);
            titleNews = itemView.findViewById(R.id.titleNews);
            contentNews = itemView.findViewById(R.id.contentNews);
            dateNews = itemView.findViewById(R.id.dateNews);
        }
    }
}
