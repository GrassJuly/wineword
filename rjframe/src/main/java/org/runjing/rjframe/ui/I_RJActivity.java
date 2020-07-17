package org.runjing.rjframe.ui;

import android.view.View;

/**
 * 接口协议，实现此接口可使用KJActivityManager堆栈<br>
 * Created by zm on 2016/4/27.
 */
public interface I_RJActivity {
    int DESTROY = 0;
    int STOP = 2;
    int PAUSE = 1;
    int RESUME = 3;

    /**
     * 设置root界面
     */
    void setRootView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 在线程中初始化数据
     */
    void initDataFromThread();

    /**
     * 初始化控件
     */
    void initWidget();

    /**
     * 点击事件回调方法
     */
    void widgetClick(View v);
}
