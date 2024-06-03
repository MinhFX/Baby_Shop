package com.example.giohang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.Chatbot;
import com.example.giohang.Item.CategoriesHomeItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;
import com.example.giohang.DanhMuc;
import com.example.giohang.voucherall;

import java.util.List;

public class adapterCategoriesHome extends RecyclerView.Adapter<adapterCategoriesHome.CategoriesHomeViewHolder> {
    Context context;
    List<CategoriesHomeItem> items;
    public adapterCategoriesHome(Context context, List<CategoriesHomeItem> items)
    {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public CategoriesHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesHomeViewHolder(LayoutInflater.from(context).inflate(R.layout.categories_home,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull CategoriesHomeViewHolder holder, int position) {
        String temp = String.valueOf(items.get(position).getCategoryID());
        holder.txtIcon.setText(items.get(position).getCategoryName());
        String Url = MainLink.GlobalLink + "images/icon_home/" + items.get(position).getImage();
        Glide.with(context)
                .load(Url)
                .into(holder.imgIconHome);
        holder.linerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent();
               switch (temp)
               {

                   case "1":
                       intent = new Intent(context, DanhMuc.class);
                       break;

                   case "2":
                       intent = new Intent(context, voucherall.class);
                       break;

                   case "3":
                       intent = new Intent(context, Chatbot.class);
                       break;
                   default:
                       break;

               }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoriesHomeViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linerIcon;
        TextView txtIcon;
        ImageView imgIconHome;
        public CategoriesHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIcon = itemView.findViewById(R.id.txtIcon);
            imgIconHome = itemView.findViewById(R.id.imgIconHome);
            linerIcon = itemView.findViewById(R.id.linerIcon);
        }
    }
}
