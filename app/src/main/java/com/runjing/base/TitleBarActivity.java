package com.runjing.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.RJActivity;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：应用Activity基类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public abstract class TitleBarActivity extends RJActivity implements TextWatcher {
    public FrameLayout fm_content;
    public LinearLayout fm_left;
    public TextView tv_left;
    public ImageView iv_left;
    public TextView tv_title;
    public LinearLayout ll_search;
    public EditText et_search;
    public LinearLayout fm_right;
    public TextView tv_home_right;
    public ImageView iv_right;
    private ViewGroup viewGroup;

    public  void OnActionBar(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //TODO 后期记得记载进来
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
    public void initWidget() {
        super.initWidget();
    }

    @Override
    protected void onStart() {
        try {
            fm_content = findViewById(R.id.base_title);
            fm_left = fm_content.findViewById(R.id.home_fl_left);
            tv_left = fm_content.findViewById(R.id.home_bar_tv_left);
            iv_left = fm_content.findViewById(R.id.home_bar_iv_left);
            ll_search = fm_content.findViewById(R.id.title_ll_search);
            et_search = fm_content.findViewById(R.id.title_et_search);
            tv_title = fm_content.findViewById(R.id.home_bar_tv_title);
            fm_right = fm_content.findViewById(R.id.fl_right);
            tv_home_right = fm_content.findViewById(R.id.home_bar_tv_right);
            iv_right = fm_content.findViewById(R.id.home_bar_iv_right);
            fm_left.setOnClickListener(this);
            iv_left.setOnClickListener(this);
            tv_left.setOnClickListener(this);
            fm_right.setOnClickListener(this);
            tv_home_right.setOnClickListener(this);
            iv_right.setOnClickListener(this);
            et_search.addTextChangedListener(this);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        OnActionBar();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.home_fl_left: // 左侧返回
            case R.id.home_bar_tv_left:
            case R.id.home_bar_iv_left:
                onBackClick();
                break;
            case R.id.fl_right: // 右侧菜单
            case R.id.home_bar_tv_right:
            case R.id.home_bar_iv_right:
                onMenuClick();
                break;
        }
    }

    public void onBackClick() {
    }

    public void onMenuClick() {
    }

    public void onSearchTextChanged(String search) {
        System.out.println("走吗？   11 ---  " +search);
    }

    public void onSearch() {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onSearchTextChanged(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

