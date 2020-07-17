package org.runjing.rjframe.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 下拉刷新控件的头部（可供KJListView、KJScrollView使用）
 */
public class RJListViewHeader extends LinearLayout {
    /** 头部刷新状态 */
    public enum RefreshState {
        STATE_NORMAL, // 原样
        STATE_READY, // 完成
        STATE_REFRESHING // 正在刷新
    }

    // flag
    private RefreshState mState = RefreshState.STATE_NORMAL;

    private String normal = "有一种下拉可以刷新";
    private String ready = "有一种刷新叫做放手";
    private String refreshing = "正在刷新…";

    // widget
    private TextView hintTextView; // 刷新提示文字（上拉刷新、下拉刷新、正在刷新）
   public RelativeLayout layout; // 头部layout
   public TextView timeTextView; // 刷新时间

    public RJListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 初始化组件
     */
    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度为0
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 0);
        layout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        LinearLayout l = new LinearLayout(context);
        l.setGravity(Gravity.CENTER);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setLayoutParams(params);
        hintTextView = new TextView(context);
        hintTextView.setGravity(Gravity.CENTER);
        hintTextView.setText(normal);
        timeTextView = new TextView(context);
        timeTextView.setGravity(Gravity.CENTER);
        l.addView(hintTextView);
        l.addView(timeTextView);
        layout.addView(l);
        addView(layout, lp);
        setGravity(Gravity.BOTTOM);
    }

    /**
     * 设置顶部组件的显示
     * 
     * @param state
     *            顶部组件当前状态
     */
    public void setState(RefreshState state) {
        if (state == mState)
            return;
        switch (state) {
        case STATE_NORMAL:
            hintTextView.setText(normal);
            break;
        case STATE_READY:
            if (mState != RefreshState.STATE_READY) {
                hintTextView.setText(ready);
            }
            break;
        case STATE_REFRESHING:
            hintTextView.setText(refreshing);
            break;
        default:
        }
        mState = state;
    }

    /**
     * 设置显示高度
     */
    public void setVisibleHeight(int height) {
        if (height < 0) {
            height = 0;
        }
        LayoutParams params = (LayoutParams) layout
                .getLayoutParams();
        params.height = height;
        layout.setLayoutParams(params);
    }

    /**
     * 获取高度
     */
    public int getVisibleHeight() {
        return layout.getHeight();
    }

    /**
     * 设置下拉时的显示文字
     * 
     * @param normal
     *            刚开始下拉，还没有到放手的状态
     */
    public void setNormal(String normal) {
        this.normal = normal;
    }

    /**
     * 设置下拉回放时的显示文字
     * 
     * @param ready
     *            下拉完成后向上收缩，准备刷新时的状态
     */
    public void setReady(String ready) {
        this.ready = ready;
    }

    /**
     * 设置刷新时的文字
     * 
     * @param refreshing
     *            正在刷新的状态
     */
    public void setRefreshing(String refreshing) {
        this.refreshing = refreshing;
    }
}
