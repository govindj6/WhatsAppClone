package com.example.whatsappclone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class WriteStatus extends AppCompatActivity {
    FloatingActionButton floatingWriteSend;
    ImageView writeEmoji, writeText, writeColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_status);
        getSupportActionBar().hide();

        floatingWriteSend = findViewById(R.id.floating_write_status);
        writeEmoji = findViewById(R.id.write_emoji);
        writeText = findViewById(R.id.write_text);
        writeColour = findViewById(R.id.write_colour);
    }
}
