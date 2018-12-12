package com.mimyboutique.android.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BwUJw4HSPsKOvu9Rtp0WRTqTtSALYVamjrXC3vkt")
                // if defined
                .clientKey("6zhw1nO25CX4a1eCu3D0aYDiKjw2PimSfZ3YxUMO")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
