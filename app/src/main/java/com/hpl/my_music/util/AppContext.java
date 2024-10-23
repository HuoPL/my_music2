package com.hpl.my_music.util;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

/**
 * 全局Application
 */
public class AppContext extends Application {
    private static final String TAG = "AppContext";

    @Override
    public void onCreate() {
        super.onCreate();
        initMMKV();
    }

    /**
     * 初始化 腾讯开源的高性能keyValue存储，用来替代系统的SharedPreferences
     */
    private void initMMKV() {
        String rootDir = MMKV.initialize(this);
        Log.d(TAG, "initMMKV: " + rootDir);
    }
}
