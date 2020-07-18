package com.runjing.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.HomeRequest;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.response.home.HomeResponse;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.common.BaseUrl;
import com.runjing.http.MyRequestCallBack;
import com.runjing.http.OkHttpUtil;
import com.runjing.utils.SpacesItemDecoration;
import com.runjing.widget.LoadingDialog;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;


/**
 * @Created: qianxs  on 2020.07.16 23:20.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:20.
 * @Remark:
 */
public class HomeFragment extends TitleBarFragment {

    @BindView(id = R.id.frag_srl_content)
    private RefreshLayout refreshLayout;
    @BindView(id = R.id.frg_sv_contennt)
    private NestedScrollView sv_content;
    @BindView(id = R.id.frag_ll_select, click = true)
    private LinearLayout ll_select;
    @BindView(id = R.id.frag_tv_address)
    private TextView tv_address;
    @BindView(id = R.id.lay_iv_shop)
    private ImageView iv_shop;
    @BindView(id = R.id.frag_ll_search, click = true)
    private LinearLayout ll_search;
    @BindView(id = R.id.lay_ll_order, click = true)
    private LinearLayout ll_order;
    @BindView(id = R.id.lay_ll_store, click = true)
    private LinearLayout ll_store;
    @BindView(id = R.id.lay_ll_coin, click = true)
    private LinearLayout ll_coin;
    @BindView(id = R.id.banner)
    private Banner banner;
    @BindView(id = R.id.lay_ll_store_status)
    private LinearLayout ll_store_status;
    @BindView(id = R.id.lay_tv_storemsg)
    private TextView tv_storemsg;
    @BindView(id = R.id.lay_tv_store_status)
    private TextView tv_status;
    @BindView(id = R.id.lay_rv_content)
    private RecyclerView rv_content;
    private RecyclerView.LayoutManager mLayoutManager;
    private LoadingDialog loadingDialog;
    private HomeAdapter homeAdapter;
    private int mSuspensionHeight;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        loadingDialog = new LoadingDialog(outsideAty);
        return inflater.inflate(R.layout.frag_home, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 2;
        actionBarRes.middleLayoutColor = R.color.color_ffffff;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        refreshLayout.setRefreshHeader(new RJRefreshHeader(outsideAty).
                setNormalColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setAnimatingColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setSpinnerStyle(SpinnerStyle.Scale));
        View view = LayoutInflater.from(outsideAty).inflate(R.layout.layout_recycler_footer, null);
        refreshLayout.setRefreshFooter(new RJRefreshFooter(view));
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
        mLayoutManager = new StaggeredGridLayoutManager(Appconfig.TAG_TWO, StaggeredGridLayoutManager.VERTICAL);
        homeAdapter = new HomeAdapter(getActivity());
        rv_content.setHasFixedSize(false);
        rv_content.setLayoutManager(mLayoutManager);
        rv_content.setNestedScrollingEnabled(false);
        rv_content.setAdapter(homeAdapter);
        rv_content.addItemDecoration(new SpacesItemDecoration(DensityUtils.dip2dp(getActivity(), 7), STAGGEREDGRIDLAYOUT));

        setData(HomeData.getHomeData());
    }

    @Override
    protected void initData() {
        super.initData();
//        getData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.lay_ll_search:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.Search);
                break;
            case R.id.lay_ll_order:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.StoreList);
                break;
            case R.id.lay_ll_store:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.StoreList);
                break;
            case R.id.lay_ll_coin:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.StoreList);
                break;
            case R.id.frag_ll_select:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.SelectAddress);
                break;
            case R.id.frag_iv_shop:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.ShopCat);
                break;
        }
    }

    public void getData() {
        loadingDialog.show();
        HomeRequest homeRequest = new HomeRequest();
        OkHttpUtil.postRequest(BaseUrl.AppMain, homeRequest, HomeResponse.class, new MyRequestCallBack<HomeResponse>() {
            @Override
            public void onPostResponse(HomeResponse response) {
                loadingDialog.dismiss();
                setData(response.getData());
            }

            @Override
            public void onPostErrorResponse(Exception e, String msg) {

            }

            @Override
            public void onNoNetWork() {

            }
        });
    }

    /**
     * 设置数据
     *
     * @param homeBean
     */
    public void setData(HomeBean homeBean) {
        AppMethod.bannerWeight(outsideAty, banner, homeBean.getImages());
        homeAdapter.setData(homeBean, 0);
    }
}


