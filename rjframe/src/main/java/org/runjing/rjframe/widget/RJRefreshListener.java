package org.runjing.rjframe.widget;

/**
 * 包含刷新和加载更多地接口方法
 */
public interface RJRefreshListener {
    /**
     * 下拉刷新回调接口
     */
    public void onRefresh();

    /**
     * 上拉刷新回调接口
     */
    public void onLoadMore();
}