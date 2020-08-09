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

import com.runjing.base.BaseRequest;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.StoreDetailRequest;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.store.DetailGoodBean;
import com.runjing.bean.response.store.DetailStroeBean;
import com.runjing.bean.response.store.StoreDetailData;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.http.ApiServices;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.GlideUtils;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * @Created: qianxs  on 2020.07.26 19:56.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.26 19:56.
 * @Remark:
 */
public class StoreDeatailFragment extends TitleBarFragment {

    @BindView(id = R.id.frag_srl_content)
    private RefreshLayout refreshLayout;
    @BindView(id = R.id.frag_iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.lay_search_goods, click = true)
    private LinearLayout ll_search;
    @BindView(id = R.id.frag_et_search)
    private TextView et_search;
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
    @BindView(id = R.id.lay_ll_nomore)
    private LinearLayout ll_nomore;
    private StoreGoodAdapter adapter;
    private int storeId;
    private int pageNo = 1;
    private StoreDetailRequest request;
    private List<GoodBean.DataBean.ListBean> list;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        KeyBoardUtil.init(outsideAty);
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
        return inflater.inflate(R.layout.fragment_store_detail, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        list = new ArrayList<>();
        refreshLayout.setRefreshHeader(new RJRefreshHeader(outsideAty).
                setNormalColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setAnimatingColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new RJRefreshFooter(LayoutInflater.from(outsideAty).inflate(R.layout.layout_recycler_footer, null)));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNo = 1;
                initData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                initData();
            }
        });
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
                case R.id.lay_search_goods:
                    AppMethod.postShowWith(outsideAty, SimpleBackPage.Search);
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
//                    AppMethod.showMsg(getActivity(), "测试");
                    break;
                case R.id.frag_tv_settlement:
                    break;
            }
        }
    }


    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY);
        if (bundle != null) storeId = bundle.getInt(Appconfig.TAG);
        request = new StoreDetailRequest();
        request.setPageNo(pageNo);
        request.setPageSize(Appconfig.pageSize);
        request.setStoreId(storeId + "");
        RetrofitClient retrofitClient = RetrofitClient.getInstance(outsideAty, RetrofitClient.baseUrl);
        Observable<DetailStroeBean> store = retrofitClient
                .create(ApiServices.class)
                .getStoreDetail(ApiServices.MyRequestBody.createBody(request))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<GoodBean> good = retrofitClient
                .create(ApiServices.class)
                .getStoreGood(ApiServices.MyRequestBody.createBody(request))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<StoreDetailData> homeObservable = Observable.zip(store, good, new Func2<DetailStroeBean, GoodBean, StoreDetailData>() {
            @Override
            public StoreDetailData call(DetailStroeBean detailStroeBean, GoodBean detailGoodBean) {
                return new StoreDetailData(detailStroeBean, detailGoodBean);
            }
        });
        homeObservable.subscribe(new Subscriber<StoreDetailData>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                KLog.e("onError", e);
            }

            @Override
            public void onNext(StoreDetailData response) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                setData(response);
            }
        });
    }

    /**
     * 数据部署
     * @param response
     */
    public void setData(StoreDetailData response) {
        if (response != null) {
            DetailStroeBean.DataBean detailStroe = response.getDetailStroe().getData();
            if (detailStroe != null) {
                GlideUtils.getInstance().displayImageCenter(iv_store, detailStroe.getImage(), iv_store.getContext(), R.mipmap.ic_launcher);
                tv_name.setText(AppMethod.setDefault(detailStroe.getName()));
                tv_address.setText(AppMethod.setDefault(detailStroe.getAddressDetail()));
                tv_distance.setText(AppMethod.setDefault(detailStroe.getDistance()) + "km");
                if (detailStroe.getStatus() == 1) {
                    tv_rest.setVisibility(View.GONE);
                } else if (detailStroe.getStatus() == 2){
                    tv_rest.setVisibility(View.VISIBLE);
                }
            }
            GoodBean detailGood = response.getDetailGood();
            if (detailGood != null) {
                if (pageNo == 1) {
                    list.clear();
                    list.addAll(detailGood.getData().getList());
                    refreshLayout.setEnableLoadMore(true);
                    ll_nomore.setVisibility(View.GONE);
                }else {
                    list.addAll(detailGood.getData().getList());
                    if (detailGood.getData().getList().size() < Appconfig.pageSize) {
                        refreshLayout.setEnableLoadMore(false);
                        ll_nomore.setVisibility(View.VISIBLE);
                    } else {
                        refreshLayout.setEnableLoadMore(true);
                        ll_nomore.setVisibility(View.GONE);
                    }
                }
                adapter.setData(list);
            }
        }
    }
}
