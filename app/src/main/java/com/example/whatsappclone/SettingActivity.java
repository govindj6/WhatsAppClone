package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {
    CircleImageView settingIv;
    TextView settingUser, settingStatus;
    String name = "";
    int mobile;
    String status = "";
    String dpurl = "";
    ParseFile dp = null;
    protected static final String WHATSAPPUSER_TABLE = "WhatsAppUser";
    protected static final String WHATSAPPUSER_ROW_NAME = "name";
    protected static final String WHATSAPPUSER_ROW_MOBILE = "mobile";
    protected static final String WHATSAPPUSER_ROW_STATUS = "status";
    protected static final String WHATSAPPUSER_ROW_PIC = "profilepic";
    protected static final String TAG = "Back4app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingIv = findViewById(R.id.settings_iv);
        settingUser = findViewById(R.id.settings_user);
        settingStatus = findViewById(R.id.settings_status);

        settingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SettingActivity.this, ProfileActivity.class);
                in.putExtra("name", name);
                in.putExtra("mobile", mobile);
                in.putExtra("status", status);
                in.putExtra("dp", dpurl);
                startActivity(in);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getUserData();
    }

    public void getUserData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(WHATSAPPUSER_TABLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null && objects.size() > 0) {
                        for (ParseObject object : objects) {
                            name = object.getString(WHATSAPPUSER_ROW_NAME);
                            mobile = object.getInt(WHATSAPPUSER_ROW_MOBILE);
                            status = object.getString(WHATSAPPUSER_ROW_STATUS);
                            dp = object.getParseFile(WHATSAPPUSER_ROW_PIC);
                            settingUser.setText(name);
                            settingStatus.setText(status);

                            if (dp != null) {
                                dpurl = dp.getUrl();
                                AQuery aQuery = new AQuery(SettingActivity.this);
                                aQuery.id(settingIv).image(dpurl);
                            }
                            Log.d(TAG, "User[" + name + "],[" + mobile + "],[" + status + "]");
                        }
                    } else {
                        Log.d(TAG, "No matching found");
                    }
                } else {
                    Log.e(TAG, "Query error[" + e.getMessage() + "]");
                }
            }
        });
    }
}
