package com.runjing;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.runjing.base.TitleBarActivity;
import com.runjing.bean.request.LoginRequest;
import com.runjing.bean.response.login.LoginResponse;
import com.runjing.common.AppMethod;
import com.runjing.common.BaseUrl;
import com.runjing.http.MyRequestCallBack;
import com.runjing.http.OkHttpUtil;
import com.runjing.utils.PopupWindowUtil;
import com.runjing.widget.LoadingDialog;
import com.runjing.widget.MiddlePopupWindow;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.StringUtils;
import org.runjing.rjframe.utils.SystemTool;

/**
 * login 登录页面
 *
 * @author
 */

public class LoginActivity extends TitleBarActivity {

    /*定时器*/
    private Handler handler = new Handler();
    @BindView(id = R.id.act_et_phone)
    private EditText et_login;
    @BindView(id = R.id.act_et_pwd)
    private EditText et_pwd;
    @BindView(id = R.id.act_btn_login, click = true)
    private Button btn_login;
    @BindView(id = R.id.act_tv_version)
    private TextView tv_version;
    LoadingDialog loadDialog;
    @BindView(id = R.id.tv_forget_pwd, click = true)
    private TextView tv_forget_pwd;

    @Override
    public void setRootView() {
        MyApplication.contextApp.addActivity(this);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        loadDialog = new LoadingDialog(this);
        String versionName = "v" + SystemTool.getAppVersionName(this);
        tv_version.setText(versionName);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.act_btn_login:
                String name = et_login.getText().toString();
                String pwd = et_pwd.getText().toString();

                if (StringUtils.isEmpty(name)) {
                    ViewInject.toast("请输入手机号");
                    return;
                }
                boolean isPhone = AppMethod.isMobileNO(name);
                if (!isPhone) {
                    ViewInject.toast("请输入正确的手机号码");
                    return;
                }
                if (StringUtils.isEmpty(pwd)) {
                    ViewInject.toast("请输入登录密码");
                    return;
                }
                submit(name, pwd);

                break;

            case R.id.tv_forget_pwd:
                PopupWindowUtil.showSingleWindow(this, getString(R.string.forget_pwd), new MiddlePopupWindow.PopupWindowSingleCallBack() {
                    @Override
                    public void onSingleButtonClick() {

                    }
                });
                break;
        }
    }

    private void submit(String name, String pwd) {
        loadDialog.show();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount(name);
        loginRequest.setPassword(pwd);
        OkHttpUtil.postRequest(BaseUrl.LoginIn, loginRequest, LoginResponse.class, new MyRequestCallBack<LoginResponse>() {
            @Override
            public void onPostResponse(LoginResponse obj) {
                if (obj != null) {
                    if ("116".equals(obj.resultCode)) {


                    }
                    loadDialog.dismiss();
                }
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

