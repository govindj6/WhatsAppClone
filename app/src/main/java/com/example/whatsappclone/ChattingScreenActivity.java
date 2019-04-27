package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChattingScreenActivity extends AppCompatActivity {
    CustomEditText etmsg;
    Button btnSend;
    RecyclerView rvChatting;
    ArrayList<Chatting> arr=new ArrayList<>();
    ChattingAdapter ca;
    CustomEditText customEditText;
    String pic="";
    String title="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_screen);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.custom_actionbar);
        View v = ab.getCustomView();

        final TextView name = v.findViewById(R.id.ab_tvname);
        CircleImageView dp = v.findViewById(R.id.ab_ivdp);
        final ImageView back = v.findViewById(R.id.ab_ivback);
        final ImageView menu = v.findViewById(R.id.ab_ivmenu);
        final ImageView phone = v.findViewById(R.id.ab_ivphone);
        ImageView vedio = v.findViewById(R.id.ab_ivvideo);
        btnSend = findViewById(R.id.btnSend);
        etmsg = findViewById(R.id.custom_Edittext);
        rvChatting = findViewById(R.id.rvChatting);

        ca = new ChattingAdapter(arr);
        rvChatting.setLayoutManager(new LinearLayoutManager(ChattingScreenActivity.this));
        rvChatting.setAdapter(ca);

        Intent intent = getIntent();
        pic = intent.getStringExtra("image");
        title = intent.getStringExtra("title");

        name.setText(title);
        AQuery aQuery = new AQuery(ChattingScreenActivity.this);
        aQuery.id(dp).image(pic);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ChattingScreenActivity.this, menu);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String menu = item.getTitle().toString();
                        switch (menu) {
                            case "View contact":
                                Toast.makeText(ChattingScreenActivity.this, "View contact", Toast.LENGTH_SHORT).show();
                                break;
                            case "Media":
                                break;
                            case "Search":
                                break;
                            case "Mute notification":
                                break;
                            case "Wallpaper":
                                break;
                            case "More":
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        customEditText=new CustomEditText(ChattingScreenActivity.this);
        customEditText.cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChattingScreenActivity.this, "camera", Toast.LENGTH_SHORT).show();
                Intent in=new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(in);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=customEditText.msg;
                Toast.makeText(ChattingScreenActivity.this, msg, Toast.LENGTH_SHORT).show();
                Chatting chatting = new Chatting(msg);
                arr.add(chatting);
                ca.notifyDataSetChanged();
                customEditText.messsge.getText().clear();
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ChattingScreenActivity.this,UserActivity.class);
                i.putExtra("dp",pic);
                i.putExtra("name",title);
                startActivity(i);
            }
        });
    }
}
