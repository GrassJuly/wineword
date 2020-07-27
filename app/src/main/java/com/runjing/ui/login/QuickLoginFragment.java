package com.runjing.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jd.verify.ShowCapCallback;
import com.jd.verify.Verify;
import com.jd.verify.model.IninVerifyInfo;
import com.runjing.base.BaseResponse;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.HomeRequest;
import com.runjing.common.AppMethod;
import com.runjing.common.BaseUrl;
import com.runjing.http.MyRequestCallBack;
import com.runjing.http.OkHttpUtil;
import com.runjing.utils.JDLogin.UserUtil;
import com.runjing.utils.TimerCount;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import jd.wjlogin_sdk.common.WJLoginHelper;

import static com.runjing.utils.JDLogin.UserUtil.session_id;
import static com.runjing.utils.JDLogin.UserUtil.udid;

/**
 * @Created: qianxs  on 2020.07.20 19:37.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.20 19:37.
 * @Remark:
 */
public class QuickLoginFragment extends TitleBarFragment {
    private static final String TAG = QuickLoginFragment.class.getName();

    @BindView(id = R.id.frag_et_phone)
    private EditText et_phone;
    @BindView(id = R.id.frag_iv_del, click = true)
    private ImageView iv_del;
    @BindView(id = R.id.frag_et_code)
    private EditText et_code;
    @BindView(id = R.id.frag_tv_sendcode, click = true)
    private TextView tv_sendcode;
    @BindView(id = R.id.frag_iv_isagree, click = true)
    private ImageView iv_isagree;
    @BindView(id = R.id.frag_tv_isagree)
    private TextView tv_isagree;
    @BindView(id = R.id.frag_tv_login, click = true)
    private Button tv_login;
    private TimerCount timer;
    private WJLoginHelper helper;
    private Verify verify;
    private String countryCode = "86";


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_quick_login, null);
    }

    @Override
    protected void initData() {
        super.initData();
        helper = UserUtil.getWJLoginHelper();
        verify = Verify.getInstance();
        verify.setLoading(true);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        tv_isagree.setText(AppMethod.setDiffCollors(
                "{" + getResources().getString(R.string.login_isagree_next) + "}" +
                        getResources().getString(R.string.login_isagree_last),
                getResources().getColor(R.color.color_666666),
                getResources().getColor(R.color.color_5899E4)));
        //系统提供的定时类有误差 + 20
        timer = new TimerCount(59000 + 20, 1000, tv_sendcode);
        tv_sendcode.setClickable(false);
        tv_login.setClickable(false);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    iv_del.setVisibility(View.GONE);
                    tv_login.setClickable(false);
                    tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                } else {
                    iv_del.setVisibility(View.VISIBLE);
                    if (s.length() == 11) {
                        tv_sendcode.setClickable(true);
                        tv_sendcode.setTextColor(getResources().getColor(R.color.color_F80000));
                    } else {
                        tv_sendcode.setClickable(false);
                        tv_sendcode.setTextColor(getResources().getColor(R.color.color_999999));
                    }

                    if (!TextUtils.isEmpty(et_code.getText().toString())
                            && getResources().getString(R.string.tag_yes).equals(iv_isagree.getTag())) {
                        tv_login.setClickable(true);
                        tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_f80000));
                    } else {
                        tv_login.setClickable(false);
                        tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    tv_login.setClickable(false);
                    tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                } else {
                    if (!TextUtils.isEmpty(et_phone.getText().toString())
                            && getResources().getString(R.string.tag_yes).equals(iv_isagree.getTag())) {
                        tv_login.setClickable(true);
                        tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_f80000));
                    } else {
                        tv_login.setClickable(false);
                        tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.frag_iv_del:
                    et_phone.setText("");
                    break;
                case R.id.frag_iv_isagree:
                    if (getResources().getString(R.string.tag_no).equals(v.getTag())) {
                        iv_isagree.setTag(getResources().getString(R.string.tag_yes));
                        iv_isagree.setImageResource(R.mipmap.icon_select);
                        if (!TextUtils.isEmpty(et_phone.getText().toString()) && !TextUtils.isEmpty(et_code.getText().toString())) {
                            tv_login.setClickable(true);
                            tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_f80000));
                        }
                    } else {
                        iv_isagree.setTag(getResources().getString(R.string.tag_no));
                        iv_isagree.setImageResource(R.mipmap.icon_no_select);
                        if (!TextUtils.isEmpty(et_phone.getText().toString()) && !TextUtils.isEmpty(et_code.getText().toString())) {
                            tv_login.setClickable(false);
                            tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                        }
                    }
                    break;
                case R.id.frag_tv_sendcode:
                    getCode();
                    timer.start();
                    break;
                case R.id.frag_tv_login:
                    if (getResources().getString(R.string.tag_yes).equals(iv_isagree.getTag())) {
                        onSubmit();
                    }
                    break;
            }
        }
    }


    public void getJDCode() {
        String phoneNum = et_phone.getText().toString();
        if ("86".equals(countryCode) && (!phoneNum.startsWith("1") || phoneNum.length() < 11 || phoneNum.length() > 12 || !AppMethod.isNumber(phoneNum))) {
            ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
            return;
        }
        /**
         * 中国手机号位数11位（号段限制保留）；
         * 香港、澳门、台湾是6-10位；852,853,886
         */
        if (!AppMethod.isNumber(phoneNum)) {
            ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
            return;
        } else if ((TextUtils.equals("852", countryCode) || TextUtils.equals("853", countryCode) || TextUtils.equals("886", countryCode))) {
            if (phoneNum.length() < 6 || phoneNum.length() > 10) {
                ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
                return;
            }
        }
        verify.init(session_id, outsideAty, udid, phoneNum, new ShowCapCallback() {
            @Override
            public void showCap() {

            }

            @Override
            public void loadFail() {

            }

            @Override
            public void onSSLError() {

            }

            @Override
            public void showButton(int i) {

            }

            @Override
            public void invalidSessiongId() {

            }

            @Override
            public void onSuccess(IninVerifyInfo ininVerifyInfo) {

            }

            @Override
            public void onFail(String s) {

            }
        });
    }


    public void getCode() {
        HomeRequest homeRequest = new HomeRequest();
        OkHttpUtil.postRequest(BaseUrl.AppMain, homeRequest, BaseResponse.class, new MyRequestCallBack<BaseResponse>() {
            @Override
            public void onPostResponse(BaseResponse response) {

            }

            @Override
            public void onPostErrorResponse(Exception e, String msg) {

            }

            @Override
            public void onNoNetWork() {

            }
        });
    }

    public void onSubmit() {
        if (TextUtils.isEmpty(et_phone.getText())) {
            ViewInject.showCenterToast(getActivity(), "请填写手机号");
            return;
        }
        if (AppMethod.isMobileNO(et_phone.getText().toString())) {
            ViewInject.showCenterToast(outsideAty, "请填写正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(et_code.getText().toString())) {
            ViewInject.showCenterToast(outsideAty, "请填写验证码");
            return;
        }

        HomeRequest homeRequest = new HomeRequest();
        OkHttpUtil.postRequest(BaseUrl.AppMain, homeRequest, BaseResponse.class, new MyRequestCallBack<BaseResponse>() {
            @Override
            public void onPostResponse(BaseResponse response) {
                onLogin();
            }

            @Override
            public void onPostErrorResponse(Exception e, String msg) {

            }

            @Override
            public void onNoNetWork() {

            }
        });
    }

    public void onLogin() {
        HomeRequest homeRequest = new HomeRequest();
        OkHttpUtil.postRequest(BaseUrl.LoginIn, homeRequest, BaseResponse.class, new MyRequestCallBack<BaseResponse>() {
            @Override
            public void onPostResponse(BaseResponse response) {

            }

            @Override
            public void onPostErrorResponse(Exception e, String msg) {

            }

            @Override
            public void onNoNetWork() {

            }
        });
    }
}
