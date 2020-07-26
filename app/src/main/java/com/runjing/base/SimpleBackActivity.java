package com.runjing.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.runjing.MyApplication;
import com.runjing.common.Appconfig;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.widget.LoadingDialog;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

import androidx.drawerlayout.widget.DrawerLayout;

import static org.runjing.rjframe.utils.DensityUtils.dip2dp;


/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：二级菜单
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class SimpleBackActivity extends TitleBarActivity {

    @BindView(id = R.id.second_ll_frame)
    private FrameLayout ly_frame;
    @BindView(id = R.id.img_base_line)
    public ImageView img_base_line;
    TitleBarFragment currentFragment;
    public LoadingDialog dialog;
    @BindView(id = R.id.act_dl_content)
    private DrawerLayout dl_content;
    private DrawerActionBar drawerActionBar;


    @Override
    public void setRootView() {
        dialog = new LoadingDialog(this);
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.aty_simple_back);
        int value = getIntent().getIntExtra(Appconfig.CONTENT_KEY, -1);
        if (value != -1) {
            try {

                if (value != 330) {

                    currentFragment = (TitleBarFragment) SimpleBackPage
                            .getPageByValue(value).newInstance();
                    changeFragment(currentFragment);


                } else {

                    drawerActionBar = new DrawerActionBar();
                    currentFragment = (TitleBarFragment) SimpleBackPage.getPageByValue(value).newInstance();
                    currentFragment.getDrawer(drawerActionBar);
                    changeFragment(currentFragment);

                    View view = LayoutInflater.from(this).inflate(drawerActionBar.getDrawerView(), null);
                    currentFragment.putDrawerView(view);

                    if (dl_content == null)
                        dl_content = (DrawerLayout) findViewById(R.id.act_dl_content);
                    if (view != null) {
                        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                                drawerActionBar.getHeight() == -1 ? DrawerLayout.LayoutParams.MATCH_PARENT :
                                        dip2dp(aty, drawerActionBar.getWidth()),
                                drawerActionBar.getHeight() == -1 ? DrawerLayout.LayoutParams.MATCH_PARENT :
                                        dip2dp(aty, drawerActionBar.getHeight()));
                        params.gravity = drawerActionBar.getGravity();
                        dl_content.setDrawerListener(drawerActionBar.getDrawerListener());
                        dl_content.setScrimColor(getResources().getColor(drawerActionBar.getScrimColor()));
                        dl_content.setDrawerLockMode(drawerActionBar.getLockModle());
                        dl_content.addView(view, params);
                    }
                }


            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {

            }
        }
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        if (currentFragment != null) {
            currentFragment.initToolBar();
        }
    }

    /**
     * 跳转fragment
     */
    public void changeFragment(TitleBarFragment targetFragment) {
        currentFragment = targetFragment;
        super.changeFragment(R.id.second_ll_frame, targetFragment);
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        if (currentFragment != null) {
            currentFragment.onBackClick();
        }
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        if (currentFragment != null) {
            currentFragment.onMenuClick();
        }
    }

    @Override
    public void onSearch() {
        super.onSearch();
        if (currentFragment != null) {
            currentFragment.onSearch();
        }
    }

    @Override
    public void onSearchTextChanged(String search) {
        super.onSearchTextChanged(search);
        if (currentFragment != null) {
            currentFragment.onSearchTextChanged(search);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            currentFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public DrawerLayout getDl_content() {
        return dl_content;
    }

    @Override
    public void OnActionBar() {
        currentFragment.onActionBar();
    }
}
