package com.example.giohang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.giohang.Item.SliderBannerItem;
import com.example.giohang.Item.VoucherItem;
import com.example.giohang.Link.MainLink;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterSliderImage extends RecyclerView.Adapter<AdapterSliderImage.VoucherViewHolder> {
    Context context;
    List<SliderBannerItem> items;
    public AdapterSliderImage(Context context, List<SliderBannerItem> items)
    {
        this.context = context;
        this.items = items;
    }

    public String fomartMoney(int value)
    {
        return NumberFormat.getNumberInstance(Locale.US).format(value);
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoucherViewHolder(LayoutInflater.from(context).inflate(R.layout.slider_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        final SliderBannerItem temp = items.get(position);
        String Url = MainLink.GlobalLink + "images/icon_home/" + items.get(position).getImage();
        Glide.with(context)
                .load(Url)
                .into(holder.imageBannerMain);
        holder.fSliderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class VoucherViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fSliderImage;
        ImageView imageBannerMain;
        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBannerMain = itemView.findViewById(R.id.imageBannerMain);
            fSliderImage = itemView.findViewById(R.id.fSliderImage);
        }
    }
}
