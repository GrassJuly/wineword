package com.runjing.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jd.verify.ShowCapCallback;
import com.jd.verify.Verify;
import com.jd.verify.model.IninVerifyInfo;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.utils.JDLogin.UserUtil;
import com.runjing.utils.PopupWindowUtil;
import com.runjing.utils.TimerCount;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.utils.time.CountDownTimerUtil;
import com.runjing.widget.pop.LoginReceivePopupWindow;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.json.JSONObject;
import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import jd.wjlogin_sdk.common.DevelopType;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.common.listener.OnLoginCallback;
import jd.wjlogin_sdk.common.listener.PhoneLoginFailProcessor;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.SuccessResult;
import jd.wjlogin_sdk.util.ReplyCode;

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
    @BindView(id = R.id.frag_ll_isagree, click = true)
    private LinearLayout ll_isagree;
    @BindView(id = R.id.frag_iv_isagree, click = true)
    private ImageView iv_isagree;
    @BindView(id = R.id.frag_tv_isagree)
    private TextView tv_isagree;
    @BindView(id = R.id.frag_tv_login, click = true)
    private Button tv_login;
    //    private TimerCount timer;
    private CountDownTimerUtil timer;
    private WJLoginHelper helper;
    private Verify verify;
    private String countryCode = "86";
    private String phoneNum;
    private String sid;
    private OnLoginCallBack listener;

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
        setAgreeClick(tv_isagree);
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
                case R.id.frag_ll_isagree:
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
                    getJDCode();
                    break;
                case R.id.frag_tv_login:
                    if (getResources().getString(R.string.tag_yes).equals(iv_isagree.getTag())) {
                        onLoginJD();
                    }
//                    listener.onLoginRJ(MMKVUtil.getInstance().decodeString(Appconfig.JDPin));
                    break;
            }
        }
    }

    /*对接京东登陆这块*/
    public void getJDCode() {
        phoneNum = et_phone.getText().toString();
        if ("86".equals(countryCode) && (!phoneNum.startsWith("1") || phoneNum.length() < 11 || phoneNum.length() > 12 || !AppMethod.isNumber(phoneNum))) {
            ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
            return;
        }
        /**
         * 中国手机号位数11位（号段限制保留）；
         * 香港、澳门、台湾是6-10位；852,853,886
         */
        if (!AppMethod.isMobileNO(phoneNum)) {
            ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
            return;
        } else if ((TextUtils.equals("852", countryCode) || TextUtils.equals("853", countryCode) || TextUtils.equals("886", countryCode))) {
            if (phoneNum.length() < 6 || phoneNum.length() > 10) {
                ViewInject.showCenterToast(getActivity(), "手机号码格式错误");
                return;
            }
        }
        getSessionId();
    }

    private void getSessionId() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("countryCode", countryCode);
            jsonObject.put("phone", phoneNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.setDevelop(DevelopType.PRODUCT);
        helper.getCaptchaSid(3, jsonObject, new OnCommonCallback() {
            @Override
            public void onSuccess() {
                getMsgCode(phoneNum, "", "");
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
                verify.init(sid, outsideAty, "", phoneNum, verifyCallback);
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
            Log.d(TAG, " verifyCallback onSSLError:");

        }

        @Override
        public void showButton(int i) {
            //接入了嵌入式的验证方式，需要显示按钮的回调。现在支持的是点图的方式，所以正常接入不会执行到这个回调
            //业务方若自行显示loading圈，为保险起见在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。
            Log.d(TAG, " verifyCallback showButton:" + i);

        }

        @Override
        public void invalidSessiongId() {
            Log.d(TAG, "init verifyCallback invalidSessiongId countryCode = " + countryCode);
            getSessionId();
        }

        @Override
        public void onSuccess(IninVerifyInfo ininVerifyInfo) {
            Log.d(TAG, "init verifyCallback onSuccess countryCode = " + countryCode);
            getMsgCode(phoneNum, sid, ininVerifyInfo.getVt());
        }

        @Override
        public void onFail(String s) {
            //嵌入式的（滑动验证码）验证失败回调，业务方若自行显示loading圈，在这个回调要将loading圈消失掉。若使用验证码sdk提供的loading则不需处理。
            Log.d(TAG, " verifyCallback onFail:" + s);

        }
    };


    /**
     * 获取验证码
     *
     * @param phoneNum
     * @param sid
     * @param token
     */
    private void getMsgCode(final String phoneNum, final String sid, final String token) {
        helper.sendMsgCodeForPhoneNumLogin4JD(phoneNum, countryCode, sid, token, new OnDataCallback<SuccessResult>() {

            @Override
            public void beforeHandleResult() {

            }

            @Override
            public void onSuccess(SuccessResult successResult) {
                System.out.println("onSuccess？？？  " + successResult);
                int msgCodeExpireTime = successResult.getIntVal();
                //显示倒计时
//                timer.setCountdownInterval(msgCodeExpireTime * 1000 + 20);
//                timer.setMillisInFuture(1000);
                timer.start();
            }

            @Override
            public void onError(ErrorResult error) {
                ViewInject.showCenterToast(outsideAty, "" + error);
            }

            @Override
            public void onFail(FailResult failResult) {
                if (failResult.getReplyCode() == ReplyCode.reply0x17) {
                    // 提交过于频繁。是否需要禁止用户操作，由客户端决定
                    //刷新显示到计时failResult.getDwLimitTimet
                    int time = failResult.getIntVal();
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                } else if (failResult.getReplyCode() == ReplyCode.reply0x1f) {
                    //短信已发送，请勿重复提交。 是否需要禁止用户操作，由客户端决定
                    //刷新显示到计时failResult.getDwLimitTimet
//                    timer.start();
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                } else {
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                }

            }

        });

    }

    public void onLoginJD() {
        if (TextUtils.isEmpty(phoneNum) || !phoneNum.startsWith("1") || phoneNum.length() < 11 || phoneNum.length() > 12) {
            ViewInject.showCenterToast(outsideAty, "手机号码格式错误");
            return;
        } else if (TextUtils.isEmpty(et_code.getText().toString())) {
            ViewInject.showCenterToast(outsideAty, "短信验证码不能为空");
            return;
        }
        helper.checkMsgCodeForPhoneNumLogin4JD(phoneNum, et_code.getText().toString(), countryCode, new OnLoginCallback(new PhoneLoginFailProcessor() {
            @Override
            public void handle0xb4(FailResult failResult) {
                //验证频繁，弹框显示 可以跳转到密码登录
                KLog.d(TAG, "getMsg handle0xb4 message" + failResult.getMessage() + "  code=" + failResult.getReplyCode());
            }

            @Override
            public void handle0x73(FailResult failResult) {
                //跳转到历史收货人页面
                PopupWindowUtil.showPopReceive(outsideAty, "", "", new LoginReceivePopupWindow.PopupWindowCallBack() {
                    @Override
                    public void onNegativeButtonClick() {

                    }

                    @Override
                    public void onPositiveButtonClick(String msg) {
                        checkReceive(msg);
                    }
                });
            }

            @Override
            public void onCommonHandler(FailResult failResult) {
                //返回0x31，可以去设置密码
                if (ReplyCode.reply0x31 == failResult.getReplyCode()) {
                    //设置密码界面
                    Bundle bundle = new Bundle();
                    bundle.putString(Appconfig.DATA_KEY, phoneNum);
                    AppMethod.postShowWith(outsideAty, SimpleBackPage.SetPwd, bundle);
                } else {
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                }
            }

            @Override
            public void accountNotExist(FailResult failResult) {
                //立即去注册
                KLog.d(TAG, "getMsg accountNotExist message" + failResult.getMessage() + "  code=" + failResult.getReplyCode());
            }

            @Override
            public void handleBetween0x7bAnd0x7e(FailResult failResult) {
                KLog.d(TAG, "getMessageCode handleBetween0x7bAnd0x7e Message");
            }

            @Override
            public void handleBetween0x77And0x7a(FailResult failResult) {
                Log.d(TAG, "getMessageCode handleBetween0x77And0x7a Message");

                // 是否需要禁止用户操作，由客户端决定
                if (TextUtils.isEmpty(failResult.getJumpResult().getUrl())) {
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                    return;
                }
            }

            @Override
            public void onSendMsg(FailResult failResult) {
                KLog.d(TAG, "getMessageCode onSendMsg Message" + failResult.getMessage());

            }

            @Override
            public void onSendMsgWithoutDialog(FailResult failResult) {
                KLog.d(TAG, "getMessageCode onSendMsgWithoutDialog Message");

            }
        }) {

            @Override
            public void beforeHandleResult() {
            }


            @Override
            public void onSuccess() {
                //检查并登录成功，此时跳回到登录上一个页面。
                MMKVUtil.getInstance().encode(Appconfig.JDPin, helper.getPin());
                MMKVUtil.getInstance().encode(Appconfig.IsPhone, phoneNum);
                KLog.d("JDPin -------  ", MMKVUtil.getInstance().decodeString(Appconfig.JDPin));
                if (listener != null) listener.onLoginRJ(helper.getPin());
            }

            @Override
            public void onError(ErrorResult error) {
                ViewInject.showCenterToast(outsideAty, "" + error);
            }

            @Override
            public void onFail(FailResult failResult) {
                //如果返回需要验证历史收货人
                if (null != failResult && ReplyCode.reply0x73 == failResult.getReplyCode()) {
                    //跳转到历史收货人页面
                    PopupWindowUtil.showPopReceive(outsideAty, "", "", new LoginReceivePopupWindow.PopupWindowCallBack() {
                        @Override
                        public void onNegativeButtonClick() {

                        }

                        @Override
                        public void onPositiveButtonClick(String msg) {
                            checkReceive(msg);
                        }
                    });
                } else {
                    ViewInject.showCenterToast(outsideAty, failResult.getMessage());
                }
            }

        });
    }

    /**
     * 验证手机收货人
     *
     * @param name
     */
    public void checkReceive(String name) {
        helper.checkHistory4JDPhoneNumLoginNew(phoneNum, "", name, new OnCommonCallback() {
            @Override
            public void onSuccess() {
                MMKVUtil.getInstance().encode(Appconfig.JDPin, helper.getPin());
                MMKVUtil.getInstance().encode(Appconfig.IsPhone, phoneNum);
                ViewInject.showCenterToast(outsideAty, "验证成功");
                finish();
            }

            @Override
            public void onError(ErrorResult errorResult) {

            }

            @Override
            public void onFail(FailResult failResult) {
                ViewInject.showCenterToast(outsideAty, failResult.getMessage());
            }
        });
    }

    /**
     * 设置不同颜色
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        verify.free();
    }

    public QuickLoginFragment setListener(OnLoginCallBack listener) {
        this.listener = listener;
        return this;
    }
}
