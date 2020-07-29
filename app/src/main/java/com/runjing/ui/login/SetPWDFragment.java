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

import com.runjing.base.TitleBarFragment;
import com.runjing.common.Appconfig;
import com.runjing.utils.JDLogin.UserUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/**
 * @Created: qianxs  on 2020.07.21 09:46.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.21 09:46.
 * @Remark:
 */
public class SetPWDFragment extends TitleBarFragment implements TextWatcher {

    @BindView(id = R.id.frag_et_pwd)
    private EditText et_pwd;
    @BindView(id = R.id.frag_iv_pwddel, click = true)
    private ImageView iv_pwddel;
    @BindView(id = R.id.frag_iv_see, click = true)
    private ImageView iv_see;
    @BindView(id = R.id.frag_tv_login, click = true)
    private TextView tv_login;
    private WJLoginHelper helper;
    private String phoneNum;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_set_pwd, null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        Bundle mBundle = getActivity().getIntent().getBundleExtra(Appconfig.DATA_KEY);
        if (mBundle != null) {
            phoneNum = mBundle.getString(Appconfig.DATA_KEY);
        }
        helper = UserUtil.getWJLoginHelper();
        et_pwd.addTextChangedListener(this);
        tv_login.setClickable(false);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.frag_iv_pwddel:
                    et_pwd.setText("");
                    break;
                case R.id.frag_iv_see:
                    if (getResources().getString(R.string.tag_yes).equals(v.getTag())) {
                        v.setTag(getResources().getString(R.string.tag_yes));
                        iv_see.setImageResource(R.mipmap.icon_see);
                        et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        v.setTag(getResources().getString(R.string.tag_no));
                        iv_see.setImageResource(R.mipmap.icon_nosee);
                        et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    break;
                case R.id.frag_tv_login:
                    onLogin();
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            iv_pwddel.setVisibility(View.GONE);
            tv_login.setClickable(false);
            tv_login.setTextColor(getResources().getColor(R.color.color_999999));
        } else {
            iv_pwddel.setVisibility(View.VISIBLE);
            tv_login.setClickable(true);
            tv_login.setTextColor(getResources().getColor(R.color.color_F80000));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void onLogin() {
        if (TextUtils.isEmpty(et_pwd.getText())) {
            ViewInject.showCenterToast(outsideAty, "密码不能为空");
            return;
        }

        helper.setLoginPassword(phoneNum, et_pwd.getText().toString().trim(), new OnCommonCallback() {
            @Override
            public void onSuccess() {
                MMKVUtil.getInstance().encode(Appconfig.JDPin, helper.getPin());
                MMKVUtil.getInstance().encode(Appconfig.IsPhone, phoneNum);
                ViewInject.showCenterToast(outsideAty, "设置成功");
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
}
