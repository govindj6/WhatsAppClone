package com.example.whatsappclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.io.File;
import java.io.InputStream;

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
    byte[] bytes;

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

        name = UserManager.getInstance().getName();
        final String status = UserManager.getInstance().getStatus();
        int mobile = UserManager.getInstance().getMobileNumber();
        ParseFile dp = UserManager.getInstance().getProfilePic();

        Glide.with(ProfileActivity.this).load(dp).into(profileDp);

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
        UserManager.getInstance().setName(etDialog.getText().toString(), new UserManager.ParseTaskCallBack() {
            @Override
            public void onTaskDone() {

            }

            @Override
            public void onTaskFailed(Exception e) {

            }
        });
    }

    private void onUpdateStatus() {
        UserManager.getInstance().setStatus(etDialog.getText().toString(), new UserManager.ParseTaskCallBack() {
            @Override
            public void onTaskDone() {

            }

            @Override
            public void onTaskFailed(Exception e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    InputStream inputStream = ProfileActivity.this.getContentResolver().openInputStream(data.getData());
                    bytes = new byte[inputStream.read()];
                    onUpdateDp(bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onUpdateDp(byte[] imageData) {
        final ParseFile parseFile = new ParseFile(imageData);
        parseFile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    UserManager.getInstance().setProfilePicture(parseFile, new UserManager.ParseTaskCallBack() {
                        @Override
                        public void onTaskDone() {
                            Toast.makeText(ProfileActivity.this, "Successfully image change", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onTaskFailed(Exception e) {
                            Toast.makeText(ProfileActivity.this, "Failed to change image", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to save image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
