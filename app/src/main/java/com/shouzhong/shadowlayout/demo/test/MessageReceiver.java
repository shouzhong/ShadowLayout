package com.shouzhong.shadowlayout.demo.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String type = intent.getStringExtra("type");
            if (TextUtils.equals(type, "eventbus")) {
                int pid = intent.getIntExtra("pid", 0);
                String action = intent.getStringExtra("action");
                if (TextUtils.equals(action, "init")) {
                    if (!Utils.isMainProcess()) return;
                    for (String key : EventBusCache.TYPE_BY_SUBSCRIBER.keySet()) {
                        EventBusUtils.sendBroadcast(pid, "register", key);
                    }
                    for (String key : EventBusCache.STICKY_EVENTS.keySet()) {
                        EventBusUtils.sendBroadcast(pid, "postSticky", key + ";" + EventBusCache.STICKY_EVENTS.get(key));
                    }
                    return;
                }
                if (pid != 0 && pid != Process.myPid()) return;
                String data = intent.getStringExtra("data");
                if (TextUtils.equals(action, "register")) {
                    EventBusCache.register(data);
                    return;
                }
                if (TextUtils.equals(action, "unregister")) {
                    EventBusCache.unregister(data);
                    return;
                }
                if (TextUtils.equals(action, "post")) {
                    String[] ss = data.split(";", 2);
                    EventBusUtils.get().post(Utils.getGson().fromJson(ss[1], Class.forName(ss[0])));
                    return;
                }
                if (TextUtils.equals(action, "postSticky")) {
                    String[] ss = data.split(";", 2);
                    EventBusUtils.get().postSticky(Utils.getGson().fromJson(ss[1], Class.forName(ss[0])));
                    EventBusCache.postSticky(ss[0], ss[1]);
                    return;
                }
                if (TextUtils.equals(action, "removeStickyEvent")) {
                    EventBusUtils.get().removeStickyEvent(Class.forName(data));
                    EventBusCache.removeStickyEvent(data);
                    return;
                }
                if (TextUtils.equals(action, "removeAllStickyEvents")) {
                    EventBusUtils.get().removeAllStickyEvents();
                    EventBusCache.removeAllStickyEvents();
                    return;
                }
            }
            if (TextUtils.equals(type, "activity")) {
                int pid = intent.getIntExtra("pid", 0);
                String action = intent.getStringExtra("action");
                if (TextUtils.equals(action, "init")) {
                    if (!Utils.isMainProcess()) return;
                    for (String s : ActivityCache.CACHE.keySet()) {
                        ActivityUtils.sendBroadcast(pid, "put", s + "->" + ActivityCache.CACHE.get(s));
                    }
                    return;
                }
                if (pid != 0 && pid != Process.myPid()) return;
                String data = intent.getStringExtra("data");
                if (TextUtils.equals(action, "put")) {
                    String[] ss = data.split("->", 2);
                    ActivityCache.put(ss[0], ss[1]);
                    return;
                }
                if (TextUtils.equals(action, "remove")) {
                    ActivityCache.remove(data);
                    return;
                }
                if (TextUtils.equals(action, "finish")) {
                    for (Activity act : ActivityUtils.ACTIVITIES.keySet()) {
                        if (TextUtils.equals(data, act.getClass().getName())) act.finish();
                    }
                    return;
                }
                if (TextUtils.equals(action, "exit")) {
                    if (!TextUtils.isEmpty(data) && !TextUtils.equals(data, Utils.getCurrentProcessName())) return;
                    for (Activity act : ActivityUtils.ACTIVITIES.keySet()) {
                        act.finish();
                    }
                    return;
                }
            }
        } catch (Exception e) { }
    }
}
