package com.runjing.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.runjing.MyApplication;
import com.runjing.wineworld.BuildConfig;

import org.json.JSONObject;

import jd.wjlogin_sdk.common.WJLoginExtendProxy;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ClientInfo;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/**
 * 构造单例helper,开发中建议使用单例模式。
 *
 * @author Administrator
 */

public class UserUtil {
    private static WJLoginHelper helper;
    public static final short APPID = 100;

    private static ClientInfo getClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        //下面的值由接入方填充
        clientInfo.setDwAppID(APPID); //必填，appId, 无线统一登录后台分配
        clientInfo.setAppName("WJLoginAndroidDemo");//必填，APP名字
        clientInfo.setDwGetSig(1);//必填，这里统一填1
        clientInfo.setUnionId("50965");//选填,联盟号，有就填值，没有就不用写这一句
        clientInfo.setSubunionId("jingdong");//选填,子联盟号，有就填值，没有就不用写这一句
        clientInfo.setPartner("jingdong");//选填,渠道号，有就填值，没有就不用写这一句
        return clientInfo;
    }


    public static void refreshA2() {
        getWJLoginHelper().refreshA2(new OnCommonCallback() {

            @Override
            public void onSuccess() {
                Toast.makeText(MyApplication.contextApp, "刷新A2成功",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(FailResult failResult) {

            }

            @Override
            public void onError(ErrorResult error) {
                // TODO 自动生成的方法存根
                Toast.makeText(MyApplication.contextApp, error + "",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 单例模式的helper
     *
     * @return
     */
    public synchronized static WJLoginHelper getWJLoginHelper() {
        if (helper == null) {
            Log.i("LoginSDK.UserUtil", "getWJLoginHelper");
            helper = WJLoginHelper.createInstance(MyApplication.contextApp, UserUtil.getClientInfo(), BuildConfig.DEBUG);
            helper.setWJLoginExtendProxy(mLoginParamProxy);
        }
        return helper;
    }

    private static WJLoginExtendProxy mLoginParamProxy = new WJLoginExtendProxy() {
        @Override
        public String getUuid() {
            return getDeviceId();
        }

        @Override
        public String getArea() {
            return "123.2333_67.099990";
        }

        @Override
        public String getDeviceFinger() {
            //新增联合指纹的key&value固定为"unionwsws"
            //如果之前实现了getDeviceFinger，且有返回值，请确保历史数据也 put 进了 json
            JSONObject json = new JSONObject();
            //从安全联合指纹sdk中获取到数据
      //      json.put("unionwsws", DeviceFingerUtils.getMergeLogo(getApplicationContext()));
            return json.toString();

        }

        @Override
        public String getJMAFinger() {
            //接入了JMA sdk 的app需要传递，未接入过的传""即可
            return "";
        }
    };


    public static String getDeviceId() {
        try {
            TelephonyManager tm = (TelephonyManager) MyApplication.contextApp.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            return imei == null ? "" : imei;
        } catch (Exception e) {
            return "";
        }
    }




    private static Toast toast;

    /**
     * @param text
     */
    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.contextApp, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);//如果不为空，则直接改变当前toast的文本
        }
        toast.show();
    }
}
