package org.runjing.rjframe;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import org.runjing.rjframe.ui.AnnotateUtil;
import org.runjing.rjframe.ui.I_BroadcastReg;
import org.runjing.rjframe.ui.I_RJActivity;
import org.runjing.rjframe.ui.I_SkipActivity;
import org.runjing.rjframe.ui.RJActivityStack;
import org.runjing.rjframe.ui.SupportFragment;
import org.runjing.rjframe.utils.RJLoger;

import java.lang.ref.SoftReference;

import androidx.fragment.app.FragmentActivity;


/**
 * MNActivity框架抽象类
 */
public abstract class RJActivity extends FragmentActivity implements View.OnClickListener, I_BroadcastReg, I_RJActivity, I_SkipActivity {

    public static final int WHICH_MSG = 0X37210;

    public Activity aty;

    protected RJFragment currentMNFragment;

    protected SupportFragment currentSupportFragment;
    private ThreadDataCallBack callback;
    private MNActivityHandle threadHandle = new MNActivityHandle(this);

    /**
     * Activity状态
     */
    public int activityState = DESTROY;

    /**
     * 一个私有回调类，线程中初始化数据完成后的回调
     */
    private interface ThreadDataCallBack {
        void onSuccess();
    }

    private static class MNActivityHandle extends Handler {
        private final SoftReference<RJActivity> mOuterInstance;

        MNActivityHandle(RJActivity outer) {
            mOuterInstance = new SoftReference<RJActivity>(outer);
        }

        @Override
        public void handleMessage(Message msg) {
            RJActivity aty = mOuterInstance.get();
            if (msg.what == WHICH_MSG && aty != null) {
                aty.callback.onSuccess();
            }
        }
    }

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void threadDataInited() {

    }

    @Override
    public void initDataFromThread() {
        callback = new ThreadDataCallBack() {
            @Override
            public void onSuccess() {
                threadDataInited();
            }
        };
    }

    @Override
    public void initData() {

    }

    @Override
    public void initWidget() {

    }

    private void initializer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataFromThread();
                threadHandle.sendEmptyMessage(WHICH_MSG);
            }
        }).start();
        initData();
        initWidget();
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void registerBroadcast() {
    }

    @Override
    public void unRegisterBroadcast() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aty = this;
        RJActivityStack.create().addActivity(this);
        RJLoger.state(this.getClass().getName(), "---------onCreat ");
        setRootView(); // 必须放在annotate之前调用
        AnnotateUtil.initBindView(this);
        initializer();
        registerBroadcast();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RJLoger.state(this.getClass().getName(), "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = RESUME;
        RJLoger.state(this.getClass().getName(), "---------onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = PAUSE;
        RJLoger.state(this.getClass().getName(), "---------onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = STOP;
        RJLoger.state(this.getClass().getName(), "---------onStop ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        RJLoger.state(this.getClass().getName(), "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        unRegisterBroadcast();
        activityState = DESTROY;
        RJLoger.state(this.getClass().getName(), "---------onDestroy ");
        super.onDestroy();
        RJActivityStack.create().finishActivity(this);
        currentMNFragment = null;
        currentSupportFragment = null;
        callback = null;
        threadHandle = null;
        aty = null;
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, RJFragment targetFragment) {
        if (targetFragment.equals(currentMNFragment)) {
            return;
        }
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
            transaction.addToBackStack(targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentMNFragment != null && currentMNFragment.isVisible()) {
            transaction.hide(currentMNFragment);
        }
        currentMNFragment = targetFragment;
        transaction.commit();
    }

    /**
     * 用Fragment替换视图,不添加到返回栈中
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, RJFragment targetFragment, int flag) {
        if (targetFragment.equals(currentMNFragment)) {
            return;
        }
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentMNFragment != null && currentMNFragment.isVisible()) {
            transaction.hide(currentMNFragment);
        }
        currentMNFragment = targetFragment;
        transaction.commit();
    }


}
