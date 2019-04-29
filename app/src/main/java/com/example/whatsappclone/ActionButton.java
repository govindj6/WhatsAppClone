package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class ActionButton extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Chat>arrayList;
    ActionButtonAdapter actionButtonAdapter;
    RequestQueue requestQueue;
    int x;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_button);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.custom_ab_fbtn);
        View v = ab.getCustomView();
        final TextView contactCount=v.findViewById(R.id.tv_contact_no);
        ImageView back=v.findViewById(R.id.iv_back);
        ImageView search=v.findViewById(R.id.iv_search);
        ImageView option=v.findViewById(R.id.iv_option_menu);
        recyclerView=findViewById(R.id.rv_action_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String url="http://3.18.136.114/ypsilonTest/api/category/dataList";
        requestQueue= Volley.newRequestQueue(ActionButton.this);

        arrayList=new ArrayList<>();
        actionButtonAdapter=new ActionButtonAdapter(arrayList, new ActionButtonAdapter.ContactClickListener() {
            @Override
            public void onContactClickListener(Chat chat) {
                Intent i = new Intent(getApplicationContext(), ContactActivity.class);
                i.putExtra("dp",chat.getImage());
                i.putExtra("contactname",chat.getTitle());
                startActivity(i);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(actionButtonAdapter);

        Chat c1 = new Chat("New group", "https://ibb.co/F6mSqMm");
        Chat c2 = new Chat("New contact", "http://169.254.6.69:8080/");
        arrayList.add(c1);
        arrayList.add(c2);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    ChatResponse res = gson.fromJson(response.toString(), ChatResponse.class);
                    arrayList.addAll(res.Data);
                    actionButtonAdapter.notifyDataSetChanged();
                    int count=arrayList.size()-2;
                    contactCount.setText(count+" contacts");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActionButton.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
