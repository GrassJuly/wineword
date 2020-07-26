package com.runjing.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
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
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.runjing.rjframe.utils.DensityUtils;
import org.runjing.rjframe.widget.RJAdapter;

import java.util.Collection;

/**
 * Created by xiaoyu on 2016/5/11.
 * Describe：
 */
public class ShoptPopupWindow<T extends BasePop> extends PopupWindow {

    private View popView;
    private LinearLayout ll_bg;
    private ImageView iv_choose;
    private TextView tv_total;
    private LinearLayout ll_del;
    private SwipeRecyclerView sr_content;

    private Context mContext;
    private LayoutInflater mInflater;
    private static ShoptPopupWindow popupWindow;
    private Display display;
    private Collection<T> mList;
    private PopupWindowCallBack listener;


    private ShoptPopupWindow(Context context) throws Exception {
        super(context);
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static ShoptPopupWindow getInstance(Context context) throws Exception {
        if (popupWindow == null) {
            popupWindow = new ShoptPopupWindow(context);
        }
        return popupWindow;
    }

    public ShoptPopupWindow builder() throws Exception {
        popupWindow = new ShoptPopupWindow(mContext);
        popView = mInflater.inflate(R.layout.layout_pop_shop, null);
        ll_bg = (LinearLayout) popView.findViewById(R.id.ll_bg);
        iv_choose = (ImageView) popView.findViewById(R.id.lay_pop_iv_choose);
        tv_total = (TextView) popView.findViewById(R.id.lay_pop_tv_total);
        ll_del = (LinearLayout) popView.findViewById(R.id.lay_pop_ll_del);
//        sr_content = (SwipeRecyclerView) popView.findViewById(R.id.lay_pop_sr_content);

        // 设置MiddlePopupWindow的View
        popupWindow.setContentView(popView);
        // 设置MiddlePopupWindow弹出窗体的宽
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置MiddlePopupWindow弹出窗体的高
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置MiddlePopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置
        popupWindow.setOutsideTouchable(true);
        // 设置MiddlePopupWindow弹出窗体的背景
        ColorDrawable colorDrawable = new ColorDrawable(0x7f000000);
        popupWindow.setBackgroundDrawable(colorDrawable);
//        ll_bg.setLayoutParams(new LinearLayout.LayoutParams((int) (display
//                .getWidth() * 0.8), (int) (display.getHeight() * 0.3)));
        popupWindow.update();
//        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        return this;
    }

    /**
     * 设置显示位置
     *
     * @param view
     * @return
     */
    public ShoptPopupWindow showUp(View view) throws Exception {

//在控件上方显示    向上移动y轴是负数
        //获取需要在其上方显示的控件的位置信息
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        //在控件上方显示
////        showAtLocation(view, Gravity.NO_GRAVITY, location[0], (location[1] - view.getMeasuredHeight()));
//        showAtLocation(view, Gravity.BOTTOM, 0, (location[1] - DensityUtils.dip2dp(mContext, 50)));


        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(view);
        } else {
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
        return this;
    }

    /**
     * 添加布局管理器
     *
     * @param adapter
     * @return
     */
    public ShoptPopupWindow setAdapter(RJAdapter adapter) throws Exception {
        if (adapter != null) {

        }
        return this;
    }

    /**
     * 设置数据
     *
     * @param list
     * @return
     */
    public ShoptPopupWindow setContent(Collection<T> list) throws Exception {
        if (list != null && list.size() > 0) {
            this.mList = list;
        }
        return this;
    }

    public ShoptPopupWindow setShopCallBackLister(PopupWindowCallBack lister) {
        this.listener = lister;
        return this;
    }

    public void disPop() {
        if (popupWindow != null)
            popupWindow.dismiss();
    }


    public interface PopupWindowCallBack<T extends BasePop> {
        void onSelect(T item);

        void onSelectAll();

        void onClearAll();
    }

}










