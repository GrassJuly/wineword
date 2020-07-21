package com.runjing.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.runjing.base.BaseResponse;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.request.HomeRequest;
import com.runjing.bean.response.WebBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.common.BaseUrl;
import com.runjing.http.MyRequestCallBack;
import com.runjing.http.OkHttpUtil;
import com.runjing.utils.TimerCount;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

/**
 * @Created: qianxs  on 2020.07.20 19:37.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.20 19:37.
 * @Remark:
 */
public class APLoginFragment extends TitleBarFragment {

    @BindView(id = R.id.frag_et_account)
    private EditText et_account;
    @BindView(id = R.id.frag_iv_accountdel, click = true)
    private ImageView iv_accountdel;
    @BindView(id = R.id.frag_et_pwd)
    private EditText et_pwd;
    @BindView(id = R.id.frag_iv_pwddel, click = true)
    private ImageView iv_pwddel;
    @BindView(id = R.id.frag_iv_pwdsee, click = true)
    private ImageView iv_pwdsee;
    @BindView(id = R.id.frag_tv_forget, click = true)
    private TextView tv_forget;
    @BindView(id = R.id.frag_iv_isagree, click = true)
    private ImageView iv_isagree;
    @BindView(id = R.id.frag_tv_isagree)
    private TextView tv_isagree;
    @BindView(id = R.id.frag_tv_login, click = true)
    private TextView tv_login;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_ap_login, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        tv_isagree.setText(AppMethod.setDiffCollors(
                "{" + getResources().getString(R.string.login_isagree_next) + "}" +
                        getResources().getString(R.string.login_isagree_last),
                getResources().getColor(R.color.color_666666),
                getResources().getColor(R.color.color_5899E4)));
        tv_login.setClickable(false);
        et_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    iv_accountdel.setVisibility(View.GONE);
                    tv_login.setClickable(false);
                    tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                } else {
                    iv_accountdel.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(et_pwd.getText().toString())
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
        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    iv_pwddel.setVisibility(View.GONE);
                    tv_login.setClickable(false);
                    tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                } else {
                    iv_pwddel.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(et_account.getText().toString())
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
        //系统提供的定时类有误差 + 20
        tv_login.setClickable(false);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.frag_iv_accountdel:
                    et_account.setText("");
                    break;
                case R.id.frag_iv_pwddel:
                    et_pwd.setText("");
                    break;
                case R.id.frag_iv_pwdsee:
                    if (getResources().getString(R.string.tag_no).equals(v.getTag())) {
                        iv_pwdsee.setTag(getResources().getString(R.string.tag_yes));
                        iv_pwdsee.setImageResource(R.mipmap.icon_see);
                        et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        iv_pwdsee.setTag(getResources().getString(R.string.tag_no));
                        iv_pwdsee.setImageResource(R.mipmap.icon_nosee);
                        et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    break;
                case R.id.frag_tv_forget:
                    Bundle bundle = new Bundle();
                    WebBean webBean = new WebBean();
                    webBean.setUrl("");
                    bundle.putSerializable(Appconfig.DATA_KEY, webBean);
                    AppMethod.postShowWith(getActivity(), SimpleBackPage.Web, bundle);
                    break;
                case R.id.frag_iv_isagree:
                    if (getResources().getString(R.string.tag_no).equals(v.getTag())) {
                        iv_isagree.setTag(getResources().getString(R.string.tag_yes));
                        iv_isagree.setImageResource(R.mipmap.icon_select);
                        if (!TextUtils.isEmpty(et_account.getText().toString()) && !TextUtils.isEmpty(et_pwd.getText().toString())) {
                            tv_login.setClickable(true);
                            tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_f80000));
                        }
                    } else {
                        iv_isagree.setTag(getResources().getString(R.string.tag_no));
                        iv_isagree.setImageResource(R.mipmap.icon_no_select);
                        if (!TextUtils.isEmpty(et_account.getText().toString()) && !TextUtils.isEmpty(et_pwd.getText().toString())) {
                            tv_login.setClickable(false);
                            tv_login.setBackground(getResources().getDrawable(R.drawable.style_radius_bg_4df80000));
                        }
                    }
                    break;
                case R.id.frag_tv_login:
                    onSubmit();
                    break;
            }
        }
    }

    public void onSubmit() {
//        if (TextUtils.isEmpty(et_account.getText())) {
//            ViewInject.showCenterToast(getActivity(), "请填写手机号");
//            return;
//        }
//        if (et_account.getText().toString().contains("@")){
//
//        } else {
//
//        }
//        if (AppMethod.isMobileNO(et_phone.getText().toString())) {
//            ViewInject.showCenterToast(outsideAty, "请填写正确的手机号");
//            return;
//        }
//        if (TextUtils.isEmpty(et_pwd.getText().toString())) {
//            ViewInject.showCenterToast(outsideAty, "请填写密码");
//            return;
//        }

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