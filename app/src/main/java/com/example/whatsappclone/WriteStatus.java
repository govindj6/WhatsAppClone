package com.example.whatsappclone;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class WriteStatus extends AppCompatActivity {
    FloatingActionButton floatingWriteSend;
    ImageView writeEmoji, writeText, writeColour;
    ConstraintLayout constraintLayout;
    EditText etWriteStatus;
    int colorIndex = 1;
    int color;
    String FontFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_status);
        getSupportActionBar().hide();

        floatingWriteSend = findViewById(R.id.floating_write_status);
        writeEmoji = findViewById(R.id.write_emoji);
        writeText = findViewById(R.id.write_text);
        writeColour = findViewById(R.id.write_colour);
        constraintLayout = findViewById(R.id.write_status_layout);
        etWriteStatus = findViewById(R.id.et_write_status);

        writeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextChange();
            }
        });

        writeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onColourChange();
            }
        });
        writeEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onColourChange() {
        if (colorIndex == 0) {
            color = Color.MAGENTA;
            colorIndex = 1;
        } else if (colorIndex == 1) {
            color = Color.DKGRAY;
            colorIndex = 2;
        } else if (colorIndex == 2) {
            color = Color.CYAN;
            colorIndex = 3;
        } else {
            color = Color.LTGRAY;
            colorIndex = 0;
        }
        constraintLayout.setBackgroundColor(color);
    }

    int fontIndex = 1;

    public void onTextChange() {
        if (fontIndex == 0) {
            FontFamily = "cursive";
            fontIndex = 1;
        } else if (fontIndex == 1) {
            FontFamily = "sans-serif-thin";
            fontIndex = 2;
        } else {
            FontFamily = "sans-serif";
            fontIndex = 0;
        }
        etWriteStatus.setTypeface(Typeface.create(FontFamily, Typeface.NORMAL));
    }
}
