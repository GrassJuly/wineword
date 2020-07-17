package com.runjing.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.17 11:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:33.
 * @Remark:
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private Activity context;
    private List<GoodBean> data;

    public HomeAdapter(Activity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<GoodBean> list) {
        if (list != null) {
            data = list;
        }
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_good, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        Glide.with(context).load(data.get(position).getImage()).into(holder.iv_good);
        //这个后期根据后台切图动态删除， 我找的图太大 尺寸不对
        setImageWH(holder.iv_good);
        holder.tv_name.setText(data.get(position).getName());
        holder.tv_desc.setText(data.get(position).getDesc());
        holder.tv_price.setText(data.get(position).getPrice());
        holder.tv_favprice.setText(data.get(position).getFavorablePrice());
        holder.ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMethod.postShowWith(context, SimpleBackPage.GoodDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : Appconfig.TAG_ZERO;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_detail;
        private ImageView iv_good;
        private TextView tv_name;
        private TextView tv_desc;
        private TextView tv_price;
        private TextView tv_favprice;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            ll_detail = itemView.findViewById(R.id.lay_ll_order_detail);
            iv_good = itemView.findViewById(R.id.lay_iv_good);
            tv_name = itemView.findViewById(R.id.lay_tv_name);
            tv_desc = itemView.findViewById(R.id.lay_tv_desc);
            tv_price = itemView.findViewById(R.id.lay_tv_price);
            tv_favprice = itemView.findViewById(R.id.lay_tv_favorablePrice);
        }
    }

    public void setImageWH(ImageView image) {
        Display display = context.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
        //设置图片的相对于屏幕的宽高比
        params.width = width/3;
        params.height =  (int) (Math.random() * 400) ;
        image.setLayoutParams(params);
    }
}
