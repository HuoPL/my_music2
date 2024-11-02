package com.hpl.my_music.util;

import android.app.Application;
import android.util.Log;

import com.hpl.superui.toast.SuperToast;
import com.tencent.mmkv.MMKV;

/**
 * 全局Application
 */
public class AppContext extends Application {
    private static final String TAG = "AppContext";
    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initMMKV();

        //初始化toast工具类，也是全局只需要初始化一次
        SuperToast.init(getApplicationContext());
    }

    /**
     * 初始化 腾讯开源的高性能keyValue存储，用来替代系统的SharedPreferences
     */
    private void initMMKV() {
        String rootDir = MMKV.initialize(this);
        Log.d(TAG, "initMMKV: " + rootDir);
    }
}
