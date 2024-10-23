package com.hpl.my_music.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tencent.mmkv.MMKV;

/**
 * 偏好设置工具类
 *
 */
public class PreferenceUtil {
    /**
     * 是否显示引导界面key
     */
    private static final String SHOW_GUIDE = "SHOW_GUIDE";

    private static PreferenceUtil instance;
    private final Context context;
    private final MMKV preference;


    /**
     * 构造方法
     *
     * @param context
     */
    public PreferenceUtil(Context context) {
        //保存上下文
        this.context = context.getApplicationContext();

        //初始化腾讯开源的高性能key,value存储，可以写到单独的类中
        preference= MMKV.defaultMMKV();


        //这样写有内存泄漏
        //因为当前工具类不会马上释放
        //如果当前工具类引用了界面实例
        //当界面关闭后
        //因为界面对应在这里还有引用
        //所以会导致界面对象不会被释放
        //this.context = context;
    }


    /**
     * 获取偏好设置单例
     *这里的contex翻译的话是上下文，activity就是contex，
     * @param context
     * @return
     */
    public synchronized static PreferenceUtil getInstance(Context context) {
        if (instance == null) {
            //之所以把这个单利叫做instence 是因为这是惯例
            instance = new PreferenceUtil(context);
        }
        return instance;
    }
    /**
     * 是否显示引导界面
     *
     * @return
     */
    public boolean isShowGuide() {
        return getBoolean(SHOW_GUIDE, true);
    }

    /**
     * 设置是否显示引导界面
     *
     * @param value
     */
    public void setShowGuide(boolean value) {
        putBoolean(SHOW_GUIDE, value);
    }
    /**
     * 获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private boolean getBoolean(String key, boolean defaultValue) {
        return preference.getBoolean(key, defaultValue);
    }





    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    private void putBoolean(String key, boolean value) {
        preference.edit().putBoolean(key, value).apply();
    }



}
