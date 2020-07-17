package com.runjing.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ymx on 2017/4/12.
 * 单例模式吐司
 */
public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
