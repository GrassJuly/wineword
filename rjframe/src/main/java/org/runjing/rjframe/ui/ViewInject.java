package org.runjing.rjframe.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast工具类，用于优化Toast提示
 * Created by zm on 2016/4/27.
 */
public class ViewInject {

    public static Toast mToast = null;
    private ViewInject() {
    }

    /**
     * 显示一个toast
     *
     * @param msg
     */
    public static void toast(String msg) {
        try {
            toast(RJActivityStack.create().topActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 长时间显示一个toast
     *
     * @param msg
     */
    public static void longToast(String msg) {
        try {
            longToast(RJActivityStack.create().topActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 长时间显示一个toast
     *
     * @param msg
     */
    public static void longToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示一个toast
     *
     * @param msg
     */
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示居中默认toast
     *
     * @param context
     * @param toast
     */
    public static void showCenterToast(Context context, String toast) {
        if (mToast == null) {
            mToast = Toast.makeText(context, toast, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(toast);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }




}
