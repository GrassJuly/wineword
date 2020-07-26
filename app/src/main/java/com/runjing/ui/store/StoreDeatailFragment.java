package com.runjing.ui.store;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.runjing.base.TitleBarFragment;
import com.runjing.common.AppMethod;
import com.runjing.ui.good.DiscountAdapter;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.26 19:56.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.26 19:56.
 * @Remark:
 */
public class StoreDeatailFragment extends TitleBarFragment implements TextWatcher {

    @BindView(id = R.id.frag_iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.frag_et_search)
    private EditText et_search;
    @BindView(id = R.id.frag_iv_store)
    private ImageView iv_store;
    @BindView(id = R.id.frag_tv_name)
    private TextView tv_name;
    @BindView(id = R.id.frag_tv_rest)
    private TextView tv_rest;
    @BindView(id = R.id.frag_tv_address)
    private TextView tv_address;
    @BindView(id = R.id.frag_tv_distance)
    private TextView tv_distance;
    @BindView(id = R.id.frag_ll_location, click = true)
    private LinearLayout ll_location;
    @BindView(id = R.id.frag_rv_content)
    private RecyclerView rv_content;
    @BindView(id = R.id.frag_rl_tab)
    private RelativeLayout rl_tab;
    @BindView(id = R.id.frag_il_shop, click = true)
    private RelativeLayout rl_shop;
    @BindView(id = R.id.frag_iv_shop, click = true)
    private ImageView iv_shop;
    @BindView(id = R.id.frag_tv_shopNum)
    private TextView tv_shopNum;
    @BindView(id = R.id.frag_tv_money)
    private TextView tv_money;
    @BindView(id = R.id.frag_tv_reducemoney)
    private TextView tv_reduceMoney;
    @BindView(id = R.id.frag_tv_add, click = true)
    private TextView tv_add;
    @BindView(id = R.id.frag_tv_settlement, click = true)
    private TextView tv_settlement;

    private StoreGoodAdapter adapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        KeyBoardUtil.init(outsideAty);
        return inflater.inflate(R.layout.fragment_store_detail, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        adapter = new StoreGoodAdapter(getActivity());
        rv_content.setHasFixedSize(false);
        rv_content.setNestedScrollingEnabled(false);
        rv_content.setLayoutManager(new GridLayoutManager(outsideAty, 2));
        rv_content.setAdapter(adapter);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.frag_iv_back:
                    finish();
                    break;
                case R.id.lay_rl_good:
                case R.id.lay_tv_good:

                    break;
                case R.id.lay_rl_detail:
                case R.id.lay_tv_detail:
                    break;
                case R.id.frag_iv_shop:
                    if (getResources().getString(R.string.tag_no).equals(v.getTag())) {
                        iv_shop.setTag(getResources().getString(R.string.tag_yes));
                        rl_shop.setVisibility(View.VISIBLE);
                        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff), 66);
                    } else {
                        iv_shop.setTag(getResources().getString(R.string.tag_no));
                        rl_shop.setVisibility(View.GONE);
                        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff));
                    }
                    break;
                case R.id.frag_tv_add:
                    AppMethod.showMsg(getActivity(), "测试");
                    break;
                case R.id.frag_tv_settlement:
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
