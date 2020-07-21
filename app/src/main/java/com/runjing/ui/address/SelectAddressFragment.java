package com.runjing.ui.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.address.AddressBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.ui.home.HomeAdapter;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.SpacesItemDecoration;
import com.runjing.widget.RJRefreshFooter;
import com.runjing.widget.RJRefreshHeader;
import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class SelectAddressFragment extends TitleBarFragment {
    @BindView(id = R.id.recy_rece_content)
    RecyclerView  recyAddress;
    @BindView(id = R.id.lv_near_address)
    RecyclerView recyNearAddress;
    private RecyclerView.LayoutManager mLayoutManager,mManager;
    List<AddressBean> addressList;
    List<AddressBean> nearList;
    ReceiveAddressAdapter receiveAddressAdapter;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        return inflater.inflate(R.layout.frag_select_address, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 2;
        actionBarRes.leftVisiable = 1;
        actionBarRes.middleTitle = "选择收货地址";
    }


    @Override
    protected void initData() {
        super.initData();

        getData();
        getNearData();
    }


    private List<AddressBean> getData(){
        addressList = new ArrayList<>();
        for(int i = 0;i<=5;i++){
            AddressBean bean = new AddressBean();
            bean.setAddress("大兴区亦庄经济开发区中航国际广场L1栋601");
            bean.setMark("1");
            bean.setName("张某某");
            bean.setPhone("13555555555");
            addressList.add(bean);
        }
        return addressList;
    }

    private List<AddressBean> getNearData(){
        nearList = new ArrayList<>();
        for(int i = 0;i<=5;i++){
            AddressBean bean = new AddressBean();
            bean.setAddress("大兴区亦庄经济开发区中航国际广场L1栋601");
            nearList.add(bean);
        }
        return nearList;
    }
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        receiveAddressAdapter = new ReceiveAddressAdapter(getActivity(),addressList,1);
        recyAddress.setHasFixedSize(false);
        recyAddress.setLayoutManager(mLayoutManager);
        recyAddress.setNestedScrollingEnabled(false);
        recyAddress.setAdapter(receiveAddressAdapter);
        recyAddress.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));

        mManager = new LinearLayoutManager(getActivity());
        receiveAddressAdapter = new ReceiveAddressAdapter(getActivity(),nearList,2);
        recyNearAddress.setHasFixedSize(false);
        recyNearAddress.setLayoutManager(mManager);
        recyNearAddress.setNestedScrollingEnabled(false);
        recyNearAddress.setAdapter(receiveAddressAdapter);
        recyNearAddress.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }


}
