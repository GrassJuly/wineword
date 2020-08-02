package com.runjing.ui.address;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.BaseRequest;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.def.HomeAdapter;
import com.runjing.bean.response.home.def.HomeBean;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.bean.test.HomeData;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.ui.login.LoginActivity;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.widget.LoadingDialog;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.List;

/**
 * 已开通城市
 */
public class OpenCityFragment extends TitleBarFragment {
    @BindView(id = R.id.tv_xzcs_sq_address)
    private TextView tv_xzcs_sq_address;
    @BindView(id = R.id.lv_select_pirvoce)
    private RecyclerView rec_select_pirvoce;
    @BindView(id = R.id.img_select_search_back,click = true)
    private ImageView img_select_search_back;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        loadingDialog = new LoadingDialog(outsideAty);
        return inflater.inflate(R.layout.layout_select_vity, null);
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

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.img_select_search_back:
                finish();
                break;
        }
    }

    private LoadingDialog loadingDialog;
    private LinearLayoutManager manager;
    public void getData() {
        RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl).execute(
                RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl)
                        .create(ApiServices.class)
                        .getDistrict(ApiServices.MyRequestBody.createBody(new BaseRequest())),
                new BaseSubscriber<DistrictBean>(outsideAty) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("Lyk", e.getMessage());

                    }

                    @Override
                    public void onNext(DistrictBean response) {

                        if (response != null) {
                            List<DistrictBean.DataBean> mList = response.getData();
                            OpenCityAdapter homeAdapter = new OpenCityAdapter(outsideAty,mList);
                           if(manager==null) {
                               manager = new LinearLayoutManager(outsideAty);
                               rec_select_pirvoce.setLayoutManager(manager);
                               rec_select_pirvoce.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                                       getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
                               rec_select_pirvoce.setHasFixedSize(false);
                               rec_select_pirvoce.setNestedScrollingEnabled(false);
                           }
                                rec_select_pirvoce.setAdapter(homeAdapter);


                        }
                    }
                });

    }

}
