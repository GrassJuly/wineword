package com.runjing;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.runjing.base.TitleBarActivity;
import com.runjing.base.TitleBarFragment;
import com.runjing.common.AppMethod;
import com.runjing.ui.home.HomeFragment;
import com.runjing.ui.home.Subject;
import com.runjing.ui.home.SubjectImp;
import com.runjing.ui.login.WelcomeActivity;
import com.runjing.ui.mine.MineFragment;
import com.runjing.ui.ordre.OrderFragment;
import com.runjing.ui.sort.SortFragment;
import com.runjing.utils.PermissionUtils;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.tabview.TabView;
import com.runjing.widget.tabview.TabViewChild;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends TitleBarActivity implements TabView.OnTabChildClickListener {

    @BindView(id = R.id.act_tb_menu)
    private TabView tabView;
    private List<TabViewChild> tabViewChildList;
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            BACKGROUND_LOCATION_PERMISSION
    };
    private static String BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private SubjectImp subjectImp;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        subjectImp = SubjectImp.getIntence();
        if (!PermissionUtils.checkPermissions(this, needPermissions)) {
            subjectImp.notifyLocal();
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.mipmap.tab_home_main, R.mipmap.tab_home_main1,
                getString(R.string.main_home), subjectImp.registerObserver(new HomeFragment()));
        TabViewChild tabViewChild02 = new TabViewChild(R.mipmap.tab_home_menu, R.mipmap.tab_home_menu1,
                getString(R.string.main_category), new SortFragment());
        TabViewChild tabViewChild03 = new TabViewChild(R.mipmap.tab_home_order, R.mipmap.tab_home_order1,
                getString(R.string.main_order), new OrderFragment());
        TabViewChild tabViewChild04 = new TabViewChild(R.mipmap.tab_home_mine, R.mipmap.tab_home_mine1,
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
        switch (position) {
            case 0:
                StatusBarUtil.setColor(this, getResources().getColor(R.color.color_F80000));
                break;
            case 1:
                break;
            case 2:
                StatusBarUtil.setColor(this, getResources().getColor(R.color.color_ffffff));
                StatusBarUtil.setDarkMode(this);
                break;
            case 3:
                break;
        }
    }

    public void getPersion() {
        PermissionUtils.requestPermissionsResult(this, 1, needPermissions
                , new PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                    }

                    @Override
                    public void onPermissionDenied() {
                        PermissionUtils.showTipsDialog(MainActivity.this);
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppMethod.AppOver(this);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
