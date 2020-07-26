package com.runjing.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.runjing.MainActivity;
import com.runjing.common.Appconfig;

import org.runjing.rjframe.RJFragment;
import org.runjing.rjframe.utils.DensityUtils;
import org.runjing.rjframe.utils.StringUtils;

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
        public int titleBarColor;
        public CharSequence middleTitle = "";//中间标题头
        public int titleLayoutVisible = 1;//头部显示与隐藏
        public int middleTitleColor;//标题背景颜色
        public int leftImageId;
        public int rightImageId;
        public int leftVisiable = 1;
        public int rightVisiable;
        public int searchVisiable;
        public String leftVal;
        public String rightVal;
        public int middleVisiable = 1;
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
        setLeftVisiable(actionBarRes.leftVisiable);
        setLeftVal(actionBarRes.leftVal);
        setLeftImage(actionBarRes.leftImageId);
        setTitle(actionBarRes.middleTitle.toString());
        setTitleColor(actionBarRes.middleTitleColor);
        setTitleVisiable(actionBarRes.middleVisiable);
        setTitleBarVis(actionBarRes.titleLayoutVisible);
        setRightImg(actionBarRes.rightImageId);
        setRightVal(actionBarRes.rightVal);
        setRightVis(actionBarRes.rightVisiable);
        setSearchVisiable(actionBarRes.searchVisiable);
    }

    private void setLeftVisiable(int visiable) {
        if (outsideAty != null) {
            if (visiable == 0) {
                outsideAty.fm_left.setVisibility(View.INVISIBLE);
            } else {
                outsideAty.fm_left.setVisibility(View.VISIBLE);
                outsideAty.iv_left.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setLeftVal(String leftVal) {
        if (outsideAty != null) {
            if (!StringUtils.isEmpty(leftVal)) {
                outsideAty.tv_left.setText(leftVal);

            }
        }
    }

    public void setLeftImage(int image) {
        if (outsideAty != null) {
            outsideAty.iv_left.setImageResource(image);
        }
    }

    public void setTitleVisiable(int visiable) {
        if (outsideAty != null) {
            if (visiable == 0) {
                outsideAty.tv_title.setVisibility(View.GONE);
            } else {
                outsideAty.tv_title.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setTitle(String title) {
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
            } else {
                outsideAty.ll_search.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setRightVal(String val) {
        if (outsideAty != null) {
            outsideAty.tv_home_right.setText(val);
        }
    }

    public void setRightImg(int img) {
        if (outsideAty != null) {
            outsideAty.iv_right.setImageResource(img);
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

    public void setTitleBarVis(int vis) {
        if (outsideAty != null) {
            if (vis == 0) {
                outsideAty.fm_content.setVisibility(View.GONE);
            } else {
                outsideAty.fm_content.setVisibility(View.VISIBLE);
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

    public void onActionBar() {
        try {
            setActionBarRes(actionBarRes);
            styleChanged(actionBarRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
