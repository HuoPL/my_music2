package com.hpl.my_music.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 偏好设置工具类,这种偏好设置数据较少所以就没有保存到数据库中，数据多的时候就要保存在数据库中
 * 是否登录了，是否显示引导界面，用户Id
 */
public class DefaultPreferenceUtil {
    /**
     * 偏好设置文件名称
     */
    private static final String NAME = "ixuea_my_cloud_music";
    private static final String KEY_MOBILE_NETWORK_PLAY = "mobile_network_play";

    private static final String TERMS_SERVICE = "TERMS_SERVICE";

    private static DefaultPreferenceUtil instance;
    private final Context context;
    private final SharedPreferences preference;

    /**
     * 构造方法
     *
     * @param context
     */
    public DefaultPreferenceUtil(Context context) {
        //保存上下文
        this.context = context.getApplicationContext();

        //这样写有内存泄漏
        //因为当前工具类不会马上释放
        //如果当前工具类引用了界面实例
        //当界面关闭后
        //因为界面对应在这里还有引用
        //所以会导致界面对象不会被释放
        //this.context = context;

        //获取系统默认偏好设置，在设置界面保存的值就可以这样获取
        preference = PreferenceManager.getDefaultSharedPreferences(this.context);
        //这里传入的contex就是上面的那个contex  防止内存泄漏的那个

        //自定义名称
//        preference = this.context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取偏好设置单例
     *这里的contex翻译的话是上下文，activity就是contex，
     * @param context
     * @return
     */
    public synchronized static DefaultPreferenceUtil getInstance(Context context) {
        if (instance == null) {
            //之所以把这个单利叫做instence 是因为这是惯例
            instance = new DefaultPreferenceUtil(context);
        }
        return instance;
    }

    /**
     * 设置同意了用户协议
     */
    public void setAcceptTermsServiceAgreement() {
        putBoolean(TERMS_SERVICE, true);
    }

    /**
     * 获取是否同意了用户条款
     *
     * @return
     */
    public boolean isAcceptTermsServiceAgreement() {
        return preference.getBoolean(TERMS_SERVICE, false);
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    private void putBoolean(String key, boolean value) {
        preference.edit().putBoolean(key, value).commit();
    }

    /**
     * 移动网络下是否播放
     *
     * @return 默认不能播放
     */
    public boolean isMobileNetworkPlay() {
        return preference.getBoolean(KEY_MOBILE_NETWORK_PLAY, false);
    }
}
