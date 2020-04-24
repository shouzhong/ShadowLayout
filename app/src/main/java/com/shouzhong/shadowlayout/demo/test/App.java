package com.shouzhong.shadowlayout.demo.test;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessMessage.init(this);
    }
}
