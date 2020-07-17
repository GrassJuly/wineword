package com.runjing.http;

/**
 * okhttp，回调方法返回类型
 */
public abstract class MyRequestCallBack<T> {

    public abstract void onPostResponse(T obj);

    public abstract void onPostErrorResponse(Exception e, String msg);

    public abstract void onNoNetWork();

}
