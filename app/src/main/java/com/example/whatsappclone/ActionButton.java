package com.example.whatsappclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActionButton extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Chat>arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_button);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.custom_ab_fbtn);
        View v = ab.getCustomView();
        TextView contactCount=v.findViewById(R.id.tv_contact_no);
        ImageView back=v.findViewById(R.id.iv_back);
        ImageView search=v.findViewById(R.id.iv_search);
        ImageView option=v.findViewById(R.id.iv_option_menu);




    }
}
