package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChattingScreenActivity extends AppCompatActivity {
    EditText etmsg;
    Button btnVoice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_screen);
        ActionBar ab=getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setDisplayShowCustomEnabled(true);
        ab.setCustomView(R.layout.custom_actionbar);
        View v=ab.getCustomView();
        TextView name=v.findViewById(R.id.ab_tvname);
        CircleImageView dp=v.findViewById(R.id.ab_ivdp);
        final ImageView back=v.findViewById(R.id.ab_ivback);
        final ImageView menu=v.findViewById(R.id.ab_ivmenu);
        final ImageView phone=v.findViewById(R.id.ab_ivphone);
        ImageView vedio=v.findViewById(R.id.ab_ivvideo);
        etmsg=findViewById(R.id.etmsg);


        Intent intent=getIntent();
        String pic=intent.getStringExtra("image");
        String title=intent.getStringExtra("title");

        name.setText(title);
        AQuery aQuery=new AQuery(ChattingScreenActivity.this);
        aQuery.id(dp).image(pic);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(ChattingScreenActivity.this,menu);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String menu=item.getTitle().toString();
                        switch (menu){
                            case "View contact":
                                Toast.makeText(ChattingScreenActivity.this, "View contact", Toast.LENGTH_SHORT).show();
                                break;
                            case "Media":break;
                            case "Search":break;
                            case "Mute notification":break;
                            case "Wallpaper":break;
                            case "More":break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });





    }
}