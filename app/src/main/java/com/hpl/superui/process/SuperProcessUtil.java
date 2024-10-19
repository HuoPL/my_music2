package com.hpl.superui.process;
import android.os.Process;

/**
 * 这是一个和进程相关的工具类
 */

public class SuperProcessUtil {
    public static void killApp() {
        Process.killProcess(Process.myPid());
    }
}
