package com.hpl.my_music.activity;

import android.content.Intent;

/**
 * 这是一个通用的界面逻辑
 */
public class BaseCommonActivity extends  BaseActivity{
    /**
     * 启动界面
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 启动界面并关闭当前界面
     *
     * @param clazz
     */
    protected void startActivityAfterFinishThis(Class<?> clazz) {
        startActivity(clazz);

        finish();
    }

}
