package com.runjing.http;

import com.google.gson.Gson;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.BannerBean;
import com.runjing.bean.response.home.HomeStoreBean;
import com.runjing.bean.response.login.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @Created: qianxs  on 2020.07.29 21:45.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.29 21:45.
 * @Remark:
 */
public interface ApiServices {

    //    public final static String BaseUrl = "https://cxc.jd9sj.com/api/";//线上正式环境
//    public final static String BaseUrl = "https://pre-cxc.jd9sj.com/api/";//线上正式环境
    public final static String BaseUrl = "http://116.196.90.67:9002/api/";//测试环境

    class MyRequestBody {
        public static RequestBody createBody(Object request) {
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(request));
        }
    }

    /*登陆接口*/
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("customer/appLogin")
    Observable<LoginResponse> onLogin(@Body RequestBody body);

    /*首页-获取开通城市接口*/
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("district/getDistrict")
    Observable<DistrictBean> getDistrict(@Body RequestBody body);

    /*首页-获取附近门店列表*/
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("freshStore/list ")
    Observable<HomeStoreBean> getStore(@Body RequestBody body);

    /*首页-获取banner*/
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("banner/wxIndexGet")
    Observable<BannerBean> getBanner(@Body RequestBody body);

    /*首页-获取商品列表*/
    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("product/wxStoreSku")
    Observable<GoodBean> getGood(@Body RequestBody body);

}
