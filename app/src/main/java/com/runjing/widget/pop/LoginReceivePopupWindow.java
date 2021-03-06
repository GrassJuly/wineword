package com.runjing.widget.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.runjing.wineworld.R;


/**
 * Created by xiaoyu on 2016/5/11.
 * Describe： 图片选择框
 */
public class LoginReceivePopupWindow extends PopupWindow {

    private View popView;
    private EditText et_input;
    private LinearLayout ll_Group, ll_bg;
    private ImageView iv_line, img_line;
    private Button btn_neg, btn_pos;
    private Context mContext;
    private LayoutInflater mInflater;
    private static LoginReceivePopupWindow popupWindow;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private Display display;


    public LoginReceivePopupWindow(Context context) {
        super(context);
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static LoginReceivePopupWindow getInstance(Context context) {

        if (popupWindow == null) {
            popupWindow = new LoginReceivePopupWindow(context);
        }
        return popupWindow;
    }

    public LoginReceivePopupWindow builder() {

        popupWindow = new LoginReceivePopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_popupwindow_receive, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.ll_bg);
        et_input = (EditText) popView.findViewById(R.id.et_input);
        ll_Group = (LinearLayout) popView.findViewById(R.id.ll_Group);
        iv_line = (ImageView) popView.findViewById(R.id.iv_line);
        img_line = (ImageView) popView.findViewById(R.id.img_line);
        btn_neg = (Button) popView.findViewById(R.id.btn_neg);
        btn_pos = (Button) popView.findViewById(R.id.btn_pos);
        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(true);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
//        ll_bg.setLayoutParams(new LayoutParams((int) (display
//                .getWidth() * 0.75), LayoutParams.WRAP_CONTENT));
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        popupWindow.update();
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    public LoginReceivePopupWindow setNeg(String neg, final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(neg)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(neg);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                popupWindow.dismiss();
            }
        });
        return this;

    }

    public LoginReceivePopupWindow setPos(String pos, final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(pos)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(pos);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                popupWindow.dismiss();
            }
        });
        return this;
    }

    public String getInputMsg() {
        return et_input.getText().toString();
    }

    private void setLayout() {
        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_neg.setVisibility(View.VISIBLE);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
        }
    }

    public void show() {
        setLayout();
    }

    /**
     * 自定义对话框的事件接口回调
     */
    public interface PopupWindowCallBack {

        /**
         * 点击取消接口回调
         */
        public void onNegativeButtonClick();

        /**
         * 点击确认接口回调
         */
        public void onPositiveButtonClick(String msg);

    }

}
