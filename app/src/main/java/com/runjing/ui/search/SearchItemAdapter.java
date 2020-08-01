package com.runjing.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.SimpleBackPage;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;

import com.runjing.bean.response.home.def.GoodBean;
import com.runjing.wineworld.R;
import org.runjing.rjframe.utils.StringUtils;

import java.util.List;

public class SearchItemAdapter   extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Activity context;
    private List<GoodBean> goodsList;
    public SearchItemAdapter(Activity context,List<GoodBean> list) {
        this.context = context;
        this.goodsList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_near_address_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchItemAdapter.GoodHolder)holder).setData(goodsList, position);
    }

    @Override
    public int getItemCount() {
        return goodsList != null ? goodsList.size() : Appconfig.TAG_ZERO;
    }


    public class GoodHolder extends RecyclerView.ViewHolder {

        private TextView txt_search_goods_name;
        private LinearLayout lay_near;
        String goodsName;
        public GoodHolder(@NonNull View itemView) {
            super(itemView);

            txt_search_goods_name = itemView.findViewById(R.id.tv_near_address);
            lay_near = itemView.findViewById(R.id.lay_near);
        }

        public void setData(List<GoodBean> goods, int position) {
            if (goods != null && goods.size() > 0) {
                 goodsName = goods.get(position).getName();
                if(!StringUtils.isEmpty(goodsName)){
                    txt_search_goods_name.setText(goodsName);
                }

                lay_near.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Appconfig.DATA_KEY,goodsName);
                        AppMethod.postShowWith(context, SimpleBackPage.SearchGoods);
                    }
                });
            }
        }
    }
}
