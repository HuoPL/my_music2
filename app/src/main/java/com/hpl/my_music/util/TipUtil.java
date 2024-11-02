package com.hpl.my_music.util;

import com.hpl.superui.toast.SuperToast;

/**
 * 提示工具类
 * <p>
 * 主要是判断是否有placeholder，如果有就使用该控件显示提示
 * 如果没有就使用toast提示
 */
public class TipUtil {
    /**
     * 显示错误提示
     *
     * @param toastResource
     */
    public static void showError(int toastResource) {
        SuperToast.error(toastResource);
    }

    /**
     * 显示错误提示
     *
     * @param toast
     */
    public static void showError(String toast) {
        SuperToast.error(toast);
    }
}
