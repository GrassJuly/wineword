package com.runjing.ui.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jd.verify.ShowCapCallback;
import com.jd.verify.Verify;
import com.jd.verify.model.IninVerifyInfo;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.web.WebBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.utils.JDLogin.UserUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;

import org.json.JSONObject;
import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.LoginFailProcessor;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnLoginCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.util.MD5;

import static com.runjing.common.Appconfig.FROMREGIST;

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
    @BindView(id = R.id.frag_ll_isagree, click = true)
    private LinearLayout ll_isagree;
    @BindView(id = R.id.frag_iv_isagree, click = true)
    private ImageView iv_isagree;
    @BindView(id = R.id.frag_tv_isagree)
    private TextView tv_isagree;
    @BindView(id = R.id.frag_tv_login, click = true)
    private TextView tv_login;
    private WJLoginHelper helper;
    private OnLoginCallBack listener;
    private Verify verify;
    private String sid;
    private String sUserName;
    private String sUserPassword;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_ap_login, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        helper = UserUtil.getWJLoginHelper();
        verify = Verify.getInstance();
        setAgreeClick(tv_isagree);
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
                    getForgtPwd();
                    break;
                case R.id.frag_ll_isagree:
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
                    onJDLogin();
                    break;
            }
        }
    }

    public void onJDLogin() {
        if (TextUtils.isEmpty(et_pwd.getText().toString())) {
            ViewInject.showCenterToast(outsideAty, "请填写密码");
            return;
        }
        sUserName = et_account.getText().toString().trim();
        sUserPassword = MD5.encrypt32(et_pwd.getText().toString());
        getSessionId();
    }

    private void getSessionId() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loginName", et_account.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.getCaptchaSid(4, jsonObject, new OnCommonCallback() {
            @Override
            public void onSuccess() {
                helper.JDLoginWithPasswordNew(sUserName, sUserPassword, "", "", onLoginCallback);
            }

            @Override
            public void onError(ErrorResult errorResult) {
            }

            @Override
            public void onFail(FailResult failResult) {
                sid = failResult.getStrVal();
                if (TextUtils.isEmpty(sid)) {
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                    return;
                }
                //第三个参数是uuid,app中有uuid 请填上
                verify.init(sid, outsideAty, "", sUserName, verifyCallback);
            }
        });
    }

    ShowCapCallback verifyCallback = new ShowCapCallback() {
        @Override
        public void showCap() {
            //弹出验证码时的回调，业务方若自行显示loading圈，在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。

        }

        @Override
        public void loadFail() {
            //加载失败或验证失败的回调，业务方若自行显示loading圈，在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。

        }

        @Override
        public void onSSLError() {
            //网络请求时ssl异常的回调，业务方若自行显示loading圈，在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。
            Log.d("verifyCallback", "onSSLError");
        }

        @Override
        public void showButton(int i) {
            //接入了嵌入式的验证方式，需要显示按钮的回调。现在支持的是点图的方式，所以正常接入不会执行到这个回调
            //业务方若自行显示loading圈，为保险起见在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。
            Log.d("verifyCallback", "showButton");
        }

        @Override
        public void invalidSessiongId() {
            //sid失效，需要重新获取
            Log.d("verifyCallback", "invalidSessiongId");
            getSessionId();
        }

        @Override
        public void onSuccess(IninVerifyInfo ininVerifyInfo) {
            //验证成功的回调
            helper.JDLoginWithPasswordNew(sUserName, sUserPassword, sid, ininVerifyInfo.getVt(), onLoginCallback);
        }

        @Override
        public void onFail(String s) {
            //嵌入式的（滑动验证码）验证失败回调，业务方若自行显示loading圈，在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。
            Log.d("verifyCallback", "onFail");
            //滑动验证码sdk已经提示，不需要做其他操作
        }
    };


    OnLoginCallback onLoginCallback = new OnLoginCallback(new LoginFailProcessor() {
        @Override
        public void onCommonHandler(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }


        @Override
        public void getBackPassword(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }


        @Override
        public void accountNotExist(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }

        @Override
        public void handle0x6a(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }

        @Override
        public void handle0x64(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }

        @Override
        public void handle0x8(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }

        @Override
        public void handleBetween0x7bAnd0x7e(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }

        @Override
        public void onSendMsgWithoutDialog(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }


        @Override
        public void handleBetween0x77And0x7a(FailResult failResult) {
            // 是否需要禁止用户操作，由客户端决定
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }


        @Override
        public void onSendMsg(FailResult failResult) {
            ViewInject.showCenterToast(outsideAty, failResult.getMessage());
        }
    }) {

        @Override
        protected void beforeHandleResult() {
        }

        @Override
        public void onSuccess() {
            MMKVUtil.getInstance().encode(Appconfig.JDPin, helper.getPin());
            MMKVUtil.getInstance().encode(Appconfig.IsPhone, sUserName);
            MMKVUtil.getInstance().encode(Appconfig.IsPWD, sUserPassword);
            if (listener != null) listener.onLoginRJ(helper.getPin());
        }

        @Override
        public void onError(ErrorResult error) {
            ViewInject.showCenterToast(outsideAty, error.toString());
        }
    };

    //忘记密码
    public void getForgtPwd() {
        String findPwdUrl = "https://plogin.m.jd.com/cgi-bin/m/mfindpwd";
        String userName = et_account.getText().toString().trim();
        String versionName = null;
        try {
            PackageInfo packageInfo = outsideAty.getPackageManager().getPackageInfo(outsideAty.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String formatUrl = String.format("%1$s?appid=%2$s&show_title=%3$s&client_type=%4$s&os_version=%5$s&app_client_ver=%6$s&uuid=%7$s&account=%8$s&returnurl=%9$s",
                findPwdUrl, UserUtil.APPID, "0", "android", Build.VERSION.RELEASE, versionName,
                "", userName, FROMREGIST);

        Intent intent = new Intent(outsideAty, WebActivity.class);
        intent.putExtra("url", formatUrl);
        intent.putExtra("isRegist", true);
        startActivity(intent);
    }

    /**
     * 设置不同颜色 点击效果
     * @param content
     */
    public void setAgreeClick(TextView content) {
        String str = getResources().getString(R.string.login_isagree);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(str);
        //第一个出现的位置
        final int start = str.indexOf("《");
        ssb.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                //用户服务协议点击事件
                Bundle bundle = new Bundle();
                bundle.putString("title", "用户协议");
                bundle.putInt("showType", 0);
                AppMethod.postShowWith(outsideAty, SimpleBackPage.Web, bundle);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文件颜色
                ds.setColor(getResources().getColor(R.color.color_5899E4));
                // 去掉下划线
                ds.setUnderlineText(false);
            }

        }, start, str.length(), 0);
        content.setHighlightColor(getResources().getColor(R.color.color_ffffff));
        content.setMovementMethod(LinkMovementMethod.getInstance());
        content.setText(ssb, TextView.BufferType.SPANNABLE);
    }


    public APLoginFragment setListener(OnLoginCallBack listener) {
        this.listener = listener;
        return this;
    }
}