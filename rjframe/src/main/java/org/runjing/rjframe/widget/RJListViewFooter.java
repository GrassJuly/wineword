package org.runjing.rjframe.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 上拉加载ListView的底部<br>
 *
 */
public class RJListViewFooter extends LinearLayout {
    /** 头部刷新状态 */
    public enum LoadMoreState {
        STATE_NORMAL, // 原样
        STATE_READY, // 完成
        STATE_LOADING // 正在刷新
    }

    private String refreshing = "上拉查看更多";

    RelativeLayout contentView;
    private View progressBar;
    private TextView hintView;

    public RJListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 初始化底部组件
     */
    private void initView(Context context) {
        contentView = new RelativeLayout(context);
        contentView.setPadding(10, 10, 10, 10);
        contentView.setLayoutParams(new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(params);
        hintView = new TextView(context);
        hintView.setLayoutParams(params);
        hintView.setText(refreshing);
        hintView.setGravity(Gravity.CENTER);
        contentView.addView(progressBar);
        contentView.addView(hintView);
        addView(contentView);
    }

    /**
     * 设置底部组件的显示
     * 
     * @param state
     *            底部组件当前状态
     */
    public void setState(LoadMoreState state) {
        hintView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        if (state == LoadMoreState.STATE_READY) {
            hintView.setVisibility(View.VISIBLE);
            hintView.setText("松开载入更多");
        } else if (state == LoadMoreState.STATE_LOADING) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            hintView.setVisibility(View.VISIBLE);
            hintView.setText("上拉查看更多");
        }
    }

    /**
     * 设置底边距
     * 
     * @param height
     */
    public void setBottomMargin(int height) {
        if (height < 0) {
            return;
        }
        LayoutParams params = (LayoutParams) contentView
                .getLayoutParams();
        params.bottomMargin = height;
        contentView.setLayoutParams(params);
    }

    /**
     * 获取底边距
     */
    public int getBottomMargin() {
        LayoutParams params = (LayoutParams) contentView
                .getLayoutParams();
        return params.bottomMargin;
    }

    /**
     * 普通状态
     */
    public void normal() {
        hintView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 加载状态
     */
    public void loading() {
        hintView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 没有更多时隐藏底部
     */
    public void hide() {
        LayoutParams params = (LayoutParams) contentView
                .getLayoutParams();
        params.height = 0;
        contentView.setLayoutParams(params);
    }

    /**
     * 显示底部
     */
    public void show() {
        LayoutParams params = (LayoutParams) contentView
                .getLayoutParams();
        params.height = LayoutParams.WRAP_CONTENT;
        contentView.setLayoutParams(params);
    }

    /**
     * 设置上拉刷新时的文字
     * 
     * @param refreshing
     *            正在刷新的状态
     */
    public void setRefreshing(String refreshing) {
        this.refreshing = refreshing;
    }
}
