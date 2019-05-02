package com.example.whatsappclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_IMAGE = 23;

    TextView profileName, profileMobile, profileStatus;
    ImageView profileEditName, profileEditStatus;
    FloatingActionButton profileEditDp;
    CircleImageView profileDp;
    EditText etDialog;
    String name = "";
    File file = null;
    Uri imageuri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.profile_name);
        profileStatus = findViewById(R.id.profile_status);
        profileMobile = findViewById(R.id.profile_mobile);
        profileEditName = findViewById(R.id.profile_edit_name);
        profileEditStatus = findViewById(R.id.profile_edit_status);
        profileDp = findViewById(R.id.profile_dp);
        profileEditDp = findViewById(R.id.profile_dp_edit);

        Intent in = getIntent();
        name = in.getStringExtra("name");
        final String status = in.getStringExtra("status");
        int mobile = in.getIntExtra("mobile", 0);
        String dp = in.getStringExtra("dp");

        AQuery aQuery = new AQuery(ProfileActivity.this);
        aQuery.id(profileDp).image(dp);

        profileName.setText(name);
        profileStatus.setText(status);
        profileMobile.setText("" + mobile);

        profileEditDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_GET_CONTENT);
                //in.addCategory(Intent.CATEGORY_OPENABLE);
                in.setType("image/*");
                //startActivityForResult(in, REQUEST_PICK_IMAGE);
                startActivityForResult(Intent.createChooser(in, "Select Picture"), REQUEST_PICK_IMAGE);
            }
        });

        profileEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("Enter your name");
                View view = LayoutInflater.from(ProfileActivity.this).inflate(R.layout.custom_dialog, null);
                etDialog = view.findViewById(R.id.et_profile_edit);
                etDialog.setText(name);
                dialog.setView(view);
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onUpdateName();
                    }
                });
                dialog.show();
            }
        });

        profileEditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("Update your status");
                View view = LayoutInflater.from(ProfileActivity.this).inflate(R.layout.custom_dialog, null);
                etDialog = view.findViewById(R.id.et_profile_edit);
                etDialog.setText(status);
                dialog.setView(view);
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onUpdateStatus();
                    }
                });
                dialog.show();
            }
        });
    }

    private void onUpdateName() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("WhatsAppUser");
        query.whereEqualTo(SettingActivity.WHATSAPPUSER_ROW_NAME, name);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.put(SettingActivity.WHATSAPPUSER_ROW_NAME, etDialog.getText().toString());
                    object.saveInBackground();
                } else {
                    Log.e("onUpdateName: ", e.getMessage());
                }
            }
        });
    }

    private void onUpdateStatus() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("WhatsAppUser");
        query.whereEqualTo(SettingActivity.WHATSAPPUSER_ROW_NAME, name);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.put(SettingActivity.WHATSAPPUSER_ROW_STATUS, etDialog.getText().toString());
                    object.saveInBackground();
                } else {
                    Log.e("onUpdateName: ", e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    imageuri = data.getData();
                    if (imageuri != null) {
                        file = new File(imageuri.getPath());
                        Toast.makeText(this, file.toString(), Toast.LENGTH_SHORT).show();
                        if (file.length() != 0) {
                            Toast.makeText(this, "Has data", Toast.LENGTH_SHORT).show();
                            onUpdateDp();
                        } else {
                            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onUpdateDp() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("WhatsAppUser");
        query.whereEqualTo(SettingActivity.WHATSAPPUSER_ROW_NAME, name);
        final ParseFile parseFile = new ParseFile(file);
        parseFile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(ProfileActivity.this, "Successfully image change", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to change image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.put(SettingActivity.WHATSAPPUSER_ROW_PIC, parseFile);
                    object.saveInBackground();
                } else {
                    Log.e("onUpdateName: ", e.getMessage());
                }
            }
        });
    }

    private File getFileFromData(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return new File(picturePath);
    }
}
