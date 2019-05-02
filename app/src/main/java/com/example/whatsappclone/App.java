package com.example.whatsappclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(
                new Parse.Configuration.Builder(this)
                        .applicationId("Xf7VER6Rlo0ERP0ur2D3yxr9faxyPp36PQ2gJLrg")
                        // if defined
                        .clientKey("N9Qs0wdKy17zeWNFVFlWHmtB0d4WxauKiuN9NIPN")
                        .server("https://parseapi.back4app.com")
                        .build()
        );
    }
}
