package com.runjing.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.runjing.base.BasePop;
import com.runjing.wineworld.R;

import org.runjing.rjframe.widget.RJAdapter;

import java.util.Collection;

/**
 * Created by xiaoyu on 2016/5/11.
 * Describe：
 */
public class MiddleListPopupWindow<T extends BasePop> extends PopupWindow {

    private View popView;
    private TextView tv_title;
    private TextView tv_succ;
    private TextView tv_back;
    private ListView lv_content;
    private ImageView iv_line;
    private LinearLayout ll_bg;
    private LinearLayout ll_tab;
    private Context mContext;
    private LayoutInflater mInflater;
    private static MiddleListPopupWindow popupWindow;
    private boolean showTitle = false;
    private boolean showSuc = false;
    private boolean showTab = false;
    private Display display;
    private boolean showBack = false;
    private Collection<T> mList;
    private double ratio_height;
    private double ratio_width;


    public MiddleListPopupWindow(Context context) throws Exception {
        super(context);
        ratio_height = 0.8;
        ratio_width = 0.6;
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static MiddleListPopupWindow getInstance(Context context) throws Exception {
        if (popupWindow == null) {
            popupWindow = new MiddleListPopupWindow(context);
        }
        return popupWindow;
    }

    public MiddleListPopupWindow builder() throws Exception {
        popupWindow = new MiddleListPopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_middlelist, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.ll_bg);
        ll_tab = (LinearLayout) popView.findViewById(R.id.lay_ll_tab);
        tv_title = (TextView) popView.findViewById(R.id.tv_title);
        tv_succ = (TextView) popView.findViewById(R.id.tv_succ);
        tv_back = (TextView) popView.findViewById(R.id.tv_back);
        iv_line = (ImageView) popView.findViewById(R.id.img_line);
        lv_content = (ListView) popView.findViewById(R.id.lay_lv_content);
        tv_title.setVisibility(View.GONE);
        tv_succ.setVisibility(View.GONE);
        tv_back.setVisibility(View.GONE);
        iv_line.setVisibility(View.GONE);
        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(true);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
        ll_bg.setLayoutParams(new LinearLayout.LayoutParams((int) (display
                .getWidth() * 0.8), (int) (display.getHeight() * 0.3)));
        popupWindow.update();
//        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    public MiddleListPopupWindow builder(Type type) throws Exception {
        popupWindow = new MiddleListPopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_middlelist, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.ll_bg);
        ll_tab = (LinearLayout) popView.findViewById(R.id.lay_ll_tab);
        tv_title = (TextView) popView.findViewById(R.id.tv_title);
        tv_succ = (TextView) popView.findViewById(R.id.tv_succ);
        tv_back = (TextView) popView.findViewById(R.id.tv_back);
        iv_line = (ImageView) popView.findViewById(R.id.img_line);
        lv_content = (ListView) popView.findViewById(R.id.lay_lv_content);
        tv_title.setVisibility(View.GONE);
        tv_succ.setVisibility(View.GONE);
        tv_back.setVisibility(View.GONE);
        iv_line.setVisibility(View.GONE);
        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(true);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
        if (type == Type.Type_One){
            ratio_height = 0.6;
            ratio_width = 0.8;
        }else if (type == Type.Type_Two){
            ratio_height = 0.3;
            ratio_width = 0.8;
        }
        ll_bg.setLayoutParams(new LinearLayout.LayoutParams((int) (display
                .getWidth() * ratio_width), (int) (display.getHeight() * ratio_height)));
        popupWindow.update();
//        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    /**
     * 设置显示位置
     *
     * @param gravity
     * @return
     */
    public MiddleListPopupWindow setLocal(int gravity) throws Exception {
        popupWindow.showAtLocation(popView, gravity, 0, 0);
        return this;
    }

    /**
     * @param padding
     * @return
     */
    public MiddleListPopupWindow setPadding(int padding) throws Exception {
        lv_content.setPadding(padding, 0, padding, 0);
        return this;
    }

    public MiddleListPopupWindow setDividerHeight(int dividerHeight) throws Exception {
        lv_content.setDividerHeight(dividerHeight);
        return this;
    }

    /**
     * 设置左右边距
     *
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public MiddleListPopupWindow setMargin(View viwe, int gravity, int x, int y) throws Exception {
        ll_bg.setLayoutParams(new LinearLayout.LayoutParams((int) (display
                .getWidth()), (int) (display.getHeight() * 0.6)));
        popupWindow.showAtLocation(viwe, gravity, x, y);
        return this;
    }

    /**
     * 添加布局管理器
     *
     * @param adapter
     * @return
     */
    public MiddleListPopupWindow setAdapter(RJAdapter adapter) throws Exception {
        if (adapter != null) {
            lv_content.setAdapter(adapter);
            adapter.refresh(mList);
        }
        return this;
    }

    /**
     * 设置表投诉
     *
     * @param title
     * @return
     */
    public MiddleListPopupWindow setTitle(String title) throws Exception {
        showTitle = true;
        tv_title.setText(title);
        return this;
    }

    /**
     * 获取组件
     *
     * @return
     */
    public ListView getContent() throws Exception {
        if (lv_content != null) {
            return lv_content;
        }
        return (ListView) popView.findViewById(R.id.lay_lv_content);
    }

    /**
     * 设置数据
     *
     * @param list
     * @return
     */
    public MiddleListPopupWindow setContent(Collection<T> list) throws Exception {
        if (list != null && list.size() > 0) {
            this.mList = list;
        }
        return this;
    }

    /**
     * 设置表头
     *
     * @param view
     * @return
     */
    public MiddleListPopupWindow setHeaderView(View view) throws Exception {
        if (view != null) {
            lv_content.addHeaderView(view);
        }
        return this;
    }

    /**
     * 设置表弟
     *
     * @param view
     * @return
     */
    public MiddleListPopupWindow setFooterView(View view) throws Exception {
        if (view != null) {
            lv_content.addFooterView(view);
        }
        return this;
    }

    public MiddleListPopupWindow setClick(String neg, final View.OnClickListener listener) throws Exception {
        showSuc = true;
        showTab = true;
        if ("".equals(neg)) {
            tv_succ.setText("确定");
        } else {
            tv_succ.setText(neg);
        }
        tv_succ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                popupWindow.dismiss();
            }
        });
        return this;

    }

    public MiddleListPopupWindow setBack(String neg, final View.OnClickListener listener) throws Exception {
        showBack = true;
        if ("".equals(neg)) {
            tv_back.setText("取消");
        } else {
            tv_back.setText(neg);
            tv_back.setVisibility(View.VISIBLE);
            iv_line.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                popupWindow.dismiss();
            }
        });
        return this;

    }

    public MiddleListPopupWindow setItem(AdapterView.OnItemClickListener listener) throws Exception {
        lv_content.setOnItemClickListener(listener);
        return this;
    }

    public void show() {
        if (showTitle) tv_title.setVisibility(View.VISIBLE);
        if (showSuc) tv_succ.setVisibility(View.VISIBLE);
        if (showTab) ll_tab.setVisibility(View.VISIBLE);
    }

    public void disPop() {
        if (popupWindow != null)
            popupWindow.dismiss();
    }

    /**
     * 自定义对话框的事件接口回调
     */
    public interface PopupWindowSingleCallBack {

        /**
         * 点击确认接口回调
         */
        public void onClick();

    }

    public interface PopupWindowCallBack {
        void onNext();

        void onBack();
    }

    public interface PopupWindowCallBackThree {
        void onNext();

        void onXiaoYu();

        void onBack();
    }

    public interface ListCallback {
        public void onSelection(int position, Object mStr);
    }

    public enum Type{
        Type_One,
        Type_Two;
    }

}
