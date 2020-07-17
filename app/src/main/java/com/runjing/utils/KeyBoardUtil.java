package com.runjing.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.10 19:32.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.10 19:32.
 * @Remark:
 */
public class KeyBoardUtil {
    private static ViewGroup viewGroup;
    /**
     * Initialization method
     *
     * @param activity
     */
    public static void init(Activity activity) {
        new KeyBoardUtil(activity, null);
    }

    /**
     * Can pass the outer layout
     *
     * @param activity
     * @param content
     */
    public static void init(Activity activity, ViewGroup content) {
        new KeyBoardUtil(activity, content);
    }

    /**
     * Forced hidden keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (null == activity) {
            throw new RuntimeException("参数错误");
        }
        View view = activity.getCurrentFocus();
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Forced hidden keyboard
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            throw new RuntimeException("参数错误");
        }
    }

    /**
     * Forced hidden keyboard
     *
     * @param dialog
     */
    public static void hideDialogSoftKeyboard(Dialog dialog) {
        if (null == dialog) {
            throw new RuntimeException("参数错误");
        }
        View view = dialog.getCurrentFocus();
        if (null != view) {
            InputMethodManager inputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param activity
     */
    private KeyBoardUtil(final Activity activity, ViewGroup content) {
        if (content == null) {
            content = (ViewGroup) activity.findViewById(android.R.id.content);
        }
        getScrollView(content, activity);
        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                dispatchTouchEvent(activity, motionEvent);

                return false;
            }
        });
    }

    private void getScrollView(ViewGroup viewGroup, final Activity activity) {
        if (null == viewGroup) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ScrollView) {
                ScrollView newDtv = (ScrollView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof AbsListView) {
                AbsListView newDtv = (AbsListView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof RecyclerView) {
                RecyclerView newDtv = (RecyclerView) view;
                newDtv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            } else if (view instanceof ViewGroup) {
                this.getScrollView((ViewGroup) view, activity);
            }

            if (view.isClickable() && view instanceof TextView && !(view instanceof EditText)) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        dispatchTouchEvent(activity, motionEvent);
                        return false;
                    }
                });
            }
        }
    }

    /**
     * @param mActivity
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(Activity mActivity, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = mActivity.getCurrentFocus();
            if (null != v && isShouldHideInput(v, ev)) {
                hideSoftInput(mActivity, v.getWindowToken());
            }
        }
        return false;
    }

    /**
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            Rect rect = new Rect();
            v.getHitRect(rect);
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param mActivity
     * @param token
     */
    private void hideSoftInput(Activity mActivity, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 隐鲹软件盘
     *
     * @param mContext
     * @param editText
     */
    public static void hideSoftInput(Context mContext, EditText editText) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != editText) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 判断软键盘 是否隐藏
     *
     * @param mContext
     * @param editText
     * @return
     */
    public static boolean hideKeyboard(Activity mContext, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //因为是在fragment下，所以用了getView()获取view，也可以用findViewById（）来获取父控件
        if (editText != null && inputMethodManager.isActive(editText)) {
            //强制获取焦点，不然getActivity().getCurrentFocus().getWindowToken()会报错
            editText.requestFocus();
            inputMethodManager.hideSoftInputFromWindow(mContext.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            inputMethodManager.restartInput(editText);
            return true;
        }
        return false;
    }

    /**
     * 判断软键盘 是否隐藏
     *
     * @param mContext
     * @param view
     * @return
     */
    public static boolean hideKeyboard(Activity mContext, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //因为是在fragment下，所以用了getView()获取view，也可以用findViewById（）来获取父控件
        if (view != null && inputMethodManager.isActive(view)) {
            //强制获取焦点，不然getActivity().getCurrentFocus().getWindowToken()会报错
            view.requestFocus();
            inputMethodManager.hideSoftInputFromWindow(mContext.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            inputMethodManager.restartInput(view);
            return true;
        }
        return false;
    }

    /**
     * 隐鲹软件盘
     *
     * @param mContext
     * @param view
     */
    public static void hideSoftInput(Context mContext, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != view) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    public static void hideSoftInput(Context context, IBinder token) throws Throwable {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param viewGroup
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(ViewGroup viewGroup, View v, MotionEvent event) throws Throwable {
        if (viewGroup != null) KeyBoardUtil.viewGroup = viewGroup;
        if (v != null && (v instanceof EditText)) {
            int[] fatherLeftTop = {0, 0}; // 父控件在屏幕中的绝对坐标
            viewGroup.getLocationOnScreen(fatherLeftTop);

            int[] chileLeftTop = {0, 0};
            v.getLocationOnScreen(chileLeftTop);
            // 子控件在屏幕中的绝对坐标需要减去父控件的绝对坐标才能匹配真正的坐标
            int left = chileLeftTop[0] - fatherLeftTop[0];
            int top = chileLeftTop[1] - fatherLeftTop[1];
            int right = left + v.getWidth();
            int bottom = top + v.getHeight();
            if (new RectF(left, top, right, bottom).contains(event.getX(), event.getY())) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * @param viewGroup
     * @param event
     * @return
     */
    public static boolean viewIsEditText(ViewGroup viewGroup, MotionEvent event) throws Throwable {
        boolean isNextEditText = false;
        View clickView = getViewAtViewGroup(viewGroup, (int) event.getX(), (int) event.getY());
        if (clickView != null && clickView instanceof EditText) isNextEditText = true;
        return !isNextEditText;
    }

    /**
     * 根据坐标获取相对应的子控件 在重写ViewGroup使用
     *
     * @param x 坐标
     * @param y 坐标
     * @return 目标View
     */
    public static View getViewAtViewGroup(View view, int x, int y) throws Throwable{
        return findViewByXY(view, x, y);
    }

    private static View findViewByXY(View view, int x, int y) throws Throwable{
        View targetView = null;
        if (view instanceof ViewGroup) {
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

    private static View getTouchTarget(View view, int x, int y) throws Throwable {
        // 判断view是否可以聚焦
        ArrayList<View> TouchableViews = view.getTouchables();
        for (View child : TouchableViews) {
            if (isTouchPointInView(child, x, y)) {
                return child;
            }
        }
        return null;
    }


    /**
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static boolean isTouchPointInView(View view, int x, int y) {
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
