package com.instalite;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("O2Vo6m8RT0xQZYWE60sw0dadF0LVaJoyui7kJIcR")
                .clientKey("Hfd1Hqoho9syt1uepPDiiLCC6fBGHPiR6DivPfHX")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
