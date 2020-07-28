package com.runjing.ui.address;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.address.AddressBean;

import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.ui.home.HomeFragment;
import com.runjing.utils.EmptyUtil;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.RecyclerViewItemDecoration;

import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class SelectAddressFragment extends TitleBarFragment implements PoiSearch.OnPoiSearchListener, TextWatcher, Inputtips.InputtipsListener {
    @BindView(id = R.id.recy_rece_content)
    RecyclerView recyAddress;
    @BindView(id = R.id.lv_near_address)
    RecyclerView recyNearAddress;
    @BindView(id = R.id.tv_select_address,click = true)
    TextView tv_select_address;
    @BindView(id = R.id.tv_sq_address)
    TextView tv_sq_address;
    @BindView(id = R.id.layout_my_rece_add)
    LinearLayout layout_my_rece_add;
    @BindView(id = R.id.ll_zhankai_address)
    LinearLayout ll_zhankai_address;
    @BindView(id = R.id.tv_re_local, click = true)
    private TextView tv_re_local;
    @BindView(id = R.id.ll_near_address, click = true)
    private LinearLayout ll_near_address;
    @BindView(id = R.id.edit_poi)
    private EditText edit_poi;
    private RecyclerView.LayoutManager mLayoutManager, mManager;
    List<AddressBean> addressList;
    List<AddressBean> nearList;
    ReceiveAddressAdapter receiveAddressAdapter;
    private PoiSearch.Query query;// Poi查询条件类
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private LatLonPoint lp;
    private List<PoiItem> poiItems;// poi数据
    private List<PoiItem> mpois;// poi数据
    private String searchKeyword, searchCity;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_select_address, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 1;
//        actionBarRes.leftVisiable = 1;
        actionBarRes.middleTitle = "选择收货地址";
    }

    @Override
    protected void initData() {
        super.initData();
        nearList = new ArrayList<>();
        lp = new LatLonPoint(Double.valueOf(LocalUtil.lat), Double.valueOf(LocalUtil.lon));
        tv_select_address.setText(LocalUtil.city);
        tv_sq_address.setText(LocalUtil.address);
        getData();
//        getNearData();
        doSearchQuery();

    }


    private List<AddressBean> getData() {
        addressList = new ArrayList<>();
//        for(int i = 0;i<=5;i++){
//            AddressBean bean = new AddressBean();
//            bean.setAddress("大兴区亦庄经济开发区中航国际广场L1栋601");
//            bean.setMark("1");
//            bean.setName("张某某");
//            bean.setPhone("13555555555");
//            addressList.add(bean);
//        }
        return addressList;
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        edit_poi.addTextChangedListener(this);
        if (addressList != null && addressList.size() > 0) {
            buildNearAddress(recyAddress, addressList,Appconfig.TAG_ONE);
        } else {
            layout_my_rece_add.setVisibility(View.GONE);
            ll_zhankai_address.setVisibility(View.GONE);
        }

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_re_local:
                HomeFragment.startLocation();
                doSearchQuery();
                break;
            case R.id.tv_select_address:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.OpenCity);
                break;
        }
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }


    /**
     * 开始进行poi搜索
     */
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        currentPage = 0;
        query = new PoiSearch.Query(LocalUtil.poiName, "", LocalUtil.city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        if (lp != null) {
            poiSearch = new PoiSearch(outsideAty, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }


    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果

                if (result.getQuery().equals(query)) {// 是否是同一条
                    if (result.getQuery().equals(query)) {// 是否是同一条
                        poiResult = result;

                        poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        List<SuggestionCity> suggestionCities = poiResult
                                .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                        if (poiItems != null && poiItems.size() > 0) {

                            for (int i = 0; i < poiItems.size(); i++) {
                                String addr = poiItems.get(i).getTitle();
                                AddressBean bean = new AddressBean();
                                bean.setAddress(addr);
                                nearList.add(bean);
                            }
                            buildNearAddress(recyNearAddress,nearList,Appconfig.TAG_TWO);
                        }
                    }
                } else {
                    ViewInject.showCenterToast(outsideAty, "对不起，没有搜索到相关数据！");
                }
            }
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * 地址绑定adapter
     *
     * @param
     * @param mList
     */
    private void buildNearAddress(RecyclerView receLitView, List<AddressBean> mList, int type) {

        mManager = new LinearLayoutManager(getActivity());
        receiveAddressAdapter = new ReceiveAddressAdapter(getActivity(), mList, type);
        receLitView.setHasFixedSize(false);
        receLitView.setLayoutManager(mManager);
        receLitView.setNestedScrollingEnabled(false);
        receLitView.setAdapter(receiveAddressAdapter);
        receLitView.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        ll_near_address.setVisibility(View.GONE);
        String newText = s.toString().trim();
        if (!EmptyUtil.IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, LocalUtil.city);
            Inputtips inputTips = new Inputtips(outsideAty, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }else{
            ll_near_address.setVisibility(View.VISIBLE);
            buildNearAddress(recyNearAddress,nearList,Appconfig.TAG_TWO);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            List<AddressBean> searchList = new ArrayList<AddressBean>();
            for (int i = 0; i < tipList.size(); i++) {
                AddressBean addressBean = new AddressBean();
                addressBean.setAddress(tipList.get(i).getName());
                addressBean.setPoiAddress(tipList.get(i).getAddress());
                searchList.add(addressBean);
            }
            buildNearAddress(recyNearAddress, searchList, Appconfig.TAG_THREE);
        }

    }

}