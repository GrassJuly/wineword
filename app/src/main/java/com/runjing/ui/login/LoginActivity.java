package com.runjing.ui.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.runjing.MyApplication;
import com.runjing.base.BaseResponse;
import com.runjing.base.TitleBarActivity;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.LoginRequest;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.common.Appconfig;
import com.runjing.common.BaseUrl;
import com.runjing.http.MyRequestCallBack;
import com.runjing.http.OkHttpUtil;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

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
        ft =fm.beginTransaction();
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
        request.setCity(MMKVUtil.getInstance().decodeString(Appconfig.city));
        request.setLatitude(MMKVUtil.getInstance().decodeString(Appconfig.lat));
        request.setLongitude(MMKVUtil.getInstance().decodeString(Appconfig.lon));
        request.setPin(pin);
        request.setPlatform(4);//1 小程序  2 公众号 3 m站 4 Android 5 ios
        OkHttpUtil.postRequest(BaseUrl.LoginIn, request, LoginResponse.class, new MyRequestCallBack<LoginResponse>() {
            @Override
            public void onPostResponse(LoginResponse response) {
                KLog.d(response);
            }

            @Override
            public void onPostErrorResponse(Exception e, String msg) {

            }

            @Override
            public void onNoNetWork() {

            }
        });

//        String parames = JSON.toJSONString(request, SerializerFeature.DisableCircularReferenceDetect);
//
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), parames);
//
//        RetrofitClient.getInstance(this).createBaseApi().json("service/getIpInfo.php"
//                , body, new BaseSubscriber<com.runjing.http.net.BaseResponse>(this) {
//
//
//                    @Override
//                    public void onError(ExceptionHandle.ResponeThrowable e) {
//
//
//                        KLog.e( e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse responseBody) {
//
//                    }
//                });
    }
}
