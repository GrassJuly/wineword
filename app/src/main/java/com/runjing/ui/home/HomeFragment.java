package com.runjing.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.runjing.MyApplication;
import com.runjing.base.BaseRequest;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.BannerRequest;
import com.runjing.bean.request.GoodRequest;
import com.runjing.bean.request.HomeRequest;
import com.runjing.bean.response.home.BannerBean;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeData;
import com.runjing.bean.response.home.HomeStoreBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.NetworkUtil;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.PermissionUtils;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.SpacesItemDecoration;
import com.runjing.utils.StatusBarUtil;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.widget.LoadingDialog;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func4;
import rx.schedulers.Schedulers;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;


/**
 * @Created: qianxs  on 2020.07.16 23:20.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:20.
 * @Remark:
 */
public class HomeFragment extends TitleBarFragment implements HomeObserver {

    @BindView(id = R.id.fragt_ll_content)
    private LinearLayout ll_content;
    @BindView(id = R.id.frag_srl_content)
    private RefreshLayout refreshLayout;
    @BindView(id = R.id.frg_sv_contennt)
    private NestedScrollView sv_content;
    @BindView(id = R.id.frag_ll_select, click = true)
    private LinearLayout rl_select;
    @BindView(id = R.id.frag_tv_address)
    public static TextView tv_address;
    @BindView(id = R.id.lay_iv_shop, click = true)
    private ImageView iv_shop;

    @BindView(id = R.id.frag_ll_Tselect, click = true)
    private LinearLayout rl_Tselect;
    @BindView(id = R.id.frag_ll_localNet)
    public LinearLayout ll_localNet;
    @BindView(id = R.id.lay_iv_Tshop, click = true)
    private ImageView iv_Tshop;
    @BindView(id = R.id.frg_ll_nonet)
    private LinearLayout ll_nonet;
    @BindView(id = R.id.frg_ll_nolocal)
    private LinearLayout ll_nolocal;
    @BindView(id = R.id.lay_tv_localstart, click = true)
    private TextView tv_start;
    @BindView(id = R.id.lay_tv_manual, click = true)
    private TextView tv_manual;
    @BindView(id = R.id.lay_tv_refresh, click = true)
    private TextView tv_refresh;

    @BindView(id = R.id.frag_ll_search, click = true)
    private LinearLayout ll_search;
    @BindView(id = R.id.frg_ll_home)
    private LinearLayout ll_home;
    @BindView(id = R.id.lay_ll_order, click = true)
    private LinearLayout ll_order;
    @BindView(id = R.id.lay_ll_store, click = true)
    private LinearLayout ll_store;
    @BindView(id = R.id.lay_ll_coin, click = true)
    private LinearLayout ll_coin;
    @BindView(id = R.id.lay_banner)
    private LinearLayout ll_banner;
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
    @BindView(id = R.id.lay_ll_nomore)
    private LinearLayout ll_nomore;
    private RecyclerView.LayoutManager mLayoutManager;
    private LoadingDialog loadingDialog;
    private HomeAdapter homeAdapter;
    private int mSuspensionHeight;
    public static AMapLocationClient locationClient = null;
    public static AMapLocationClientOption locationOption = null;
    public static String strLocation;
    private HomeRequest request;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        loadingDialog = new LoadingDialog(outsideAty);
        request = new HomeRequest();
        return inflater.inflate(R.layout.frag_home, null);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_F80000));
    }


    @Override
    protected void initData() {
        super.initData();
        initLocation();
        startLocation();
        getData();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        tv_address.setText("正在获取定位信息...");
        refreshLayout.setRefreshHeader(new RJRefreshHeader(outsideAty).
                setNormalColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setAnimatingColor(outsideAty.getResources().getColor(R.color.color_99000000)).
                setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new RJRefreshFooter(LayoutInflater.from(outsideAty).inflate(R.layout.layout_recycler_footer, null)));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        homeAdapter = new HomeAdapter(getActivity());
        rv_content.setHasFixedSize(false);
        rv_content.setNestedScrollingEnabled(false);
        rv_content.setAdapter(homeAdapter);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.lay_tv_manual:
                PermissionUtils.startAppSettings(HomeFragment.this, Appconfig.TAG_ONE);
                break;
            case R.id.lay_tv_localstart:
                startLocation();
                break;
            case R.id.lay_tv_refresh:
                getData();
                break;
            case R.id.lay_ll_search:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.Search);
                break;
            case R.id.lay_ll_order:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.Search);
                break;
            case R.id.lay_ll_store:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.StoreList);
                break;
            case R.id.lay_ll_coin:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.StoreList);
                break;
            case R.id.frag_ll_Tselect:
            case R.id.frag_ll_select:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.AddAddress);
//                AppMethod.postShowWith(outsideAty, SimpleBackPage.SelectAddress);
                break;
            case R.id.frag_iv_Tshop:
            case R.id.frag_iv_shop:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.ShopCat);
                break;
        }
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
        ;
        Observable<BannerBean> banner = retrofitClient
                .create(ApiServices.class)
                .getBanner(ApiServices.MyRequestBody.createBody(new BannerRequest()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        ;
        ;
        Observable<GoodBean> good = retrofitClient
                .create(ApiServices.class)
                .getGood(ApiServices.MyRequestBody.createBody(new GoodRequest()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        ;
        Observable<HomeData> homeObservable = Observable.zip(district, store, good, banner, new Func4<DistrictBean, HomeStoreBean, GoodBean, BannerBean, HomeData>() {
            @Override
            public HomeData call(DistrictBean districtBean, HomeStoreBean homeStoreBean, GoodBean goodBean, BannerBean bannerBean) {
                return new HomeData(districtBean, homeStoreBean, bannerBean, goodBean);
            }
        });
        homeObservable.subscribe(new Subscriber<HomeData>() {
            @Override
            public void onCompleted() {
                KLog.e("onCompleted");
                if (!NetworkUtil.isNetworkAvailable(outsideAty)) {
                    ll_content.setVisibility(View.GONE);
                    ll_localNet.setVisibility(View.VISIBLE);
                    ll_nonet.setVisibility(View.VISIBLE);
                    ll_nolocal.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable e) {
                KLog.e("onError", e);
            }

            @Override
            public void onNext(HomeData homeData) {
                KLog.e(homeData);
                setData(homeData);
            }
        });
    }


    /**
     * @param response
     */
    public void setData(HomeData response) {
        String city = MMKVUtil.getInstance().decodeString(Appconfig.city);
        if (isOpenCity(response.getDistrictBean().getData(), city)) {
            int type = response.getHomeStoreBean().getData().getType();
            if (type == 1) {
                response.setItemTpye(HomeData.TYPE_ITEM_GOOD);
                ll_store_status.setVisibility(View.GONE);
                ll_search.setVisibility(View.VISIBLE);
                ll_banner.setVisibility(View.VISIBLE);
                setMargin(ll_home, 46);
                rv_content.setLayoutManager(new StaggeredGridLayoutManager(Appconfig.TAG_TWO, StaggeredGridLayoutManager.VERTICAL));
                rv_content.addItemDecoration(new SpacesItemDecoration(DensityUtils.dip2dp(getActivity(), 7), STAGGEREDGRIDLAYOUT));
                AppMethod.bannerWeight(outsideAty, banner, response.getBannerBean().getData());
            } else if (type == 2) {
                response.setItemTpye(HomeData.TYPE_ITEM_STORE);
                ll_store_status.setVisibility(View.VISIBLE);
                ll_search.setVisibility(View.GONE);
                ll_banner.setVisibility(View.GONE);
                setMargin(ll_home, 0);
                rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
                rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                        getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
            }
        } else {
            response.setItemTpye(HomeData.TYPE_ITEM_CITY);
            ll_store_status.setVisibility(View.VISIBLE);
            ll_search.setVisibility(View.GONE);
            ll_banner.setVisibility(View.GONE);
            rv_content.setLayoutManager(new LinearLayoutManager(outsideAty));
            rv_content.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                    getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
            setMargin(ll_home, 0);
        }
        KLog.e();
        homeAdapter.setData(response);
    }

    /**
     * @param data
     * @param city
     */
    public boolean isOpenCity(List<DistrictBean.DataBean> data, String city) {
        if (null != data && data.size() > 0 && !TextUtils.isEmpty(city)) {
            for (DistrictBean.DataBean dataBean : data) {
                KLog.e(dataBean.getName());
                if (TextUtils.equals(city, dataBean.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 动态设置margin
     *
     * @param ll
     */
    public void setMargin(LinearLayout ll, int margin) {
        if (ll != null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
            params.topMargin = DensityUtils.dip2dp(ll.getContext(), margin);
            ll.setLayoutParams(params);
        }
    }


    /**
     * 开启定位
     */
    public static void startLocation() {
        //根据控件的选择，重新设置定位参数
        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    public static void resetOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);

        locationOption.setOnceLocationLatest(true);

        try {
            // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
            locationOption.setInterval(1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            // 设置网络请求超时时间
            locationOption.setHttpTimeOut(30000);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocalPresion() {
        ll_content.setVisibility(View.GONE);
        ll_localNet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetAble() {

    }

    /*
      初始化定位
       */
    public static void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(MyApplication.contextApp.getApplicationContext());
        locationOption = LocalUtil.getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(new LocalUtil(tv_address).locationListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        if (PermissionUtils.checkPermissions(outsideAty, Appconfig.needPermissions)) {
            ll_localNet.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
            startLocation();
        } else {
            ll_content.setVisibility(View.GONE);
            ll_localNet.setVisibility(View.VISIBLE);
            ll_nonet.setVisibility(View.GONE);
            ll_nolocal.setVisibility(View.VISIBLE);
        }
    }
}


