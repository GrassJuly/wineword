package com.runjing.ui.ordre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runjing.base.TitleBarFragment;
import com.runjing.wineworld.R;

/**
 * @Created: qianxs  on 2020.07.17 11:26.
 * @Describe：商品详情
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:26.
 * @Remark:
 */
public class OrderDetaiFragment extends TitleBarFragment {

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_order_detail, null);
    }
}
