package org.runjing.rjframe;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.runjing.rjframe.ui.AnnotateUtil;

import java.lang.ref.SoftReference;


/**
 * Fragment基类抽象类
 * @author zm
 */
public abstract  class RJFragment extends Fragment implements View.OnClickListener
{
   public static final int WHICH_MSG = 0X37211;

    protected View fragmentRootView;
    private ThreadDataCallBack callback;
    private MNFragmentHandle threadHandle = new MNFragmentHandle(this);

    private interface ThreadDataCallBack{
        void onSuccess();
    }

    private static class MNFragmentHandle extends Handler {
        private final SoftReference<RJFragment> mOuterInstance;

        MNFragmentHandle(RJFragment outer){
            mOuterInstance = new SoftReference<RJFragment>(outer);
        }

        @Override
        public void handleMessage(Message msg) {
            RJFragment mnFragment = mOuterInstance.get();
            if(msg.what == WHICH_MSG && mnFragment != null){
                mnFragment.callback.onSuccess();
            }
        }
    }

    protected abstract View inflaterView(LayoutInflater inflater,ViewGroup container,Bundle bundle);

    /**
     *
     * @param parentView 根View
     */
    protected void initWidget(View parentView){}

    protected void initData(){}

    public void onChange(){}

    protected void initDataFromThread(){
        callback = new ThreadDataCallBack() {
            @Override
            public void onSuccess() {
                threadDataInited();
            }
        };
    }

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void threadDataInited(){}
    /**
     * widget click method
     */
    protected void widgetClick(View v) {
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRootView = inflaterView(inflater, container, savedInstanceState);
        AnnotateUtil.initBindView(this, fragmentRootView);
        initData();
        initWidget(fragmentRootView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataFromThread();
                threadHandle.sendEmptyMessage(WHICH_MSG);
            }
        }).start();
        return fragmentRootView;
    }

    protected <T extends View> T bindView(int id) {
        return (T) fragmentRootView.findViewById(id);
    }
    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) fragmentRootView.findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }

}
