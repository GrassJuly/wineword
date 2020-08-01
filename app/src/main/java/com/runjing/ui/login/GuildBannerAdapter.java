package com.runjing.ui.login;

/**
 * @Created: qianxs  on 2020.07.17 00:55.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 00:55.
 * @Remark:
 */

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.runjing.MainActivity;
import com.runjing.bean.response.home.def.BannerBean;
import com.runjing.common.Appconfig;
import com.runjing.utils.GlideUtils;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.wineworld.R;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
public class GuildBannerAdapter extends BannerAdapter<BannerBean, GuildBannerAdapter.BannerViewHolder> {
    List<BannerBean> bannerBeans;

    public GuildBannerAdapter(List<BannerBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        if (mDatas != null) {
            bannerBeans = mDatas;
        } else {
            bannerBeans = new ArrayList<>();
        }
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_guild_banner, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerBean data, int position, int size) {
        GlideUtils.getInstance().displayImageCenter(holder.imageView, bannerBeans.get(position).getImage(), holder.imageView.getContext(), R.mipmap.ic_launcher);
        if (position == bannerBeans.size() - 1) {
            holder.tv_go.setVisibility(View.VISIBLE);
        } else {
            holder.tv_go.setVisibility(View.GONE);
        }
        holder.tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MMKVUtil.getInstance().encode(Appconfig.IS_GUILD, true);
                holder.tv_go.getContext().startActivity(new Intent(holder.tv_go.getContext(), MainActivity.class));
                ((GuildActivity)holder.tv_go.getContext()).finish();
            }
        });
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tv_go;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lay_iv_banner);
            tv_go = itemView.findViewById(R.id.lay_tv_go);
        }
    }
}
