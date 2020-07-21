package com.runjing.ui.good;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class GoodDetailFragment extends TitleBarFragment {

    @BindView(id = R.id.frag_banner)
    private Banner banner;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_good_detail, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 0;
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        AppMethod.GuildBanner(outsideAty, banner, HomeData.getBanner());
    }
}
