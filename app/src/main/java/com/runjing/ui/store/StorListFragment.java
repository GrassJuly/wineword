package com.runjing.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.home.def.HomeBean;
import com.runjing.bean.test.HomeData;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class StorListFragment extends TitleBarFragment {

    @BindView(id = R.id.frag_srl_content)
    private RefreshLayout refreshLayout;
    @BindView(id = R.id.frag_ll_store_status)
    private LinearLayout ll_store_status;
    @BindView(id = R.id.frag_tv_storemsg)
    private TextView tv_storemsg;
    @BindView(id = R.id.frag_tv_store_status)
    private TextView tv_storeStaus;
    @BindView(id = R.id.frag_rv_content)
    private RecyclerView rv_content;
    private StoreAdapter adapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_store_list, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 1;
        actionBarRes.titleBarColor = R.color.color_F80000;
        actionBarRes.leftVisiable = 1;
        actionBarRes.middleTitle ="门店列表";
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        refreshLayout.setRefreshHeader(new RJRefreshHeader(outsideAty).
                setNormalColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setAnimatingColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new RJRefreshFooter(LayoutInflater.from(outsideAty).inflate(R.layout.layout_recycler_footer, null)));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
        });
        adapter = new StoreAdapter(getActivity());
        rv_content.setHasFixedSize(false);
        rv_content.setNestedScrollingEnabled(false);
        rv_content.setAdapter(adapter);
        setData(HomeData.getHomeData());
    }


    /**
     * 设置数据
     *
     * @param homeBean
     */
    public void setData(HomeBean homeBean) {
        if (homeBean != null) {
            if (homeBean.getItemTpye() == HomeBean.TYPE_ITEM_CITY) {
                ll_store_status.setVisibility(View.VISIBLE);
                rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
                rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                        getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
            }else if (homeBean.getItemTpye() == HomeBean.TYPE_ITEM_STORE) {
                ll_store_status.setVisibility(View.VISIBLE);
                rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
                rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                        getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
            }
        }
        adapter.setData(homeBean);
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }
}
