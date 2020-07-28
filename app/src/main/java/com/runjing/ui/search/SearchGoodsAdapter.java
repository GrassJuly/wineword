package com.runjing.ui.search;

import android.app.Activity;
import android.graphics.Paint;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.ui.home.HomeAdapter;
import com.runjing.utils.GlideUtils;
import com.runjing.wineworld.R;

import java.util.ArrayList;
import java.util.List;

public class SearchGoodsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private List<GoodBean> goodsList;
    public SearchGoodsAdapter(Activity context,List<GoodBean> list) {
        this.context = context;
        this.goodsList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_goods_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchGoodsAdapter.GoodHolder)holder).setData(goodsList, position);
    }


    @Override
    public int getItemCount() {
        return goodsList != null ? goodsList.size() : Appconfig.TAG_ZERO;
    }


    public class GoodHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_detail;
        private ImageView iv_good;
        private TextView txt_search_goods_name;
        private TextView tv_promotion_price;
        private TextView tv_original_price;

        public GoodHolder(@NonNull View itemView) {
            super(itemView);
            ll_detail = itemView.findViewById(R.id.lay_ll_order_detail);
            iv_good = itemView.findViewById(R.id.lay_iv_good);
            txt_search_goods_name = itemView.findViewById(R.id.txt_search_goods_name);
            tv_promotion_price = itemView.findViewById(R.id.txt_goods_promotion_price);
            tv_original_price = itemView.findViewById(R.id.txt_goods_original_price);
        }

        public void setData(List<GoodBean> goods, int position) {
            if (goods != null && goods.size() > 0) {
                GlideUtils.getInstance().displayImageCenter(iv_good, goods.get(position).getImage(), iv_good.getContext(), R.mipmap.ic_launcher);
                //这个后期根据后台切图动态删除， 我找的图太大 尺寸不对
                setImageWH(iv_good);
                txt_search_goods_name.setText(AppMethod.setDefault(goods.get(position).getName())+AppMethod.setDefault(goods.get(position).getDesc()));
                tv_promotion_price.setText(AppMethod.setDefault(goods.get(position).getPrice()));
                tv_original_price.setText(AppMethod.setDefault(goods.get(position).getFavorablePrice()));
                tv_original_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
                ll_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        AppMethod.postShowWith(context, SimpleBackPage.GoodDetail);
                    }
                });
            }
        }
    }

    public void setImageWH(ImageView image) {
        Display display = context.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
        //设置图片的相对于屏幕的宽高比
        params.width = width / 3;
        params.height = (int) (Math.random() * 400);
        image.setLayoutParams(params);
    }
}
