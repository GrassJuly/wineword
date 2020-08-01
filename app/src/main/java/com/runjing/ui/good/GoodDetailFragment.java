package com.runjing.ui.good;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.runjing.base.BaseRequest;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.good.GoodDetailBaseBean;
import com.runjing.bean.response.good.GoodDetailBean;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.common.RJBaseUrl;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.GradationScrollView;
import com.runjing.widget.seckill.SecondDownTimerView;
import com.runjing.wineworld.R;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class GoodDetailFragment extends TitleBarFragment implements GradationScrollView.ScrollViewListener, ViewTreeObserver.OnGlobalLayoutListener {

    @BindView(id = R.id.frag_iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.lay_ll_title)
    private LinearLayout ll_title;
    @BindView(id = R.id.lay_rl_good, click = true)
    private RelativeLayout rl_good;
    @BindView(id = R.id.lay_tv_good, click = true)
    private TextView tv_good;
    @BindView(id = R.id.lay_iv_good)
    private View v_good;
    @BindView(id = R.id.lay_rl_detail, click = true)
    private RelativeLayout rl_detail;
    @BindView(id = R.id.lay_tv_detail, click = true)
    private TextView tv_detail;
    @BindView(id = R.id.lay_v_detail)
    private View v_detail;
    @BindView(id = R.id.lay_iv_share, click = true)
    private ImageView iv_share;
    @BindView(id = R.id.frag_banner)
    private Banner banner;
    @BindView(id = R.id.frag_gs_content)
    private GradationScrollView gs_content;
    @BindView(id = R.id.frag_ll_snakill)
    private LinearLayout ll_snakill;
    @BindView(id = R.id.frag_tv_price)
    private TextView tv_priceS_;
    @BindView(id = R.id.frag_tv_price)
    private TextView tv_priceS;
    @BindView(id = R.id.frag_tv_stock_s)
    private TextView tv_stockS;
    @BindView(id = R.id.frag_st_time)
    private SecondDownTimerView st_time;
    @BindView(id = R.id.frag_tv_price_n)
    private TextView tv_priceN;
    @BindView(id = R.id.frag_tv_price_p)
    private TextView tv_priceP;
    @BindView(id = R.id.frag_iv_plus)
    private ImageView iv_plus;
    @BindView(id = R.id.frag_tv_stock)
    private TextView tv_stock;
    @BindView(id = R.id.frag_tv_realprice)
    private TextView tv_realprice;
    @BindView(id = R.id.frag_tv_name)
    private TextView tv_name;
    @BindView(id = R.id.frag_rv_discount)
    private RecyclerView rv_discount;
    @BindView(id = R.id.frag_iv_discount, click = true)
    private ImageView iv_discount;
    @BindView(id = R.id.frag_iv_store)
    private ImageView iv_store;
    @BindView(id = R.id.frag_tv_storename)
    private TextView tv_storename;
    @BindView(id = R.id.frag_tv_dis)
    private TextView tv_dis;
    @BindView(id = R.id.frag_tv_store, click = true)
    private TextView tv_store;
    @BindView(id = R.id.frag_wv_content)
    private WebView wb_content;
    @BindView(id = R.id.frag_rl_tab)
    private RelativeLayout rl_tab;
    @BindView(id = R.id.frag_il_shop, click = true)
    private RelativeLayout rl_shop;
    @BindView(id = R.id.frag_iv_shop, click = true)
    private ImageView iv_shop;
    @BindView(id = R.id.frag_tv_shopNum)
    private TextView tv_shopNum;
    @BindView(id = R.id.frag_tv_money)
    private TextView tv_money;
    @BindView(id = R.id.frag_tv_reducemoney)
    private TextView tv_reduceMoney;
    @BindView(id = R.id.frag_tv_add, click = true)
    private TextView tv_add;
    @BindView(id = R.id.frag_tv_settlement, click = true)
    private TextView tv_settlement;

    private int height;
    private DiscountAdapter adapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_good_detail, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 0;
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(getActivity());
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(this);
        adapter = new DiscountAdapter(getActivity());
        rv_discount.setHasFixedSize(false);
        rv_discount.setNestedScrollingEnabled(false);
        rv_discount.setLayoutManager(new LinearLayoutManager(outsideAty));
        rv_discount.setAdapter(adapter);
        setData(null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v != null) {
            switch (v.getId()) {
                case R.id.frag_iv_back:
                    finish();
                    break;
                case R.id.lay_rl_good:
                case R.id.lay_tv_good:

                    break;
                case R.id.lay_rl_detail:
                case R.id.lay_tv_detail:
                    break;
                case R.id.lay_iv_share:
                    ViewInject.showCenterToast(this, "分享");
                    break;
                case R.id.frag_iv_shop:
                    if (getResources().getString(R.string.tag_no).equals(v.getTag())) {
                        iv_shop.setTag(getResources().getString(R.string.tag_yes));
                        rl_shop.setVisibility(View.GONE);
                        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff));
                    } else {
                        iv_shop.setTag(getResources().getString(R.string.tag_no));
                        rl_shop.setVisibility(View.VISIBLE);
                        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff), 66);
                    }
                    break;
                case R.id.frag_tv_add:
                    AppMethod.showMsg(getActivity(), "测试");
                    break;
                case R.id.frag_tv_settlement:
                    break;
            }
        }
    }

    @Override
    public void onGlobalLayout() {
        height = 40;
//        height = banner.getHeight() / 3;
//        System.out.println("Height -------" + banner.getHeight());
//        System.out.println("Height -------" + height);
        gs_content.setScrollViewListener(GoodDetailFragment.this);
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
//        System.out.println("y +++++++++  " + y);
        if (y <= 0) {   //设置标题的背景颜色
            iv_back.setImageResource(R.mipmap.icon_back_c);
            ll_title.setVisibility(View.INVISIBLE);
            iv_share.setImageResource(R.mipmap.icon_share_l);
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
//            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
//            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));

        } else {    //滑动到banner下面设置普通颜色
            iv_back.setImageResource(R.mipmap.icon_title_back);
            ll_title.setVisibility(View.VISIBLE);
            iv_share.setImageResource(R.mipmap.icon_share_h);
        }
    }

    public void getData() {
//        OkHttpUtil.postRequest(RJBaseUrl.AppMain, new BaseRequest(), GoodDetailBaseBean.class, new MyRequestCallBack<GoodDetailBaseBean>() {
//            @Override
//            public void onPostResponse(GoodDetailBaseBean response) {
//                if (Appconfig.RequestSuccess.equals(response.resultCode)) {
//                    setData(response.getData());
//                }
//            }
//
//            @Override
//            public void onPostErrorResponse(Exception e, String msg) {
//
//            }
//
//            @Override
//            public void onNoNetWork() {
//
//            }
//        });
    }

    public void setData(GoodDetailBean response) {
        AppMethod.DetailBanner(outsideAty, banner, HomeData.getBanner());
        adapter.setData(response);
    }

}
