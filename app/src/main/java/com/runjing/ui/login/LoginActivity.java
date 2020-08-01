package com.runjing.ui.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.runjing.MyApplication;
import com.runjing.base.TitleBarActivity;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.LoginRequest;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.common.Appconfig;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.location.LocalUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * login 登录页面
 *
 * @author
 */

public class LoginActivity extends TitleBarActivity implements OnLoginCallBack {

    @BindView(id = R.id.act_iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.act_ll_quick, click = true)
    private LinearLayout ll_quick;
    @BindView(id = R.id.act_v_quick)
    private View v_quick;
    @BindView(id = R.id.act_ll_ap, click = true)
    private LinearLayout ll_ap;
    @BindView(id = R.id.act_v_ap)
    private View v_ap;
    @BindView(id = R.id.act_fl_content)
    private FrameLayout fl_content;
    private TitleBarFragment fragment;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<TitleBarFragment> lists;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        KeyBoardUtil.init(this);
        lists = new ArrayList<>();
        lists.add(new QuickLoginFragment().setListener(this));
        lists.add(new APLoginFragment().setListener(this));
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        fragment = lists.get(0);
        ft.add(R.id.act_fl_content, fragment).commit();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.act_iv_back:
                finish();
                break;
            case R.id.act_ll_quick:
                v_quick.setVisibility(View.VISIBLE);
                v_ap.setVisibility(View.INVISIBLE);
                changePage(fragment, lists.get(0));
                break;
            case R.id.act_ll_ap:
                v_quick.setVisibility(View.INVISIBLE);
                v_ap.setVisibility(View.VISIBLE);
                changePage(fragment, lists.get(1));
                break;
        }
    }

    /**
     * 页面切换
     *
     * @param from
     * @param to
     */
    public void changePage(TitleBarFragment from, TitleBarFragment to) {
        if (fragment != to) {
            fragment = to;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.act_fl_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public void onLoginRJ(String pin) {
        LoginRequest request = new LoginRequest();
        request.setPhone(MMKVUtil.getInstance().decodeString(Appconfig.IsPhone));
        request.setCity(LocalUtil.city);
        request.setLatitude(LocalUtil.lat);
        request.setLongitude(LocalUtil.lon);
//        request.setPin("jd_7e8f4dfc127ea");
        request.setPin(pin);
        request.setPlatform(4);//1 小程序  2 公众号 3 m站 4 Android 5 ios
        RetrofitClient.getInstance(this, RJBaseUrl.BaseUrl).execute(
                RetrofitClient.getInstance(this, RJBaseUrl.BaseUrl)
                        .create(ApiServices.class)
                        .onLogin(ApiServices.MyRequestBody.createBody(request)),
                new BaseSubscriber<LoginResponse>(LoginActivity.this) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("Lyk", e.getMessage());

                    }

                    @Override
                    public void onNext(LoginResponse response) {
                        KLog.i(response.getData());
                        ViewInject.showCenterToast(LoginActivity.this, "登陆成功");
                        finish();
                    }
                });

    }


}
