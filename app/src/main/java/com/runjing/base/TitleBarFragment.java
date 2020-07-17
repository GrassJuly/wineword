package com.runjing.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.runjing.MainActivity;
import com.runjing.common.Appconfig;

import org.runjing.rjframe.RJFragment;
import org.runjing.rjframe.utils.DensityUtils;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：应用Fragment抽象基础类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public abstract class TitleBarFragment extends RJFragment {

    public class ActionBarRes {
        public int titleBarColor = Appconfig.DEFAULT_VALUES;
        public CharSequence middleTitle;//中间标题头
        public int titleLayoutVisible;//头部显示与隐藏
        public int leftButtonId;   //左侧按钮设置图片
        public int middleLayoutColor;//标题背景颜色
        public int rightButtonId; //右侧按钮设置图片
        public int leftImageVisible; //左侧图片显示与隐藏 1代表显示 否则隐藏
        public int rightImageVisible;//右侧图片显示与隐藏 1代表显示 否则隐藏
        public int rightButtonSizeTag;//搜索标题栏显示与隐藏 1代表显示 否则隐藏
        public CharSequence rightMsg;//右面标题内容
        public int serchVisible;
        public int[] serchId;

    }

    private final ActionBarRes actionBarRes = new ActionBarRes();
    //    private  DrawerActionBar drawerActionBar = new DrawerActionBar();
    private View view;
    protected TitleBarActivity outsideAty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getActivity() instanceof TitleBarActivity) {
            outsideAty = (TitleBarActivity) getActivity();
        } else if (getActivity() instanceof MainActivity) {
            outsideAty = (MainActivity) getActivity();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (view != null) setDrawer(view);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        onParames(outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY));
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            setActionBarRes(actionBarRes);
            styleChanged(actionBarRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        finish();
    }

    /**
     * @param actionBarRes
     */
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        try {
            styleChanged(actionBarRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param actionBarRes
     * @throws Exception
     */
    private void styleChanged(ActionBarRes actionBarRes) throws Exception {
        setTitleBarColor(actionBarRes.titleBarColor);
        setTitleLayoutVisible(actionBarRes.titleLayoutVisible);
        setMiddleTitle(actionBarRes.middleTitle);
        setLeftBtnVisible(actionBarRes.leftImageVisible);
        setRightBtnVisible(actionBarRes);
        setRightButtonSize(actionBarRes.rightButtonSizeTag);
        setRightMsg(actionBarRes.rightMsg);
//        setSearchId(actionBarRes.serchId);
        outsideAty.setSearch(actionBarRes.serchId);
        setSearchVisible(actionBarRes.serchVisible);
        if (actionBarRes.leftButtonId != 0) {
            setLeftBtnImage(actionBarRes.leftButtonId);
        }
        if (actionBarRes.rightButtonId != 0) {
            setRightbtnImage(actionBarRes.rightButtonId);
        }
        if (actionBarRes.middleLayoutColor != 0) {
            setMiddleTitleColor(actionBarRes.middleLayoutColor);
        }
    }

    /**
     * @param searchId
     * @throws Exception
     */
    public void setSearchId(int[] searchId) throws Exception {
        if (outsideAty != null) {
            if (searchId != null && searchId.length > 0) {
                outsideAty.iv_search.setImageResource(searchId[0]);
            }
        }
    }

    public void setTitleBarColor(int titleBarColor) {
        if (outsideAty != null) {
            if (titleBarColor != Appconfig.DEFAULT_VALUES) {
                StatusBarUtil.setColor(outsideAty, titleBarColor, 0);
                StatusBarUtil.setLightMode(outsideAty);
            }
        }
    }

    /**
     * @param visible
     * @throws Exception
     */
    public void setSearchVisible(int visible) throws Exception {
        if (outsideAty != null) {
            if (visible == 0) {
                outsideAty.iv_search.setVisibility(View.GONE);
            } else if (visible == 1) {
                outsideAty.iv_search.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * @param msg
     * @throws Exception
     */
    public void setRightMsg(CharSequence msg) throws Exception {
        if (outsideAty != null) {
            if (!TextUtils.isEmpty(msg)) {
                outsideAty.tv_home_right.setText(msg);
                outsideAty.tv_home_right.setHeight(DensityUtils.dip2dp(getActivity(), 48));
                outsideAty.tv_home_right.setWidth(DensityUtils.dip2dp(getActivity(), 96));
            }
        }
    }

    /**
     * 设置标题栏显示与隐藏
     *
     * @param visible
     * @throws Exception
     */
    public void setTitleLayoutVisible(int visible) throws Exception {
        if (outsideAty != null && outsideAty.ll_base_title != null) {
            if (visible == 1) {
                outsideAty.ll_base_title.setVisibility(View.GONE);
            } else if (visible == 2) {
                outsideAty.ll_base_title.setVisibility(View.VISIBLE);
            } else {
                outsideAty.ll_base_title.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 设置中间的标题
     *
     * @param txt
     * @throws Exception
     */
    protected void setMiddleTitle(CharSequence txt) throws Exception {
        if (outsideAty != null && outsideAty.tv_home_middle_title != null) {
            outsideAty.tv_home_middle_title.setText(txt);
        }
    }

    /**
     * 设置右边按钮的大小
     *
     * @param tag
     * @throws Exception
     */
    protected void setRightButtonSize(int tag) throws Exception {
        if (outsideAty != null && outsideAty.tv_home_right != null) {
            if (tag == 0) {
                outsideAty.tv_home_right.setWidth(48);
            } else if (tag == 1) {
                outsideAty.tv_home_right.setWidth(48);
            }
        }

    }

    /**
     * 设置左上角按钮显示
     *
     * @param visible
     * @throws Exception
     */
    protected void setLeftBtnVisible(int visible) throws Exception {
        if (outsideAty != null) {
            if (visible == 1) {
                outsideAty.btn_home_left.setVisibility(View.VISIBLE);
            } else {
                outsideAty.btn_home_left.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 设置右上角按钮显示
     *
     * @param actionBarRes
     * @throws Exception
     */
    protected void setRightBtnVisible(ActionBarRes actionBarRes) throws Exception {
        if (outsideAty != null) {
            if (actionBarRes.rightImageVisible == 1) {
                if (TextUtils.isEmpty(actionBarRes.rightMsg)) {
                    if (actionBarRes.rightButtonId != 0) {
                        outsideAty.iv_home_right.setVisibility(View.VISIBLE);
                        outsideAty.tv_home_right.setVisibility(View.GONE);
                    } else {
                        outsideAty.iv_home_right.setVisibility(View.GONE);
                        outsideAty.tv_home_right.setVisibility(View.GONE);
                    }

                } else {
                    outsideAty.tv_home_right.setVisibility(View.VISIBLE);
                    outsideAty.iv_home_right.setVisibility(View.GONE);
                }
            } else {
                outsideAty.tv_home_right.setVisibility(View.GONE);
                outsideAty.iv_home_right.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置左上角按钮图片样式
     *
     * @param resId
     * @throws Exception
     */
    protected void setLeftBtnImage(int resId) throws Exception {
        if (outsideAty != null) {
            outsideAty.btn_home_left.setBackgroundResource(resId);
        }
    }

    /**
     * 设置右上角按钮图片样式
     *
     * @param resId
     * @throws Exception
     */
    protected void setRightbtnImage(int resId) throws Exception {
        if (outsideAty != null) {
            outsideAty.iv_home_right.setImageResource(resId);
        }
    }

    /**
     * 设置标题背景颜色
     *
     * @param resId
     * @throws Exception
     */
    protected void setMiddleTitleColor(int resId) throws Exception {
        if (outsideAty != null) {
            outsideAty.ll_base_title.setBackgroundResource(resId);
        }
    }

    /* 当ActionBar上的返回键被按下时*/
    public void onBackClick() {
    }

    /* 当ActionBar上的菜单键被按下时*/
    public void onMenuClick() {
    }

    public void onSearch() {

    }

    public void finish() {
        getActivity().finish();
    }

    /**
     * @param bundle
     */
    public void onParames(Bundle bundle) {
    }

    /**
     * @return
     */
    public int[] getActionBarRes() {
        if (actionBarRes != null) return actionBarRes.serchId;
        return null;
    }

    public void getDrawer(DrawerActionBar drawerActionBar) {
    }

    public void setDrawer(View view) {

    }

    public void putDrawerView(View view) {
        this.view = view;
    }

    /**
     * 设置右侧图片资源
     *
     * @param resId
     */
    public void setRightRes(int resId) {
        if (outsideAty != null) {
            try {
                setRightbtnImage(resId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取抽屉布局
     *
     * @return
     */
    public DrawerLayout getDraweLayout() {
        if (outsideAty != null && outsideAty instanceof SimpleBackActivity) {
            return ((SimpleBackActivity) outsideAty).getDl_content();
        }
        return null;
    }

}
