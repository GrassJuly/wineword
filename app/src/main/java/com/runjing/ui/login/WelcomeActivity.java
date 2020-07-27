package com.runjing.ui.login;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import com.runjing.MainActivity;
import com.runjing.MyApplication;
import com.runjing.base.TitleBarActivity;
import com.runjing.common.Appconfig;
import com.runjing.utils.MMKVUtil;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import com.runjing.utils.PermissionUtils;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

public class WelcomeActivity extends TitleBarActivity {

    private static String BACKGROUND_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    protected String[] needPermissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            BACKGROUND_LOCATION_PERMISSION
    };

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_welcome);
        getPermissions();
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setTransparentForWindow(this);
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
                Intent intent;
                if (MMKVUtil.getInstance().decodeBoolean(Appconfig.IS_GUILD)) {
//                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, GuildActivity.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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
