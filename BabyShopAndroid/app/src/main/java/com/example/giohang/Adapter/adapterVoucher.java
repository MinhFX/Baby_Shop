package com.example.giohang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giohang.Item.VoucherItem;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class adapterVoucher extends RecyclerView.Adapter<adapterVoucher.VoucherViewHolder> {
    Context context;
    List<VoucherItem> items;
    public adapterVoucher(Context context, List<VoucherItem> items)
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
        return new VoucherViewHolder(LayoutInflater.from(context).inflate(R.layout.task_voucher,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        holder.txtNameVoucher.setText(items.get(position).getVoucherName());
        holder.txtSale.setText("Giảm ngay "+ fomartMoney(items.get(position).getDiscountPrice()) + "đ");
        holder.txtPoints.setText(""+items.get(position).getPoints()+ " điểm");
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
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
        FrameLayout frameLayout;
        TextView txtNameVoucher, txtSale,txtPoints;
        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameVoucher = itemView.findViewById(R.id.txtNameVoucher);
            txtPoints = itemView.findViewById(R.id.txtPoints);
            txtSale = itemView.findViewById(R.id.txtSale);
            frameLayout = itemView.findViewById(R.id.framelayout);
        }
    }
}
