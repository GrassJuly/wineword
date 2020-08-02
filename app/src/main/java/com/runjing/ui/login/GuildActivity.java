package com.runjing.ui.login;

import android.util.Log;

import com.runjing.MyApplication;
import com.runjing.base.TitleBarActivity;
import com.runjing.bean.request.BannerRequest;
import com.runjing.bean.response.guild.GuildBean;
import com.runjing.bean.response.guild.GuildImageBean;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.GlideUtils;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;
import com.socks.library.KLog;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * login 登录页面
 *
 * @author
 */

public class GuildActivity extends TitleBarActivity {

    @BindView(id = R.id.act_banner)
    private Banner banner;
    private GuildBean guildBean;

    @Override
    public void setRootView() {
        guildBean = new GuildBean();
        List<GuildImageBean> res = new ArrayList<>();
        GuildImageBean bean = new GuildImageBean();
        bean.setImg(R.mipmap.guild1);
        GuildImageBean bean1 = new GuildImageBean();
        bean1.setImg(R.mipmap.guild2);
        GuildImageBean bean2 = new GuildImageBean();
        bean2.setImg(R.mipmap.guild3);
        res.add(bean);
        res.add(bean1);
        res.add(bean2);
        guildBean.setImg(res);
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_guild);
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    public void initData() {
        super.initData();
        getData();
    }

    private void getData() {
        RetrofitClient.getInstance(this, RJBaseUrl.BaseUrl).execute(
                RetrofitClient.getInstance(this, RJBaseUrl.BaseUrl)
                        .create(ApiServices.class)
                        .getGuild(ApiServices.MyRequestBody.createBody(new BannerRequest())),
                new BaseSubscriber<GuildBean>(GuildActivity.this) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("Lyk", e.getMessage());
                        AppMethod.GuildBanner(GuildActivity.this, banner, guildBean.getImg(), 0);
                    }

                    @Override
                    public void onNext(GuildBean response) {
                        if (response.getImg() == null) {
                            AppMethod.GuildBanner(GuildActivity.this, banner, guildBean.getImg(), 0);
                        } else {
                            AppMethod.GuildBanner(GuildActivity.this, banner, response.getImg(), 1);
                        }
                    }
                });

    }
}
