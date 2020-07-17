package com.runjing;

import android.Manifest;
import android.content.Intent;

import com.runjing.base.TitleBarActivity;
import com.runjing.wineworld.R;

import com.runjing.utils.PermissionUtils;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

public class WelcomeActivity extends TitleBarActivity {

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_welcome);
        getPermissions();
    }

    private void getPermissions() {
        PermissionUtils.requestPermissionsResult(this, 1, new String[]{Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION}
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
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
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
