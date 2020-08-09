package com.runjing;

import android.app.Activity;
import android.app.Application;

import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;

import org.runjing.rjframe.BuildConfig;

import java.util.LinkedList;
import java.util.List;

import androidx.multidex.MultiDex;

/**
 * @Created by xiaoyu on 2017/1/10.
 * @Describe：全局变量
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/10.
 * @Remark:
 */
public class MyApplication extends Application {

    /*全局常量*/
    public static MyApplication contextApp;
    public static List<Activity> activityList;
    public static String isLimitUpdate;

    @Override
    public void onCreate() {
        super.onCreate();
        contextApp = this;
        activityList = new LinkedList<Activity>();
        MMKV.initialize(this);
        //线上检测
        CrashReport.initCrashReport(getApplicationContext(), "92a7c20823", true);
        MultiDex.install(this);
        KLog.init(BuildConfig.DEBUG, "wineworld :::::  ");
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        // 遍历所有Activity并finish()
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

}
