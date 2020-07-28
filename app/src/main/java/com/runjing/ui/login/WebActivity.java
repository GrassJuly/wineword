package com.runjing.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.runjing.MainActivity;
import com.runjing.utils.JDLogin.UserUtil;
import com.runjing.utils.StatusBarUtil;
import com.runjing.wineworld.R;

import org.json.JSONObject;

import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.common.listener.OnDataCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.ReqJumpTokenResp;
import jd.wjlogin_sdk.util.ReplyCode;

public class WebActivity extends Activity {

    private WebView mWebView;
    private String mUrl;
    private boolean reqH5; //test 打通h5 接口 的标记，不需要打通的业务忽略
    /**
     * 登录路径
     */
    public static final String LOGIN_PATH = "/user/login.action";
    private static final String TAG = "WJLogin.WebActivity";
    //此scheme 是 绑定或风控 跳转到h5页面时拼接的succcb / returnUrl 里面的scheme;
    /* APP 可自行定义属于自己的app的,比如包含包名的一个关键字：如returnUrl定义为京东金融app可以定义为 jdlogin.jr.jdmobile://communication 。
     * APP自行新增的returnUrl 需要后台配置，才会在验证成功后跳转到这个对应的returnUrl
     * 也可以直接使用demo中的这个jdlogin.safecheck.jdmobile ，已配置 可通用
     * 格式必须是 scheme://host 这种格式，字母都要小写
     */
    public static final String SCHEME = "jdlogin.safecheck.jdmobile"; //  通用的风控 scheme
    public static final String RETURN_URL = "jdlogin.safecheck.jdmobile://communication";// 通用的风控  returnUrl ,这个returnUrl里面的scheme 要在webview里面监听

    private ResumeListener resumeListener;

    private int type = 0; //表示国内版登录跳转过来还是国际版，国际版的如果风控的验证码成功后跳转到主页面后点击退出登录调用国际版的退出方法

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(this);
        setContentView(R.layout.activity_webview);
        initDataAndView();
        initWebSetting();

    }

    private void initDataAndView() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        reqH5 = intent.getBooleanExtra("reqH5", false);
        type = getIntent().getIntExtra("type", 0); //默认国内版
        mWebView = (WebView) findViewById(R.id.mywebview);
    }

    private void initWebSetting() {

        // 允许js
        WebSettings wSet = mWebView.getSettings();
        wSet.setJavaScriptEnabled(true);
        if (reqH5) {
            //走打通逻辑
            reqH5JumpToken(mUrl);

        } else {
            mWebView.loadUrl(mUrl);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边

                Log.e(TAG, "ertterre url = " + url);
                try {
                    if (reqH5) {
                        //这里是打通里面，监听饭回来的url 是否需要跳转到登录框
                        checkUrl(view, url);
                    } else {
                        Uri uri = Uri.parse(url);
                        String scheme = uri.getScheme();
                        Log.e(TAG, "scheme = " + scheme);
                        if (SCHEME.equals(scheme)) {  //此scheme为，跳转到绑定或风控时拼接的returnUrl
                            String des = uri.getQuery();
                            if (!TextUtils.isEmpty(des)) {
                                if (des.contains("\"typelogin_in\":\"wjlogin\"") || ("wjlogin".equals(uri.getQueryParameter("typelogin_in")))) {
                                    String status = uri.getQueryParameter("status");
                                    String token = null;
                                    String safeToken = uri.getQueryParameter("safe_token");
                                    if (status.equals("true")) {
                                        token = uri.getQueryParameter("token");
                                    }
                                    if (!TextUtils.isEmpty(token)) {
                                        bindLogin(token);
                                    } else if (!TextUtils.isEmpty(safeToken)) {
                                        smsVerifyLogin(safeToken);
                                    } else {
                                        Toast.makeText(WebActivity.this, "关联帐号失败",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }


                        } else {
                            view.loadUrl(url);
                        }
                    }


                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();

                    Toast.makeText(WebActivity.this, "关联帐号失败",
                            Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 微信或者QQ授权登陆
     *
     * @param token
     */
    private void bindLogin(String token) {
        UserUtil.getWJLoginHelper().bindAccountLogin(token, new OnCommonCallback() {
            @Override
            public void onSuccess() {
                toMainActivity();
            }

            @Override
            public void onError(ErrorResult errorResult) {
                String tip = "矮油，程序出错了！";
                if (null != errorResult.getErrorMsg()) {
                    tip = errorResult.getErrorMsg();
                }
                Toast.makeText(WebActivity.this, tip, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(FailResult failResult) {
                Toast.makeText(WebActivity.this, failResult.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 上下行短信验证
     *
     * @param token
     */
    private void smsVerifyLogin(String token) {
        if (1 == type) {
            //国际版调用的方法
            UserUtil.getWJLoginHelper().globalBindAccountLogin(token, new OnCommonCallback() {
                @Override
                public void onSuccess() {
                    toMainActivity();
                }

                @Override
                public void onError(ErrorResult errorResult) {
                    String tip = "矮油，程序出错了！";
                    if (null != errorResult.getErrorMsg()) {
                        tip = errorResult.getErrorMsg();
                    }
                    Toast.makeText(WebActivity.this, tip, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFail(FailResult failResult) {
                    Toast.makeText(WebActivity.this, failResult.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
        //国内版调用的方法
        UserUtil.getWJLoginHelper().h5BackToApp(token, new OnCommonCallback() {
            @Override
            public void onSuccess() {
                toMainActivity();
            }

            @Override
            public void onError(ErrorResult errorResult) {
                String tip = "矮油，程序出错了！";
                if (null != errorResult.getErrorMsg()) {
                    tip = errorResult.getErrorMsg();
                }
                Toast.makeText(WebActivity.this, tip, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(FailResult failResult) {
                Toast.makeText(WebActivity.this, failResult.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void toMainActivity() {
        Intent intent = new Intent(WebActivity.this, MainActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }

    //打通h5
    private boolean checkUrl(WebView view, String url) {
        Uri uri = Uri.parse(url);
        Log.d(TAG, "start checkUrl :" + uri);
        if (uri == null) {
            return false;
        }
        //当reqJumpToken 接口返回的url  path = /user/login.action 时，需要去登录，要跳到登陆框
        if (LOGIN_PATH.equals(uri.getPath())) {
            //跳转登录，业务app中，需要在登录成功回来后，重新走一次打通并load url
            toLogin(uri.getQueryParameter("returnurl"));
            return true;
        }
        view.loadUrl(url);
        return true;
    }

    private void reqH5JumpToken(final String url) {
        //拼接接口调用的参数 url
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("action", "to");
            jsonObject.put("to", url); // url: 要打开的目的url
            jsonObject.put("app", "WJLoginAndroidDemo"); //app 名字，与登录sdk 初始化时传递的ClientInfo的AppName一致
        } catch (Exception e) {

        }
        String jumpUrl = jsonObject.toString();
        Log.d(TAG, "reqJumpToken jumpURl = " + jumpUrl);
        //开始调用登录sdk里面打通h5的接口
        UserUtil.getWJLoginHelper().reqJumpToken(jumpUrl, new OnDataCallback<ReqJumpTokenResp>() {
            @Override
            public void onSuccess(ReqJumpTokenResp reqJumpTokenResp) {
                //拼接新的url
                String url0 = reqJumpTokenResp.getUrl();
                String token = reqJumpTokenResp.getToken();
                StringBuilder builder = new StringBuilder();
                builder.append(url0).append("?wjmpkey=").append(token).append("&to=").append(Uri.encode(url));
                Log.d(TAG, "reqJumpToken newURl = " + builder.toString());
                mWebView.loadUrl(builder.toString());
            }

            @Override
            public void onError(ErrorResult errorResult) {
                Log.d(TAG, "reqJumpToken onError = " + errorResult.getErrorMsg());
                Toast.makeText(WebActivity.this, errorResult.getErrorMsg(), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFail(FailResult failResult) {
                Log.d(TAG, "reqJumpToken onFail = " + failResult.getMessage() + failResult.getReplyCode());
                if (ReplyCode.reply0xb == failResult.getReplyCode() ||
                        ReplyCode.reply0xc == failResult.getReplyCode() ||
                        ReplyCode.reply0xd == failResult.getReplyCode() ||
                        ReplyCode.reply0xe == failResult.getReplyCode() ||
                        ReplyCode.reply0xa5 == failResult.getReplyCode() ||
                        ReplyCode.reply0xa6 == failResult.getReplyCode()) {
                    //登陆态失效或过期 或退出登录,本地app可以清除登陆态
                    UserUtil.getWJLoginHelper().clearLocalOnlineState();
                    toLogin(url);
                }

            }
        });

    }

    private void toLogin(final String url) {
        //跳转登录，业务app中，需要在登录成功回来后，重新走一次打通并load url
        resumeListener = new ResumeListener() {
            @Override
            public void onResume() {
                reqH5JumpToken(url);
                resumeListener = null;
            }
        };
        finish();
    }


    public interface ResumeListener {
        void onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != resumeListener) {
            resumeListener.onResume();
        }
    }

}
