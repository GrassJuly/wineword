package com.runjing.ui.good;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.test.HomeData;
import com.runjing.common.AppMethod;
import com.runjing.utils.StatusBarUtil;
import com.runjing.widget.GradationScrollView;
import com.runjing.wineworld.R;
import com.youth.banner.Banner;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;

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
    private int height;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_good_detail, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 0;
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        AppMethod.DetailBanner(outsideAty, banner, HomeData.getBanner());
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(this);
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
            }
        }
    }

    @Override
    public void onGlobalLayout() {
        height = 50;
//        height = banner.getHeight() / 3;
//        System.out.println("Height -------" + banner.getHeight());
//        System.out.println("Height -------" + height);
        gs_content.setScrollViewListener(GoodDetailFragment.this);
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        System.out.println("y +++++++++  " + y);
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
}
