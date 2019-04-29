package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    RecyclerView rvChat;
    ArrayList<Chat> al;
    RequestQueue requestQueue;
    FloatingActionButton floatingActionButton;
    ChatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_fragment, null);
        rvChat = view.findViewById(R.id.rvChat);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), ActionButton.class);
                startActivity(in);
            }
        });

        String api = "http://3.18.136.114/ypsilonTest/api/category/dataList";

        requestQueue = Volley.newRequestQueue(getActivity());

        al = new ArrayList<>();
        adapter = new ChatAdapter(al, new ChatAdapter.ChatClickListener() {
            @Override
            public void onChatClicked(Chat chat) {
                Intent intent=new Intent(getActivity(),ChattingScreenActivity.class);
                intent.putExtra("image",chat.getImage());
                intent.putExtra("title",chat.Title);
                startActivity(intent);
            }
        });
        rvChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChat.setAdapter(adapter);

        final JsonObjectRequest objectRequest = new JsonObjectRequest(api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Gson gson = new Gson();
                    ChatResponse res = gson.fromJson(response.toString(), ChatResponse.class);
                    al.addAll(res.Data);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        requestQueue.add(objectRequest);
        return view;
    }
}
