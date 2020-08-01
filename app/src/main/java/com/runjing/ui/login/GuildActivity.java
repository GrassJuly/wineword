package com.runjing.ui.login;

import com.runjing.MyApplication;
import com.runjing.base.BaseRequest;
import com.runjing.base.TitleBarActivity;
import com.runjing.bean.response.login.GuildBean;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.common.RJBaseUrl;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;

/**
 * login 登录页面
 *
 * @author
 */

public class GuildActivity extends TitleBarActivity {

    @BindView(id = R.id.act_banner)
    private Banner banner;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_guild);
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    public void initData() {
        super.initData();
        AppMethod.GuildBanner(GuildActivity.this, banner, HomeData.getBanner());
//        getData();
    }

//    private void getData() {
//        BaseRequest loginRequest = new BaseRequest();
//        OkHttpUtil.postRequest(RJBaseUrl.GetBanner, loginRequest, GuildBean.class, new MyRequestCallBack<GuildBean>() {
//            @Override
//            public void onPostResponse(GuildBean response) {
//                if (response != null) {
//                    AppMethod.GuildBanner(GuildActivity.this, banner, response.getData());
//                }
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
//
//    }
}
