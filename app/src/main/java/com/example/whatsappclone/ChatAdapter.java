package com.example.whatsappclone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    ArrayList<Chat>al;
    Context context;
    ChatClickListener listener;

    public ChatAdapter(ArrayList<Chat> al,ChatClickListener listener) {

        this.al = al;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_list_items,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Chat position=al.get(i);
        myViewHolder.tvname.setText(position.Title);
        String ivurl=position.image;

        AQuery aQuery=new AQuery(context);
        aQuery.id(myViewHolder.ivdp).image(ivurl);
        myViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener !=null){
                    listener.onChatClicked(position);
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvname,tvmsg;
        CircleImageView ivdp;
        View container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tv_name);
            tvmsg=itemView.findViewById(R.id.tv_msg);
            ivdp=itemView.findViewById(R.id.iv_dp);
            container=itemView.findViewById(R.id.layoutView);
        }
    }

    public interface ChatClickListener{
        void onChatClicked(Chat chat);
    }
}
