package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatusFragment extends Fragment {
    FloatingActionButton floatingCamera, floatingWriteStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.status_fragment,null);
        floatingCamera = v.findViewById(R.id.floating_camera);
        floatingWriteStatus = v.findViewById(R.id.floating_write_status);
        floatingCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCam = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCam);
            }
        });
        floatingWriteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), WriteStatus.class);
                startActivity(i);
            }
        });
        return v;
    }
}
