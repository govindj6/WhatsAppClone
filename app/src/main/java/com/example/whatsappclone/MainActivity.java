package com.example.whatsappclone;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabLayout tb;
    LinearLayout ll;
    ViewPager vp;
    FloatingActionButton floatingActionButton;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("WhatsApp");
        ab.setElevation(0);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        ll=findViewById(R.id.ll);
        vp=findViewById(R.id.viewPager);
        vp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        manager=getSupportFragmentManager();
        tb=findViewById(R.id.tablayout);
        tb.setupWithViewPager(vp);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,ActionButton.class);
                startActivity(in);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
