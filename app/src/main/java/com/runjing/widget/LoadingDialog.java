package com.runjing.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.runjing.wineworld.R;

/**
 * @Created by xiaoyu on 2017/1/9
 * @Describe：loadingdialog
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/9
 * @Remark:
 */
public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        this(context, R.style.loadingDialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_loading_dialog);

        setCanceledOnTouchOutside(false);
    }
}
