package com.runjing.ui.store;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.base.BaseRequest;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.BannerRequest;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.HomeData;
import com.runjing.bean.response.home.HomeStoreBean;
import com.runjing.common.Appconfig;
import com.runjing.http.ApiServices;
import com.runjing.http.net.RetrofitClient;
import com.runjing.ui.home.HomeAdapter;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.StatusBarUtil;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

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
    private HomeAdapter adapter;
    @BindView(id = R.id.frag_iv_l)
    private ImageView iv_l;
    @BindView(id = R.id.frag_iv_l1)
    private ImageView iv_l1;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_store_list, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 1;
        actionBarRes.leftVisiable = 1;
        actionBarRes.middleTitle = "门店列表";
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        adapter = new HomeAdapter(getActivity());
        rv_content.setHasFixedSize(false);
        rv_content.setNestedScrollingEnabled(false);
        rv_content.setAdapter(adapter);
    }


    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }

    public void getData() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(outsideAty, RetrofitClient.baseUrl);
        Observable<DistrictBean> district = retrofitClient
                .create(ApiServices.class)
                .getDistrict(ApiServices.MyRequestBody.createBody(new BannerRequest()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<HomeStoreBean> store = retrofitClient
                .create(ApiServices.class)
                .getStore(ApiServices.MyRequestBody.createBody(new BaseRequest()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<com.runjing.bean.response.home.HomeData> homeObservable = Observable.zip(district, store, new Func2<DistrictBean, HomeStoreBean, com.runjing.bean.response.home.HomeData>() {
            @Override
            public com.runjing.bean.response.home.HomeData call(DistrictBean districtBean, HomeStoreBean homeStoreBean) {
                return new com.runjing.bean.response.home.HomeData(districtBean, homeStoreBean);
            }
        });
        homeObservable.subscribe(new Subscriber<com.runjing.bean.response.home.HomeData>() {
            @Override
            public void onCompleted() {
                KLog.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                KLog.e("onError", e);
            }

            @Override
            public void onNext(HomeData homeData) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                setData(homeData);
            }
        });
    }

    public void setData(HomeData response) {
        String city = TextUtils.isEmpty(MMKVUtil.getInstance().decodeString(Appconfig.city))
                ? LocalUtil.city : MMKVUtil.getInstance().decodeString(Appconfig.city);
        if (isOpenCity(response.getDistrictBean().getData(), city)) {
            int type = response.getHomeStoreBean().getData().getType();
            if (type == 1) {
                ll_store_status.setVisibility(View.GONE);
            } else if (type == 2) {
                ll_store_status.setVisibility(View.VISIBLE);
                iv_l.setVisibility(View.VISIBLE);
                iv_l1.setVisibility(View.VISIBLE);
                tv_storemsg.setText(getResources().getString(R.string.string_next_city));
                tv_storeStaus.setText(getResources().getString(R.string.string_next_city1));
            }
            response.setItemTpye(HomeData.TYPE_ITEM_STORE);
            rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
            rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                    getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 10), 0, 0));
        } else {
            ll_store_status.setVisibility(View.VISIBLE);
            iv_l.setVisibility(View.GONE);
            iv_l1.setVisibility(View.GONE);
            tv_storemsg.setText(getResources().getString(R.string.home_service_error));
            tv_storeStaus.setText(getResources().getString(R.string.string_open_city));
            response.setItemTpye(HomeData.TYPE_ITEM_CITY);
            ll_store_status.setVisibility(View.VISIBLE);
            rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
            rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                    getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
        }
        adapter.setData(response);
    }

    /**
     * @param data
     * @param city
     */
    public boolean isOpenCity(List<DistrictBean.DataBean> data, String city) {
        if (null != data && data.size() > 0 && !TextUtils.isEmpty(city)) {
            for (DistrictBean.DataBean dataBean : data) {
                if (TextUtils.equals(city, dataBean.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
