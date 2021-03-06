package com.runjing.utils;

import android.content.Context;

import android.view.View;

import com.runjing.base.BasePop;
import com.runjing.widget.pop.BackPopupWindow;
import com.runjing.widget.pop.LoginReceivePopupWindow;
import com.runjing.widget.pop.MiddlePopupWindow;
import com.runjing.widget.pop.ShoptPopupWindow;

import java.util.List;


/**
 * 公共弹出框
 *
 * @author zm
 */
public class PopupWindowUtil {
    public PopupWindowUtil mPopUtils;
    public static MiddlePopupWindow middleWindow;
    private static PopupWindowUtil instance;

    /**
     * 显示文字信息的pop
     *
     * @param title
     * @param msg
     * @param imgShow
     * @param popWindowCallBack
     */
    public static void showPopWindow(Context context, String title, String msg, boolean imgShow, final MiddlePopupWindow.PopupWindowCallBack popWindowCallBack) {

        MiddlePopupWindow mWindow = new MiddlePopupWindow(context).builder().setTitle(title).setMsg(msg).setNeg("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onNegativeButtonClick();
            }
        });
        mWindow.setPos("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onPositiveButtonClick();
            }
        });


        mWindow.show();
    }

    /**
     * @param context
     * @param msg
     * @param popupWindowSingleCallBack
     */
    public static void showSingleWindow(Context context, String msg, final MiddlePopupWindow.PopupWindowSingleCallBack popupWindowSingleCallBack) {
        MiddlePopupWindow mWindow = new MiddlePopupWindow(context).builder().setTitle(msg).setNeg("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowSingleCallBack.onSingleButtonClick();
            }
        });
        mWindow.show();
    }

    /**
     * @param context
     * @param msg
     * @param popupWindowSingleCallBack
     */
    public static void SingleWindow(Context context, String msg, final MiddlePopupWindow.PopupWindowSingleCallBack popupWindowSingleCallBack) {
        BackPopupWindow mWindow = new BackPopupWindow(context).builder().setTitle(msg).setNeg("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowSingleCallBack.onSingleButtonClick();
            }
        });
        mWindow.show();
    }

    /**
     * 显示文字信息的pop
     *
     * @param title
     * @param msg
     * @param imgShow
     * @param popWindowCallBack
     */
    public static void showPopWindow(Context context, String title, String msg, String sure, String cancel, boolean imgShow, final MiddlePopupWindow.PopupWindowCallBack popWindowCallBack) {

        MiddlePopupWindow mWindow = new MiddlePopupWindow(context).builder().setTitle(title).setMsg(msg).setNeg(cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onNegativeButtonClick();
            }
        });
        mWindow.setPos(sure, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onPositiveButtonClick();
            }
        });
        mWindow.show();
    }

    /**
     * @param context
     * @param popWindowCallBack
     */
    public static void showPopShopCar(Context context, View view, List<BasePop> list, final ShoptPopupWindow.PopupWindowCallBack popWindowCallBack) {
        try {
            ShoptPopupWindow mWindow = ShoptPopupWindow.getInstance(context).builder();
            mWindow.showUp(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 收货地址
     * @param context
     * @param cancel
     * @param sure
     * @param popWindowCallBack
     */
    public static void showPopReceive(Context context, String cancel, String sure, final LoginReceivePopupWindow.PopupWindowCallBack popWindowCallBack) {

        LoginReceivePopupWindow mWindow = new LoginReceivePopupWindow(context).builder();
        mWindow.setNeg(cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onNegativeButtonClick();
            }
        });
        mWindow.setPos(sure, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowCallBack.onPositiveButtonClick(mWindow.getInputMsg());
            }
        });
        mWindow.show();
    }
}
