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
public class BackPopupWindow extends PopupWindow {

    private View popView;
    private TextView tv_title;
    private TextView tv_msg;
    private EditText et_input;
    private LinearLayout ll_Group, ll_bg;
    private ImageView iv_line, img_line;
    private Button btn_neg, btn_pos;
    private Context mContext;
    private LayoutInflater mInflater;
    private static BackPopupWindow popupWindow;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showEditText = false;
    private boolean showLayout = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private Display display;


    public BackPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static BackPopupWindow getInstance(Context context) {

        if (popupWindow == null) {
            popupWindow = new BackPopupWindow(context);
        }
        return popupWindow;
    }

    public BackPopupWindow builder() {

        popupWindow = new BackPopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_popupwindow_common, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.ll_bg);
        tv_title = (TextView) popView.findViewById(R.id.tv_title);
        tv_msg = (TextView) popView.findViewById(R.id.tv_msg);
        et_input = (EditText) popView.findViewById(R.id.et_input);
        ll_Group = (LinearLayout) popView.findViewById(R.id.ll_Group);
        iv_line = (ImageView) popView.findViewById(R.id.iv_line);
        img_line = (ImageView) popView.findViewById(R.id.img_line);
        btn_neg = (Button) popView.findViewById(R.id.btn_neg);
        btn_pos = (Button) popView.findViewById(R.id.btn_pos);
        tv_title.setVisibility(View.GONE);
        tv_msg.setVisibility(View.GONE);
        et_input.setVisibility(View.GONE);
        ll_Group.setVisibility(View.GONE);
        btn_neg.setVisibility(View.GONE);
        btn_pos.setVisibility(View.GONE);
        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体可点击
//        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(true);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
        ll_bg.setLayoutParams(new LayoutParams((int) (display
                .getWidth() * 0.75), LayoutParams.WRAP_CONTENT));
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        popupWindow.update();
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    public BackPopupWindow setTitle(String title) {
        showTitle = true;
        tv_title.setText(title);
        return this;
    }


    public BackPopupWindow setMsg(String message) {
        showMsg = true;
        tv_msg.setText(message);
        return this;
    }

    public BackPopupWindow setNeg(String neg, final View.OnClickListener listener) {
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

    public BackPopupWindow setPos(String pos, final View.OnClickListener listener) {
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

    public static void showDis() {
        popupWindow.dismiss();
    }

    public BackPopupWindow setEditText(String msg) {
        showEditText = true;
        if ("".equals(msg)) {
            et_input.setHint("内容");
        } else {
            et_input.setText(msg);
        }
        return this;
    }

    public String getInput() {
        return et_input.getText().toString();
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            tv_title.setText("提示");
            tv_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            tv_title.setVisibility(View.VISIBLE);
        }

        if (showEditText) {
            et_input.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            tv_msg.setVisibility(View.VISIBLE);
        }

        if (showLayout) {
            ll_Group.setVisibility(View.VISIBLE);
            iv_line.setVisibility(View.GONE);
        }

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
        public void onPositiveButtonClick();

    }

    /**
     * 自定义对话框的事件接口回调
     */
    public interface PopupWindowSingleCallBack {

        /**
         * 点击确认接口回调
         */
        public void onSingleButtonClick();

    }
}
