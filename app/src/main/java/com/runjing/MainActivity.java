package com.runjing;

import android.app.Fragment;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.runjing.base.TitleBarActivity;
import com.runjing.common.AppMethod;
import com.runjing.ui.home.HomeFragment;
import com.runjing.ui.mine.MineFragment;
import com.runjing.ui.ordre.OrderFragment;
import com.runjing.ui.sort.SortFragment;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.tabview.TabView;
import com.runjing.widget.tabview.TabViewChild;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends TitleBarActivity implements TabView.OnTabChildClickListener {

    @BindView(id = R.id.act_tb_menu)
    private TabView tabView;
    private List<TabViewChild> tabViewChildList;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.mipmap.tab_home_main, R.mipmap.tab_home_main1,
                getString(R.string.main_home), new HomeFragment());
        TabViewChild tabViewChild02 = new TabViewChild(R.mipmap.tab_home_menu, R.mipmap.tab_home_menu1,
                getString(R.string.main_category), new SortFragment());
        TabViewChild tabViewChild03 = new TabViewChild(R.mipmap.tab_home_persional, R.mipmap.tab_home_persional1,
                getString(R.string.main_order), new OrderFragment());
        TabViewChild tabViewChild04 = new TabViewChild(R.mipmap.tab_home_persional, R.mipmap.tab_home_persional1,
                getString(R.string.main_mine), new MineFragment());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList, getFragmentManager());
        tabView.setOnTabChildClickListener(this);
    }

    @Override
    public void onTabChildClick(Fragment fragment, int position) {

    }

    @Override
    public void onTabChildClick(Fragment fragment, int position, ImageView imageView, TextView textView) {

    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_F80000));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppMethod.AppOver(this);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
