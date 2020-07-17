package com.runjing.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @Created: xiaoyu  on 2017.10.26 11:41.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_2.2 on 2017.10.26 11:41.
 * @Blog:http://blog.csdn.net/noteschapter
 * @Github:https://github.com/mrxiaoyu100001
 * @Resources:
 * @Remark:TODO:就是这样任性，就是这样写代码，不爽一起来。
 */

public class ScreenViewUtil {

    /**
     * 根据坐标获取相对应的子控件<br>
     * 在Activity使用
     *
     * @param x坐标
     * @param y坐标
     * @return 目标View
     */
    public static View getViewAtActivity(View root, float x, float y) {
        // 从Activity里获取容器
        return findViewByXY(root, x, y);
    }

    /**
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static View findViewByXY(View view, float x, float y) {
        View targetView = null;
        if (view instanceof ViewGroup) {
            // 父容器,遍历子控件
            ViewGroup v = (ViewGroup) view;
            for (int i = 0; i < v.getChildCount(); i++) {
                targetView = findViewByXY(v.getChildAt(i), x, y);
                if (targetView != null) {
                    break;
                }
            }
        } else {
            targetView = getTouchTarget(view, x, y);
        }
        return targetView;

    }

    /**
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static View getTouchTarget(View view, float x, float y) {
        View targetView = null;
        // 判断view是否可以聚焦
        ArrayList<View> TouchableViews = view.getTouchables();
        for (View child : TouchableViews) {
            if (isTouchPointInView(child, x, y)) {
                targetView = child;
                break;
            }
        }
        return targetView;
    }

    /**
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static boolean isTouchPointInView(View view, float x, float y) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (view.isClickable() && y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static boolean isTouchPointInView(View viewGroup, View view, int x, int y) {
        int[] fatherLeftTop = {0, 0}; // 父控件在屏幕中的绝对坐标
        viewGroup.getLocationOnScreen(fatherLeftTop);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0] - fatherLeftTop[0];
        int top = location[1] - fatherLeftTop[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        Rect R = new Rect(left, top, right, bottom);
        if (view.isClickable() && R.contains(x, y)) {
            return true;
        }
        return false;
    }
}
