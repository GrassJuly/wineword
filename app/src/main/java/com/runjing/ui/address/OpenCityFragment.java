package com.runjing.ui.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.runjing.ui.home.HomeAdapter;
import com.runjing.utils.LocalUtil;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.SpacesItemDecoration;
import com.runjing.widget.LoadingDialog;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;

/**
 * 已开通城市
 */
public class OpenCityFragment extends TitleBarFragment {
    @BindView(id = R.id.tv_xzcs_sq_address)
    private TextView tv_xzcs_sq_address;
    @BindView(id = R.id.lv_select_pirvoce)
    private RecyclerView rec_select_pirvoce;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        loadingDialog = new LoadingDialog(outsideAty);
        return inflater.inflate(R.layout.layout_select_vity, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.leftVisiable = 1;
        actionBarRes.titleLayoutVisible = 1;
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        tv_xzcs_sq_address.setText(LocalUtil.address);

    }
    /**
     * 设置数据
     *
     * @param homeBean
     */
    public void setData(HomeBean homeBean) {

    }

    private LoadingDialog loadingDialog;
    public void getData() {
//        loadingDialog.show();
//        HomeRequest homeRequest = new HomeRequest();
//        OkHttpUtil.postRequest(BaseUrl.AppMain, homeRequest, HomeResponse.class, new MyRequestCallBack<HomeResponse>() {
//            @Override
//            public void onPostResponse(HomeResponse response) {
//                loadingDialog.dismiss();
//
//            }
//
//            @Override
//            public void onPostErrorResponse(Exception e, String msg) {
//
//            }
//
//            @Override
//            public void onNoNetWork() {
//
//            }
//        });

        HomeData.getHomeData().setItemTpye(HomeBean.TYPE_ITEM_CITY);
        HomeAdapter homeAdapter = new HomeAdapter(getActivity());

        if (HomeData.getHomeData() != null) {
            if (HomeData.getHomeData().getItemTpye() == HomeBean.TYPE_ITEM_CITY) {
                rec_select_pirvoce.setLayoutManager(new LinearLayoutManager(outsideAty));
                rec_select_pirvoce.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                        getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
                rec_select_pirvoce.setHasFixedSize(false);
                rec_select_pirvoce.setNestedScrollingEnabled(false);
                rec_select_pirvoce.setAdapter(homeAdapter);
                homeAdapter.setData(HomeData.getHomeData());
            }
        }

    }
}
