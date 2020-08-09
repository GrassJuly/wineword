package com.runjing.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.SearchGoodRequest;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.bean.response.store.DetailStroeBean;
import com.runjing.bean.response.store.StoreDetailData;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.bean.response.home.def.GoodTagBean;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.ui.login.LoginActivity;
import com.runjing.ui.store.StoreGoodAdapter;
import com.runjing.utils.GlideUtils;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.SpacesItemDecoration;
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
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;

/**
 * 搜索商品结果列表
 */
public class SearchGoodsFragment extends TitleBarFragment implements TextWatcher {

    @BindView(id = R.id.frag_srl_content)
    private RefreshLayout refreshLayout;
    @BindView(id = R.id.frag_rv_content)
    private RecyclerView rv_content;

    @BindView(id = R.id.edit_search_goods)
    private EditText edit_search_goods;
    @BindView(id = R.id.img_search_back, click = true)
    private ImageView img_search_back;
    @BindView(id = R.id.lay_ll_nomore)
    private LinearLayout ll_nomore;
    @BindView(id = R.id.layout_search_no_goods)
    private LinearLayout ll_nongoods;

    private SearchGoodsAdapter goodsAdapter;
    private String keyboard;
    private int pageNo = 1;
    private SearchGoodRequest request;
    private StoreGoodAdapter adapter;
    private List<GoodBean.DataBean.ListBean> list;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
        KeyBoardUtil.init(outsideAty);
        bundle = outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY);
        if (bundle != null) {
            keyboard = bundle.getString(Appconfig.DATA_KEY);
        }
        return inflater.inflate(R.layout.fragment_search_good, null);
    }

    @Override
    protected void initData() {
        super.initData();
        edit_search_goods.setText(keyboard);
        getData(keyboard);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        list = new ArrayList<>();
        edit_search_goods.addTextChangedListener(this);
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
        switch (v.getId()) {
            case R.id.img_search_back:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        getData(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void getData(String keyboard) {
        request = new SearchGoodRequest();
        request.setGoodsName(keyboard);
        request.setPageNo(pageNo);
        request.setPageSize(Appconfig.pageSize);
        RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl).execute(
                RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl)
                        .create(ApiServices.class)
                        .getGood(ApiServices.MyRequestBody.createBody(request)),
                new BaseSubscriber<GoodBean>(outsideAty) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        KLog.e("Lyk", e.getMessage());
                        ll_nongoods.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(GoodBean response) {
                        setData(response);
                    }
                });

    }

    public void setData(GoodBean response) {
        if (response != null) {
            ll_nongoods.setVisibility(View.GONE);
            if (pageNo == 1) {
                list.clear();
                list.addAll(response.getData().getList());
                refreshLayout.setEnableLoadMore(true);
                ll_nomore.setVisibility(View.GONE);
            } else {
                list.addAll(response.getData().getList());
                if (response.getData().getList().size() < Appconfig.pageSize) {
                    refreshLayout.setEnableLoadMore(false);
                    ll_nomore.setVisibility(View.VISIBLE);
                } else {
                    refreshLayout.setEnableLoadMore(true);
                    ll_nomore.setVisibility(View.GONE);
                }
            }
            adapter.setData(list);
        }else {
            ll_nongoods.setVisibility(View.VISIBLE);
        }
    }
}
