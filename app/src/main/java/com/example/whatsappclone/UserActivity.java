package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.androidquery.AQuery;

public class UserActivity extends AppCompatActivity {
    ImageView imageViewDp, backpress, profileIvOption;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        imageViewDp=findViewById(R.id.imageview_dp);
        username=findViewById(R.id.user_name);
        backpress = findViewById(R.id.profile_ivback);
        profileIvOption = findViewById(R.id.profile_ivoption);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profileIvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(UserActivity.this, profileIvOption);
                popupMenu.inflate(R.menu.profile_option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String items = item.getTitle().toString();
                        switch (items) {
                            case "share":
                                break;
                            case "edit":
                                break;
                            case "view_in_addressbook":
                                break;
                            case "security_code":
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        Intent i=getIntent();
        String dp=i.getStringExtra("dp");
        String title=i.getStringExtra("name");

        AQuery aQuery=new AQuery(UserActivity.this);
        aQuery.id(imageViewDp).image(dp);
        username.setText(title);

    }
}
