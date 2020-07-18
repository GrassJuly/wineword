package com.runjing.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.runjing.common.Appconfig;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.RJActivity;

import de.greenrobot.event.EventBus;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：应用Activity基类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public abstract class TitleBarActivity extends RJActivity  {
    public FrameLayout frame_titleBar;
    public RelativeLayout ll_base_title; //基础的标题头
    public Button btn_home_left;//标题左上角按钮
    public TextView tv_home_right;//标题右上角按钮
    public ImageView iv_home_right;//标题右侧图片
    public TextView tv_home_middle_title;//标题
    public LinearLayout ll_search;
    public ImageView iv_search;
    private int[] searchId;
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Appconfig.DEFAULT_VALUES!= initTitleColor()){
            StatusBarUtil.setColor(this, initTitleColor(), 0);
        }
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //TODO后期记得记载进来
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        viewGroup = contentFrameLayout;
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        try {
            ll_base_title = (RelativeLayout) findViewById(R.id.mn_home_bar_ll_top);
            ll_search = (LinearLayout) findViewById(R.id.mn_homebar_ll_search);
            iv_search = (ImageView) findViewById(R.id.mn_homebar_iv_search);
            btn_home_left = (Button) findViewById(R.id.mn_home_bar_btn_left);
            tv_home_right = (TextView) findViewById(R.id.mn_home_bar_tv_right);
            iv_home_right = (ImageView) findViewById(R.id.mn_home_bar_iv_right);
            tv_home_middle_title = (TextView) findViewById(R.id.mn_home_bar_tv_title);
            frame_titleBar = (FrameLayout) findViewById(R.id.frame_titleBar);
            btn_home_left.setOnClickListener(this);
            tv_home_right.setOnClickListener(this);
            iv_home_right.setOnClickListener(this);
            ll_search.setOnClickListener(this);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.mn_home_bar_btn_left: // 左侧返回
                onBackClick();
                break;
            case R.id.mn_home_bar_tv_right: // 右侧菜单
                addClick();
            case R.id.mn_home_bar_iv_right:
                onMenuClick();
                break;
            case R.id.mn_homebar_ll_search:
                setSearch(searchId);
                onSearch();
                break;
        }
    }

    private void addClick() {
    }

    public void onBackClick() {
    }

    public void onMenuClick() {
    }

    public void onSearch() {
    }

    public int initTitleColor() {
        return Appconfig.DEFAULT_VALUES;
    }

    /**
     * @param searchId
     */
    public void setSearch(int[] searchId) {
        try {
            if (searchId != null) {
                if (searchId.length > 1) {
                    if (this.searchId == null) this.searchId = searchId;
                    if (iv_search != null) {
                        if (iv_search.getTag() == null) {
                            iv_search.setImageResource(searchId[0]);
                            iv_search.setTag(searchId[0]);
                        } else if (((int) iv_search.getTag()) == searchId[0]) {
                            iv_search.setImageResource(searchId[1]);
                            iv_search.setTag(searchId[1]);
                        } else if (((int) iv_search.getTag()) == searchId[1]) {
                            iv_search.setImageResource(searchId[0]);
                            iv_search.setTag(searchId[0]);
                        }
                    }
                } else if (searchId.length == 1) {
                    iv_search.setImageResource(searchId[0]);
                    iv_search.setTag(searchId[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            try {
//                if (KeyBoardUtil.isShouldHideInput(viewGroup, v, ev)) {
//                    if (KeyBoardUtil.viewIsEditText(viewGroup, ev)) {
//                        KeyBoardUtil.hideSoftInput(this, v.getWindowToken());
//                    }
//                }
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        //必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
}

