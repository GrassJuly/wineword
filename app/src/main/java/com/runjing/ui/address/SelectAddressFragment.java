package com.runjing.ui.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runjing.base.TitleBarFragment;
import com.runjing.wineworld.R;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class SelectAddressFragment extends TitleBarFragment {
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        return inflater.inflate(R.layout.frag_select_address, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 2;
        actionBarRes.leftImageVisible = 1;
        actionBarRes.rightImageVisible = 1;
        actionBarRes.leftButtonResource = R.mipmap.fh_back;
        actionBarRes.middleTitle = "选择收货地址";
        actionBarRes.middleTitleColor = outsideAty.tv_home_middle_title.getResources().getColor(R.color.color_333333);
        actionBarRes.topLayoutBack = outsideAty.ll_base_title.getResources().getColor(R.color.color_ffffff);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }
}
