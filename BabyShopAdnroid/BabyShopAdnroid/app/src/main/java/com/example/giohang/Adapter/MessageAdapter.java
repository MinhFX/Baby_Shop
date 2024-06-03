package com.example.giohang.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giohang.Item.MessageItem;
import com.example.giohang.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageItem> messageItemList;
    private Activity activity;
    public MessageAdapter(List<MessageItem> messageItemList, Activity activity) {
        this.messageItemList = messageItemList;
        this.activity = activity; }
    public void setData(List<MessageItem> list) {
        this.messageItemList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_message,parent,false);
        return new MessageViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String message = messageItemList.get(position).getMessage();
        boolean isReceived = messageItemList.get(position).getIsReceived();
        if(isReceived){
            holder.messageReceive.setVisibility(View.VISIBLE);
            holder.messageSend.setVisibility(View.GONE);
            holder.messageReceive.setText(message);
        }else {
            holder.messageSend.setVisibility(View.VISIBLE);
            holder.messageReceive.setVisibility(View.GONE);
            holder.messageSend.setText(message);
        }
    }
    @Override
    public int getItemCount() {
        if(messageItemList !=null) {
            return messageItemList.size();
        }
        return 0;
    }
    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageSend;
        private TextView messageReceive;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageSend = itemView.findViewById(R.id.tv_message);
            messageReceive = itemView.findViewById(R.id.message_receive);
        }
    }
}
