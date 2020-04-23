package com.shouzhong.shadowlayout.demo.test;

import android.text.TextUtils;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bean {
    private static final int BRIDGE = 0x40;
    private static final int SYNTHETIC = 0x1000;
    private static final int MODIFIERS_IGNORE = Modifier.ABSTRACT | Modifier.STATIC | BRIDGE | SYNTHETIC;

    static final Map<String, List<String>> typesBySubscriber = new HashMap<>();
    static final Map<String, String> stickyEvents = new HashMap<>();

    static boolean hasSubscriberForEvent(String s) {
        if (TextUtils.isEmpty(s)) return false;
        for (List<String> list : typesBySubscriber.values()) {
            if (list == null) continue;
            for (String type : list) {
                if (TextUtils.equals(type, s)) return true;
            }
        }
        return false;
    }

    static boolean isRegistered(String s) {
        if (TextUtils.isEmpty(s)) return false;
        return typesBySubscriber.containsKey(s);
    }

     static void register(String s) {
        if (TextUtils.isEmpty(s)) return;
        List<String> types = findMethods(s);
        if (types == null) return;
        typesBySubscriber.put(s, types);
    }

    static void unregister(String s) {
        if (TextUtils.isEmpty(s)) return;
        typesBySubscriber.remove(s);
    }

    static void postSticky(String cls, String data) {
        if (TextUtils.isEmpty(cls)) return;
        stickyEvents.put(cls, data);
    }

    static void removeStickyEvent(String cls) {
        if (TextUtils.isEmpty(cls)) return;
        stickyEvents.remove(cls);
    }

    static void removeAllStickyEvents() {
        stickyEvents.clear();
    }

    private static List<String> findMethods(String s) {
        try {
            Class cls = Class.forName(s.substring(0, s.indexOf(';')));
            Method[] methods;
            try {
                methods = cls.getDeclaredMethods();
            } catch (Exception e) {
                methods = cls.getMethods();
            }
            if (methods == null || methods.length == 0) return null;
            List<String> list = null;
            for (Method method : methods) {
                int modifiers = method.getModifiers();
                if ((modifiers & Modifier.PUBLIC) == 0 || (modifiers & MODIFIERS_IGNORE) != 0) continue;
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes == null || parameterTypes.length != 1) continue;
                Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                if (subscribeAnnotation == null) continue;
                if (list == null) list = new ArrayList<>();
                list.add(parameterTypes[0].getName());
            }
            return null;
        } catch (Exception e) {}
        return null;
    }
}
