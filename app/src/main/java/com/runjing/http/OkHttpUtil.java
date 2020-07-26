package com.runjing.http;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fdlm.util.DESUtil;
import com.runjing.MyApplication;
import com.runjing.common.AppMethod;
import com.squareup.okhttp.Request;

import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.RJLoger;
import org.runjing.rjframe.utils.SystemTool;

import java.io.File;
import java.io.IOException;

/**
 * okHttp网络请求
 * Created by zm on 2016/8/15.
 */
public class OkHttpUtil {
    public static DESUtil desutil = new DESUtil("540OVX3d6fHGP8c5e312024be9dfg6s2");
    private static String data;
    private static String sessionId;
    private static final String TAG = "HTTP_CONNECT";

    /**
     * 网络请求
     *
     * @param url
     * @param obj
     * @param clazz
     * @param callback
     * @return
     */
    public static void postRequest(final String url, Object obj, final Class clazz, final MyRequestCallBack callback) {
        if (url.startsWith("http")) {
            try {
                if (obj != null) {
                    data = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
                    if (SystemTool.DEBUG) {
                        RJLoger.debug(url);
                        RJLoger.debug(data);
                    }
                    data = desutil.getEncAndBase64String(data);

                }
            } catch (Exception e) {
                callback.onPostErrorResponse(e, "数据错误"); // TODO 通用错误提示
            }
        }

        boolean isNetWork = AppMethod.isNetworkConnected(MyApplication.contextApp);
        if (!isNetWork) {
//            ToastUtils.showToast(MyApplication.contextApp, "网络环境不佳，请检查网络");
            callback.onNoNetWork();
//            callback.onPostResponse(null);
        } else {
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    try {
                        callback.onPostErrorResponse(e, e.getMessage());
                        if (SystemTool.DEBUG) RJLoger.debug(e.getMessage());
                    }catch(NullPointerException e3){
                        RJLoger.debug("返回数据为null");
                    } catch (IllegalArgumentException e1) {
                        ViewInject.showCenterToast(MyApplication.contextApp, "请求时间超时,请重试");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override
                public void onResponse(String response) {
                    try {
                        response = desutil.getDesAndBase64String(response);
                        if (SystemTool.DEBUG) RJLoger.debug(response);
                        if (!TextUtils.isEmpty(response)) {
                            callback.onPostResponse(JSON.parseObject(response, clazz));
                            if (!SystemTool.checkNet(MyApplication.contextApp)) {
                                ViewInject.showCenterToast(MyApplication.contextApp, "当前无网络链接,请检查网络");
                            }
                        } else {
                            response = "{resultCode:\"119\",resultInfo:\"系统未知错误请联系客服人员\"}";
                            callback.onPostResponse(JSON.parseObject(response, clazz));
                        }
                    } catch (JSONException e) {
                        try {
                            response = "{resultCode:\"119\",resultInfo:\"数据结构异常请联系相关人员\"}";
                            callback.onPostResponse(JSON.parseObject(response, clazz));
                        } catch (Exception e1) {
                            RJLoger.debug(e1.toString());
                        }
                    } catch (Exception e) {
                        response = "{resultCode:\"119\",resultInfo:\"系统未知错误请联系客服人员\"}";
                        callback.onPostResponse(JSON.parseObject(response, clazz));
                    }
                }
            }, new OkHttpClientManager.Param[]{
                    new OkHttpClientManager.Param("data", data)});
        }
    }


    /**
     * 网络请求上传图片及参数
     *
     * @param url
     * @param obj
     * @param clazz
     * @param file
     * @param fileKey
     * @param callback
     */
    public static void postFileRequest(final String url, Object obj, final Class clazz, File file, String fileKey, final MyRequestCallBack callback) {
        if (url.startsWith("http")) {
            try {
//                data = new Gson().toJson(obj);//toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
                data = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
                RJLoger.debug("data =====> " + data);
            } catch (Exception e) {
                callback.onPostErrorResponse(e, "数据错误"); // TODO 通用错误提示
            }
        }


        data = desutil.getEncAndBase64String(data);

        try {
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    callback.onPostErrorResponse(e, e.getMessage());
                    RJLoger.debug("response =====> " + e.getMessage());
                }


                @Override
                public void onResponse(String response) {
                    response = desutil.getDesAndBase64String(response);
                    RJLoger.debug("response ====> " + response);
//                  callback.onPostResponse(new Gson().fromJson(response, clazz));
                    callback.onPostResponse(JSON.parseObject(response, clazz));
                }
            }, file, fileKey, new OkHttpClientManager.Param[]{
                    new OkHttpClientManager.Param("data", data)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传单张图片
     *
     * @param url
     * @param clz
     * @param file
     * @param fileKey
     * @param callback
     */
    public static void uploadFile(final String url, final Class clz, File file, String fileKey, final MyRequestCallBack callback) {
        try {
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    callback.onPostErrorResponse(e, e.getMessage());
                    RJLoger.debug("response =====> " + e.getMessage());
                }


                @Override
                public void onResponse(String response) {
                    response = desutil.getDesAndBase64String(response);
                    RJLoger.debug("response ====> " + response);
//                    callback.onPostResponse(new Gson().fromJson(response, clz));
                    callback.onPostResponse(JSON.parseObject(response, clz));
                }
            }, file, fileKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传多张图片
     *
     * @param url
     * @param clz
     * @param files
     * @param fileKeys
     * @param callback
     */

    public static void uploadFiles(final String url, Object obj, final Class clz, File[] files, String[] fileKeys, final MyRequestCallBack callback) {
        if (url.startsWith("http")) {
            try {
//                data = new Gson().toJson(obj);//toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
                data = JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
            } catch (Exception e) {
                callback.onPostErrorResponse(e, "数据错误"); // TODO 通用错误提示
            }
        }
        data = desutil.getEncAndBase64String(data);

        try {
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    callback.onPostErrorResponse(e, e.getMessage());
                    RJLoger.debug("response =====> " + e.getMessage());
                }


                @Override
                public void onResponse(String response) {
                    response = desutil.getDesAndBase64String(response);
                    RJLoger.debug("response ====> " + response);
//                    callback.onPostResponse(new Gson().fromJson(response, clz));
                    callback.onPostResponse(JSON.parseObject(response, clz));
                }
            }, files, fileKeys, new OkHttpClientManager.Param[]{
                    new OkHttpClientManager.Param("data", data)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
