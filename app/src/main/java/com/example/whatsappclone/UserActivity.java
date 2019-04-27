package com.example.whatsappclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

public class UserActivity extends AppCompatActivity {
    ImageView imageViewDp;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        imageViewDp=findViewById(R.id.imageview_dp);
        username=findViewById(R.id.user_name);

        Intent i=getIntent();
        String dp=i.getStringExtra("dp");
        String title=i.getStringExtra("name");

        AQuery aQuery=new AQuery(UserActivity.this);
        aQuery.id(imageViewDp).image(dp);
        username.setText(title);

    }
}
