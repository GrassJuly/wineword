package com.runjing.ui.home;

/**
 * @Created: qianxs  on 2020.07.17 00:55.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 00:55.
 * @Remark:
 */

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.runjing.bean.response.home.BannerBean;
import com.runjing.ui.login.GuildBannerAdapter;
import com.runjing.utils.GlideUtils;
import com.runjing.wineworld.R;
import com.socks.library.KLog;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.runjing.rjframe.ui.ViewInject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
public class BannerItemAdapter extends BannerAdapter<BannerBean.DataBean, BannerItemAdapter.BannerViewHolder> {
    List<BannerBean.DataBean> bannerBeans;

    Context mContext;

    public BannerItemAdapter(List<BannerBean.DataBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        bannerBeans = mDatas;
    }

    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.layout_item_banner);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView,20);
        }
        return new BannerViewHolder(imageView);
    }


    @Override
    public void onBindView(BannerViewHolder holder, BannerBean.DataBean data, int position, int size) {
        GlideUtils.getInstance().glideLoad(holder.imageView.getContext(), data.getImgUrl(), holder.imageView);
//        GlideUtils.getInstance().displayImageCenter(holder.imageView, data.getImgUrl(), mContext, R.mipmap.iv_default);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewInject.showCenterToast(mContext, "订单详情");
            }
        });
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }

}
