package com.runjing.widget.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.runjing.wineworld.R;

import java.util.List;

/**
 * Created by xiaoyu on 2016/5/11.
 * Describe：
 */
public class SearchPopupWindow extends PopupWindow {

    private View popView;
    private LinearLayout ll_bg;
    private Context mContext;
    private LayoutInflater mInflater;
    private static SearchPopupWindow popupWindow;
    private Display display;


    public SearchPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public SearchPopupWindow builder(View view) {

        popupWindow = new SearchPopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_in_search, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.frag_ll_search);
        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(false);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
        ll_bg.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, (int) (display
                .getWidth() * 0.5)));
        popupWindow.showAsDropDown(view, 0, 0);
        popupWindow.update();
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    public SearchPopupWindow refresh(List<Object> data) {

        return this;
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
