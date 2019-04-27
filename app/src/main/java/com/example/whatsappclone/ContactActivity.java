package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.custom_actionbar);
        View v = ab.getCustomView();

        TextView name = v.findViewById(R.id.ab_tvname);
        CircleImageView dp = v.findViewById(R.id.ab_ivdp);
        ImageView back = v.findViewById(R.id.ab_ivback);
        final ImageView menu = v.findViewById(R.id.ab_ivmenu);
        ImageView phone = v.findViewById(R.id.ab_ivphone);
        ImageView vedio = v.findViewById(R.id.ab_ivvideo);

        Intent i=getIntent();
        String contactname=i.getStringExtra("contactname");
        String contactdp=i.getStringExtra("dp");

        name.setText(contactname);
        AQuery aQuery=new AQuery(getApplicationContext());
        aQuery.id(dp).image(contactdp);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ContactActivity.this, menu);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String menu = item.getTitle().toString();
                        switch (menu) {
                            case "View contact":
                                Toast.makeText(ContactActivity.this, "View contact", Toast.LENGTH_SHORT).show();
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



    }
}
