package com.example.whatsappclone;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CustomEditText extends FrameLayout {
    View v;
    ImageView emoji, attach, cam;
    EditText messsge;
    String msg="";
    public CustomEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void init(){
        v= LayoutInflater.from(getContext()).inflate(R.layout.custom_edittext,this,true);
        emoji = v.findViewById(R.id.ivEmoji);
        messsge=v.findViewById(R.id.et_custom_msg);
        attach = v.findViewById(R.id.iv_attatch);
        cam = v.findViewById(R.id.iv_cam);

        msg=messsge.getText().toString();
    }
}
