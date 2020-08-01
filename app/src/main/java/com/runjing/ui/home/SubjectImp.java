package com.runjing.ui.home;

import com.runjing.base.TitleBarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created: qianxs  on 2020.07.30 12:05.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.30 12:05.
 * @Remark:
 */
public class SubjectImp implements Subject {

    private List<HomeObserver> observer;

    private static class SubHelper {
        private static SubjectImp INSTANCE = new SubjectImp();

    }

    public static SubjectImp getIntence() {
        return SubHelper.INSTANCE;
    }

    private SubjectImp() {
        this.observer = new ArrayList<>(4);
    }

    @Override
    public TitleBarFragment registerObserver(HomeObserver o) {
        if (observer != null) {
            observer.add(o);
        }
        return (TitleBarFragment) o;
    }

    @Override
    public void removeObserver(HomeObserver o) {
        if (observer != null) {
            observer.remove(o);
        }
    }

    @Override
    public void notifyLocal() {
        if (observer != null) {
            for (HomeObserver o : observer) {
                o.onLocalPresion();
            }
        }

    }

    @Override
    public void notifyNet() {
        if (observer != null) {
            for (HomeObserver o : observer) {
                o.onNetAble();
            }
        }
    }
}
