package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tb;
    LinearLayout ll;
    ViewPager vp;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("WhatsApp");
        ab.setElevation(0);
        ll=findViewById(R.id.ll);
        vp=findViewById(R.id.viewPager);
        vp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        manager=getSupportFragmentManager();
        tb=findViewById(R.id.tablayout);
        tb.setupWithViewPager(vp);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String Title = item.getTitle().toString();
        switch (Title) {
            case "Settings":
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
