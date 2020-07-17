package org.runjing.rjframe.ui;

/**
 * 规范Activity中广播接受者注册的接口协议<br>
 * Created by zm on 2016/4/27.
 */
public interface I_BroadcastReg {
    /**
     * 注册广播
     */
    void registerBroadcast();

    /**
     * 解除注册广播
     */
    void unRegisterBroadcast();
}
