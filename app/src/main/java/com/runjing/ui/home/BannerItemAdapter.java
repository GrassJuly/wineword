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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.runjing.bean.response.home.BannerBean;
import com.runjing.utils.GlideUtils;
import com.runjing.wineworld.R;
import com.youth.banner.adapter.BannerAdapter;

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

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ImageView imageView = new ImageView(mContext);
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerBean.DataBean data, int position, int size) {
        GlideUtils.getInstance().displayImageCenter(holder.imageView, data.getImgUrl(), mContext, R.mipmap.iv_default);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewInject.showCenterToast(mContext, "订单详情");
            }
        });
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
