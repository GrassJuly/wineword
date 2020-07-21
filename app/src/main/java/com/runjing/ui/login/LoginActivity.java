package com.runjing.ui.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.runjing.MyApplication;
import com.runjing.base.TitleBarActivity;
import com.runjing.base.TitleBarFragment;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * login 登录页面
 *
 * @author
 */

public class LoginActivity extends TitleBarActivity {

    @BindView(id = R.id.act_iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.act_ll_quick, click = true)
    private LinearLayout ll_quick;
    @BindView(id = R.id.act_v_quick)
    private View v_quick;
    @BindView(id = R.id.act_ll_ap, click = true)
    private LinearLayout ll_ap;
    @BindView(id = R.id.act_v_ap)
    private View v_ap;
    @BindView(id = R.id.act_fl_content)
    private FrameLayout fl_content;
    private TitleBarFragment fragment;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<TitleBarFragment> lists;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        KeyBoardUtil.init(this);
        lists = new ArrayList<>();
        lists.add(new QuickLoginFragment());
        lists.add(new APLoginFragment());
        fm = getFragmentManager();
        ft =fm.beginTransaction();
        fragment = lists.get(0);
        ft.add(R.id.act_fl_content, fragment).commit();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.act_iv_back:
                finish();
                break;
            case R.id.act_ll_quick:
                v_quick.setVisibility(View.VISIBLE);
                v_ap.setVisibility(View.INVISIBLE);
                changePage(fragment, lists.get(0));
                break;
            case R.id.act_ll_ap:
                v_quick.setVisibility(View.INVISIBLE);
                v_ap.setVisibility(View.VISIBLE);
                changePage(fragment, lists.get(1));
                break;
        }
    }

    /**
     * 页面切换
     * @param from
     * @param to
     */
    public void changePage(TitleBarFragment from, TitleBarFragment to) {
        if (fragment != to) {
            fragment = to;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.act_fl_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

}
