package com.example.portomoti.trustbusse301project;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("sg4SlOe1Oa0eZnYpqP8BHKpO64c014dY0NAUEzy2")
                .clientKey("Pjue0T2ba3MHye3Fm8LL2TkfVrVT6vxz87vEWJKe")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
