package com.runjing.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.TitleBarFragment;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

/**
 * 购物车
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class ShopCarlFragment extends TitleBarFragment {
    @BindView(id = R.id.recy_shop_listview)
    private RecyclerView recy_shop_listview;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_layout_shopping_cart, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 1;
        actionBarRes.middleTitle = "购物车";
    }

    @Override
    protected void initData() {
        super.initData();
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
