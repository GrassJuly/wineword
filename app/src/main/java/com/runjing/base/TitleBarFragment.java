package com.runjing.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.runjing.MainActivity;
import com.runjing.common.Appconfig;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.RJFragment;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.StringUtils;

import androidx.annotation.IntRange;
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

    //时间有限先兼容现阶段任务， 后期完善
    public class ActionBarRes {
        /*标题是否显示*/
        public int middleVisiable = 1;
        /*标题背景颜色*/
        public int titleBarColor = R.color.color_ffffff;
        /*标题文字*/
        public CharSequence middleTitle;//中间标题头
        /*标题颜色*/
        public int middleTitleColor = R.color.color_ffffff;//标题背景颜色
        /*标题是否显示*/
        public int titleLayoutVisible = 0;//头部显示与隐藏
        /*标题左面图片*/
        public int leftImageId = Appconfig.DEFAULT_VALUE_LONG;
        /*标题有面图片*/
        public int rightImageId = R.mipmap.icon_title_back;
        /*标题左侧是否显示*/
        public int leftVisiable = 1;
        /*标题右侧是否显示*/
        public int rightVisiable;
        /*中间搜索是否显示*/
        public int searchVisiable;
        /*左侧文字*/
        public String leftVal;
        /*右侧文字*/
        public String rightVal;
    }

    private final ActionBarRes actionBarRes = new ActionBarRes();
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
    private void styleChanged(ActionBarRes actionBarRes) {
        setTitleBarVis(actionBarRes.titleLayoutVisible);
        setTitle(actionBarRes.middleTitle);
        setTitleColor(actionBarRes.middleTitleColor);
        setLeftVisiable(actionBarRes.leftVisiable);
        setLeftImage(actionBarRes.leftImageId);
        setTitleColor(actionBarRes.middleTitleColor);
        setTitleVisiable(actionBarRes.middleVisiable);
        setRightImg(actionBarRes.rightImageId);
        setRightVal(actionBarRes.rightVal);
        setRightVis(actionBarRes.rightVisiable);
        setSearchVisiable(actionBarRes.searchVisiable);
    }

    public void setTitleBarVis(int vis) {
        if (outsideAty != null) {
            if (vis == 0) {
                outsideAty.fm_content.setVisibility(View.GONE);
            } else {
                outsideAty.fm_content.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setTitleBarCol(int color) {
        if (outsideAty != null) {
            outsideAty.fm_content.setBackgroundColor(color);
        }
    }

    private void setLeftVisiable(int vis) {
        if (outsideAty != null) {
            if (vis == 0) {
                outsideAty.fm_left.setVisibility(View.INVISIBLE);
            } else {
                outsideAty.fm_left.setVisibility(View.VISIBLE);
            }
        }
    }


    public void setLeftImage(int image) {
        if (outsideAty != null) {
            if (image != Appconfig.DEFAULT_VALUE_LONG) {
                outsideAty.iv_left.setImageResource(image);
            }
        }
    }

    public void setTitleVisiable(int visiable) {
        if (outsideAty != null) {
            if (visiable == 0) {
                outsideAty.tv_title.setVisibility(View.GONE);
            } else {
                outsideAty.fm_right.setVisibility(View.GONE);
            }
        }
    }

    public void setTitle(CharSequence title) {
        if (outsideAty != null) {
            outsideAty.tv_title.setText(title);
        }
    }

    public void setTitleColor(int color) {
        if (outsideAty != null) {
            outsideAty.tv_title.setTextColor(color);
        }
    }

    public void setSearchVisiable(int visiable) {
        if (outsideAty != null) {
            if (visiable == 0) {
                outsideAty.ll_search.setVisibility(View.GONE);
                outsideAty.tv_title.setVisibility(View.VISIBLE);
                outsideAty.fm_right.setVisibility(View.INVISIBLE);
            } else {
                outsideAty.ll_search.setVisibility(View.VISIBLE);
                outsideAty.tv_title.setVisibility(View.GONE);
                outsideAty.fm_right.setVisibility(View.GONE);
            }
        }
    }

    public void setRightVal(String val) {
        if (outsideAty != null) {
            outsideAty.tv_home_right.setText(val);
        }
    }

    public void setRightImg(int img) {
        if (outsideAty != null && img != 0) {
            outsideAty.iv_right.setImageResource(img);
            outsideAty.fm_right.setVisibility(View.VISIBLE);
        }
    }

    public void setRightVis(int vis) {
        if (outsideAty != null) {
            if (vis == 0) {
                outsideAty.fm_right.setVisibility(View.INVISIBLE);
            } else {
                outsideAty.fm_right.setVisibility(View.VISIBLE);
            }
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

    public void initToolBar() {
    }

    public void finish() {
        getActivity().finish();
    }

    /**
     * @param bundle
     */
    public void onParames(Bundle bundle) {
    }


    public void getDrawer(DrawerActionBar drawerActionBar) {
    }

    public void setDrawer(View view) {

    }

    public void putDrawerView(View view) {
        this.view = view;
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

    @Override
    public void onResume() {
        super.onResume();
        onActionBar();
        initToolBar();
    }

    public void onActionBar() {
        try {
            setActionBarRes(actionBarRes);
            styleChanged(actionBarRes);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchTextChanged(String search) {

    }

}
