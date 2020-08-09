package com.runjing.ui.address;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.model.LatLngBounds;
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
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.PermissionUtils;
import com.runjing.utils.StatusBarUtil;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.RecyclerViewItemDecoration;

import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;
import org.runjing.rjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.runjing.utils.location.LocalUtil.isReplace;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class SelectAddressFragment extends TitleBarFragment implements PoiSearch.OnPoiSearchListener, TextWatcher, Inputtips.InputtipsListener,LocalUtil.onReplace {
    @BindView(id = R.id.recy_rece_content)
    RecyclerView recyAddress;
    @BindView(id = R.id.lv_near_address)
    RecyclerView recyNearAddress;
    @BindView(id = R.id.tv_select_address)
    TextView tv_select_address;
    @BindView(id = R.id.ll_select_address,click = true)
    LinearLayout ll_select_address;
    @BindView(id = R.id.tv_sq_address)
    TextView tv_sq_address;
    @BindView(id = R.id.layout_my_rece_add)
    LinearLayout layout_my_rece_add;
    @BindView(id = R.id.ll_zhankai_address)
    LinearLayout ll_zhankai_address;
    @BindView(id = R.id.tv_re_local, click = true)
    private TextView tv_re_local;
    @BindView(id = R.id.txt_add_address_click, click = true)
    private TextView txt_add_address_click;
    @BindView(id = R.id.ll_near_address, click = true)
    private LinearLayout ll_near_address;
    @BindView(id = R.id.edit_poi)
    private EditText edit_poi;
    @BindView(id = R.id.img_select_search_back,click = true)
    private ImageView img_select_search_back;

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
    private String mark; //页面跳转来源
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
        bundle = outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY);
       if(bundle!=null) {
           mark = bundle.getString(Appconfig.DATA_KEY);
       }
        Log.d("2222",mark+"");
        return inflater.inflate(R.layout.frag_select_address, null);
    }



    @Override
    protected void initData() {
        super.initData();

        nearList = new ArrayList<>();
        if(!StringUtils.isEmpty(LocalUtil.lat) && !StringUtils.isEmpty(LocalUtil.lon)) {
            lp = new LatLonPoint(Double.valueOf(LocalUtil.lat), Double.valueOf(LocalUtil.lon));
        }
        tv_select_address.setText(LocalUtil.city);
        String address = MMKVUtil.getInstance().decodeString(Appconfig.address);
        tv_sq_address.setText(address);
        if(!StringUtils.isEmpty(mark)&& !"add".equals(mark)){
            getData();
        }

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
                if(!checkGPSIsOpen()){//未开启定位权限
                   openGPSSettings();
                }else{
                    LocalUtil.setListener(this);
                    HomeFragment.initLocation();
                    HomeFragment.startLocation();
                }

                break;
            case R.id.ll_select_address:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.OpenCity);
                break;
            case R.id.img_select_search_back:
                finish();
                break;
            case R.id.txt_add_address_click:
                AppMethod.postShowWith(outsideAty, SimpleBackPage.AddAddress);
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
    public void doSearchQuery() {
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
                        poiResult = result;

                        poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        List<SuggestionCity> suggestionCities = poiResult
                                .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                        if (poiItems != null && poiItems.size() > 0) {
                            nearList.clear();
                            for (int i = 0; i < poiItems.size(); i++) {
                                String addr = poiItems.get(i).getTitle();
                               String lat = poiItems.get(i).getLatLonPoint().getLatitude() +"";
                               String lon =  poiItems.get(i).getLatLonPoint().getLongitude()+"";
                                AddressBean bean = new AddressBean();
                                bean.setAddress(addr);
                                bean.setLat(lat);
                                bean.setLon(lon);
                                nearList.add(bean);
                                Log.d("1111",lat +" "+ lon);
                            }


                            buildNearAddress(recyNearAddress,nearList,Appconfig.TAG_TWO);
                        }
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
       if(type == Appconfig.TAG_TWO) {
           ll_near_address.setVisibility(View.VISIBLE);
       }
      if(mManager==null) {
          mManager = new LinearLayoutManager(getActivity());
          receLitView.setHasFixedSize(false);
          receLitView.setLayoutManager(mManager);
          receLitView.setNestedScrollingEnabled(false);
          receLitView.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                  getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
      }
        receiveAddressAdapter = new ReceiveAddressAdapter(getActivity(), mList, type,mark);
        receLitView.setAdapter(receiveAddressAdapter);

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
                Tip tip = tipList.get(i);
               if(tip!=null) {
                   Log.d("aaa",tip.getDistrict());
                   AddressBean addressBean = new AddressBean();
                   addressBean.setAddress(tip.getName());
                   addressBean.setPoiAddress(tip.getAddress());
                   LatLonPoint ll = tip.getPoint();
                   if(ll!=null) {
                       Double lat = ll.getLatitude();
                       Double lon = ll.getLongitude();
                       addressBean.setLon(lon + "");
                       addressBean.setLat(lat + "");
                   }
                   searchList.add(addressBean);
               }
            }
            buildNearAddress(recyNearAddress, searchList, Appconfig.TAG_THREE);
        }

    }

    @Override
    public void replace(boolean b) {
        if(b){
            LocalUtil.setListener(null);
            Log.d("aaaa",b+"");
            if(!StringUtils.isEmpty(LocalUtil.lat) && !StringUtils.isEmpty(LocalUtil.lon)) {
                lp = new LatLonPoint(Double.valueOf(LocalUtil.lat), Double.valueOf(LocalUtil.lon));
            }
            doSearchQuery();
            isReplace = false;
        }
    }

    @Override
    public void onActionBar() {
        super.onActionBar();
        tv_select_address.setText(LocalUtil.city);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 判断是否有定位权限
     * @return
     */
    private boolean checkGPSIsOpen() {
     boolean isOpen;
     LocationManager locationManager = (LocationManager) outsideAty.getSystemService(Context.LOCATION_SERVICE);
     isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
      return isOpen;
    }
    private int GPS_REQUEST_CODE = 10;
    private void openGPSSettings() {

            //没有打开则弹出对话框
            new AlertDialog.Builder(outsideAty)
                    .setTitle("")
                    .setMessage("请到设置-隐私-定位服务中开启京东\n" +
                            "酒世界定位服务，以获取附近门店信\n" +
                            "息")
                    // 拒绝, 退出应用
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })

                    .setPositiveButton("去设置",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, GPS_REQUEST_CODE);
                                }
                            })

                    .setCancelable(false)
                    .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPS_REQUEST_CODE){
            tv_select_address.setText(LocalUtil.city);
            tv_sq_address.setText(LocalUtil.address);
            doSearchQuery();
        }
    }

}