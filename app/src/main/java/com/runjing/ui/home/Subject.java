package com.runjing.ui.home;

import com.runjing.base.TitleBarFragment;

/**
 * @Created: qianxs  on 2020.07.30 11:47.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 11:47.
 * @Remark:
 */

public interface Subject<T extends TitleBarFragment> {
    public T registerObserver(HomeObserver o);    //数据源提供用于 （ 观察者想要获取数据源时调用此方法注册

    public void removeObserver(HomeObserver o);        //数据源提供用于  （观察者不再需要数据源数据时，退出注册）

    public void notifyLocal();

    public void notifyNet();
}
