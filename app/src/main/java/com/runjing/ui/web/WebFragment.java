package com.runjing.ui.web;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runjing.base.TitleBarFragment;
import com.runjing.wineworld.R;

/**
 * @Created: qianxs  on 2020.07.21 09:46.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.21 09:46.
 * @Remark:
 */
public class WebFragment extends TitleBarFragment {

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_forget_pwd, null);
    }
}
