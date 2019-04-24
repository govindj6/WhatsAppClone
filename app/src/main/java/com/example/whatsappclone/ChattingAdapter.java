package com.example.whatsappclone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ChattingViewHolder> {
    List<Chatting> arr;

    public ChattingAdapter(List<Chatting> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ChattingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_chatting,null);
        return new ChattingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChattingViewHolder chattingViewHolder, int i) {
        Chatting position=arr.get(i);
        chattingViewHolder.tvmsg.setText(position.msg);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ChattingViewHolder extends RecyclerView.ViewHolder{
        TextView tvmsg;

        public ChattingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmsg=itemView.findViewById(R.id.chatting_tv);
        }
    }
}
