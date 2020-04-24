package com.shouzhong.shadowlayout.demo.test;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.IntentFilter;

public class ProcessMessage {

    private static Application app;

    static Application getApp() {
        if (app == null) {
            try {
                @SuppressLint("PrivateApi")
                Class<?> activityThread = Class.forName("android.app.ActivityThread");
                Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
                Object app = activityThread.getMethod("getApplication").invoke(thread);
                ProcessMessage.app = (Application) app;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return app;
    }

    public static void init(Application app) {
        ProcessMessage.app = app;
        String action = ProcessMessage.getApp().getPackageName() + ".action.PROCESS_MESSAGE";
        String permission = ProcessMessage.getApp().getPackageName() + ".permission.RECEIVER_PROCESS_MESSAGE";
        IntentFilter filter = new IntentFilter(action);
        ProcessMessage.getApp().registerReceiver(new MessageReceiver(), filter, permission, null);
        ActivityUtils.init();
        EventBusUtils.init();
    }
}
