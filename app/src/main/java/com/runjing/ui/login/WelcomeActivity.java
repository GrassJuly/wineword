package com.runjing.ui.login;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.MainActivity;
import com.runjing.MyApplication;
import com.runjing.base.TitleBarActivity;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import com.runjing.utils.PermissionUtils;

import org.runjing.rjframe.ui.BindView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

public class WelcomeActivity extends TitleBarActivity {

    @BindView(id = R.id.act_ll_agree)
    private LinearLayout ll_agree;
    @BindView(id = R.id.act_tv_login, click = true)
    private TextView tv_login;
    @BindView(id = R.id.act_tv_refause, click = true)
    private TextView tv_refause;

    private static String BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    protected String[] needPermissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            BACKGROUND_LOCATION_PERMISSION
    };
    private boolean is_Guild;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        is_Guild = MMKVUtil.getInstance().decodeBoolean(Appconfig.IS_GUILD);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        String is_agree = MMKVUtil.getInstance().decodeString(Appconfig.IS_AGREE);
        if (TextUtils.isEmpty(is_agree) || !Appconfig.IS_AGREE.equals(is_agree)) {
            ll_agree.setVisibility(View.VISIBLE);
        } else {
            ll_agree.setVisibility(View.GONE);
            getPermissions();
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.act_tv_login:
                    MMKVUtil.getInstance().encode(Appconfig.IS_AGREE, Appconfig.IS_AGREE);
                    ll_agree.setVisibility(View.GONE);
                    getPermissions();
                    break;
                case R.id.act_tv_refause:
                    finish();
                    break;
            }
        }
    }

    private void getPermissions() {
        PermissionUtils.requestPermissionsResult(this, 1, needPermissions
                , new PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        initView();
                    }

                    @Override
                    public void onPermissionDenied() {
                        PermissionUtils.showTipsDialog(WelcomeActivity.this);
                    }
                });
    }

    private void initView() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (is_Guild) {
                    AppMethod.postActivity(WelcomeActivity.this, MainActivity.class);
                } else {
                    AppMethod.postActivity(WelcomeActivity.this, GuildActivity.class);
                }
                finish();
            }
        };
        timer.schedule(timerTask, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
