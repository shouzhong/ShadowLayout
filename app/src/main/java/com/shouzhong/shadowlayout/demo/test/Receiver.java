package com.shouzhong.shadowlayout.demo.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra("type");
        if (TextUtils.equals(type, "eventbus")) {
            int pid = intent.getIntExtra("pid", 0);
            String action = intent.getStringExtra("action");
            if (TextUtils.equals(action, "init")) {
                if (!Utils.isMainProcess()) return;
                for (String key : Bean.stickyEvents.values()) {

                }
                return;
            }
            if (pid != 0 && pid != Process.myPid()) return;
            if (TextUtils.equals(action, "register")) {
                return;
            }
        }
    }
}
