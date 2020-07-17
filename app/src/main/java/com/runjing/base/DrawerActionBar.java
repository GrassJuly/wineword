package com.runjing.base;

import android.view.Gravity;

import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @Created: qianxs  on 2020.07.16 22:37.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 22:37.
 * @Remark:
 */
public class DrawerActionBar {
    private int drawerView;
    private int  scrimColor;
    private int  lockModle;
    private int  width;
    private int  height;
    private int  gravity= Gravity.LEFT;
    private DrawerLayout.DrawerListener drawerListener;

    public int getDrawerView() {
        return drawerView;
    }

    public void setDrawerView(int drawerView) {
        this.drawerView = drawerView;
    }

    public int getScrimColor() {
        return scrimColor;
    }

    public void setScrimColor(int scrimColor) {
        this.scrimColor = scrimColor;
    }

    public int getLockModle() {
        return lockModle;
    }

    public void setLockModle(int lockModle) {
        this.lockModle = lockModle;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public DrawerLayout.DrawerListener getDrawerListener() {
        return drawerListener;
    }

    public void setDrawerListener(DrawerLayout.DrawerListener drawerListener) {
        this.drawerListener = drawerListener;
    }

    @Override
    public String toString() {
        return "DrawerActionBar{" +
                "drawerView=" + drawerView +
                ", scrimColor=" + scrimColor +
                ", lockModle=" + lockModle +
                ", width=" + width +
                ", height=" + height +
                ", gravity=" + gravity +
                ", drawerListener=" + drawerListener +
                '}';
    }
}
