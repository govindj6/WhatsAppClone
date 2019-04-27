package com.example.whatsappclone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.androidquery.AQuery;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActionButtonAdapter extends RecyclerView.Adapter<ActionButtonAdapter.ActionViewHolder> {
    List<Chat> list;
    Context context;
    ContactClickListener listener;

    public ActionButtonAdapter(List<Chat> list, ContactClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_actionbtn, null);
        return new ActionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder actionViewHolder, int i) {
       final Chat position = list.get(i);
        actionViewHolder.tvNameAction.setText(position.getTitle());
        AQuery aQuery = new AQuery(context);
        aQuery.id(actionViewHolder.cvAction).image(position.getImage());
        actionViewHolder.viewContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onContactClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ActionViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cvAction;
        TextView tvNameAction;
        View viewContactList;
        public ActionViewHolder(@NonNull View itemView) {
            super(itemView);
            cvAction = itemView.findViewById(R.id.circleiv_action);
            tvNameAction = itemView.findViewById(R.id.tv_name_action);
            viewContactList=itemView.findViewById(R.id.contact_list_layout);
        }
    }
    public  interface ContactClickListener{
        void onContactClickListener(Chat chat);
    }
}
