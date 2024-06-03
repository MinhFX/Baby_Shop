package com.example.giohang.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giohang.Item.OrderItem;
import com.example.giohang.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.OrderHolder>
{
    Context context;
    List<OrderItem> items;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public AdapterOrder(Context context, List<OrderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false), listener);
    }

    private String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount) + "đ";
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        holder.orderId.setText("Đơn hàng #"+items.get(position).getOrderID());
        holder.orderDate.setText(items.get(position).getOrderDate()+ " | " + items.get(position).getTimeOrder());
        switch (items.get(position).getStatus())
        {
            case 0:
            {
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
                holder.status.setText("Hủy");
                break;
            }
            case 1:
            {
                holder.status.setText("Chờ duyệt");
                break;
            }
            case 2:
            {
                holder.status.setText("Đã Duyệt");
                break;
            }
            case 3:
            {
                holder.status.setText("Đang giao");
                break;
            }
            case 4:
            {
                holder.status.setText("Giao thành công");
                break;
            }
            default:
            {
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
                holder.status.setText("Hủy");
            }
        }
        holder.total.setText(getFormatedAmount(items.get(position).getTotal()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder
    {
        ImageView clickDetailOrder;
        TextView orderId, orderDate, status, total;
        public OrderHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            clickDetailOrder = itemView.findViewById(R.id.clickDetailOrder);
            orderId = itemView.findViewById(R.id.orderid);
            orderDate = itemView.findViewById(R.id.orderdate);
            status = itemView.findViewById(R.id.status);
            total = itemView.findViewById(R.id.tien_donhang);
            clickDetailOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
