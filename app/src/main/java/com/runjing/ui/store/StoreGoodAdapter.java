package com.runjing.ui.store;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.def.HomeBean;
import com.runjing.bean.response.store.DetailGoodBean;
import com.runjing.common.AppMethod;
import com.runjing.http.ApiServices;
import com.runjing.ui.home.TagAdapter;
import com.runjing.utils.GlideUtils;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.ViewInject;

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
public class StoreGoodAdapter extends RecyclerView.Adapter<StoreGoodAdapter.GoodHolder> {
    private Activity context;
    private List<GoodBean.DataBean.ListBean> data;

    public StoreGoodAdapter(Activity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<GoodBean.DataBean.ListBean> list) {
        if (list != null) {
            data = list;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public StoreGoodAdapter.GoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_detail_good, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreGoodAdapter.GoodHolder holder, int position) {
        holder.setData(data, position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


     class GoodHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_detail;
        private ImageView iv_good;
        private TextView tv_name;
        private TextView tv_desc;
        private TextView tv_price;
        private TextView tv_favprice;
        private RecyclerView rv_tag;
        private LinearLayout ll_plus;
        private TextView tv_PlusPrice;
        private ImageView iv_add;

        public GoodHolder(@NonNull View itemView) {
            super(itemView);
            ll_detail = itemView.findViewById(R.id.lay_ll_order_detail);
            iv_good = itemView.findViewById(R.id.lay_iv_good);
            tv_name = itemView.findViewById(R.id.lay_tv_name);
            tv_desc = itemView.findViewById(R.id.lay_tv_desc);
            tv_price = itemView.findViewById(R.id.lay_tv_price);
            tv_favprice = itemView.findViewById(R.id.lay_tv_favorablePrice);
            rv_tag = itemView.findViewById(R.id.lay_rv_tag);
            rv_tag.setHasFixedSize(false);
            rv_tag.setNestedScrollingEnabled(false);
            ll_plus = itemView.findViewById(R.id.lay_ll_plus);
            tv_favprice = itemView.findViewById(R.id.lay_tv_Plus_price);
            iv_add = itemView.findViewById(R.id.lay_iv_add);
        }

         public void setData(List<GoodBean.DataBean.ListBean> goods, int position) {
             if (goods != null && goods.size() > 0) {
//                setMargin(ll_detail, position);
                 GlideUtils.getInstance().glideLoad(context, ApiServices.BasePic + goods.get(position).getImages().get(0).getImgUrl(), iv_good);
                 tv_name.setText(AppMethod.setDefault(goods.get(position).getGoodsName()));
                 List<GoodBean.DataBean.ListBean.SkuPromotionResultBean.PromoDescListBean> promoDescList = goods.get(position).getSkuPromotionResult().getPromoDescList();
                 ChipsLayoutManager spanLayoutManager = ChipsLayoutManager.newBuilder(rv_tag.getContext())
                         .setOrientation(ChipsLayoutManager.VERTICAL)
                         .setMaxViewsInRow(4)
                         .build();
                 FlexboxLayoutManager manager = new FlexboxLayoutManager(rv_tag.getContext(), FlexDirection.ROW, FlexWrap.WRAP) {
                     @Override
                     public boolean canScrollVertically() {
                         return false;
                     }
                 };
                 rv_tag.setLayoutManager(manager);
                 rv_tag.setAdapter(new TagAdapter(promoDescList));
                 if (TextUtils.isEmpty(privilegePrice(promoDescList))) {
                     tv_price.setText("¥" + AppMethod.changTVsize(AppMethod.setDefault(AppMethod.setDefault(goods.get(position).getSalesPrice() + ""))));
                     tv_favprice.setVisibility(View.INVISIBLE);
                 } else {
                     tv_price.setText("¥" + privilegePrice(promoDescList));
                     tv_favprice.setVisibility(View.VISIBLE);
                 }
                 AppMethod.setTextViewLine(tv_favprice);
                 tv_favprice.setText("¥" + AppMethod.changTVsize(AppMethod.setDefault(AppMethod.setDefault(goods.get(position).getMarketPrice() + ""))));
                 iv_add.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         ViewInject.showCenterToast(context, "添加商品");
                     }
                 });
                 ll_detail.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         AppMethod.postShowWith(context, SimpleBackPage.GoodDetail);
                     }
                 });
             }
         }


         /**
          * 获取优惠价格
          * @param promoDescList
          * @return
          */
         public String privilegePrice(List<GoodBean.DataBean.ListBean.SkuPromotionResultBean.PromoDescListBean> promoDescList) {
             if (promoDescList != null && promoDescList.size() > 0) {
                 for (GoodBean.DataBean.ListBean.SkuPromotionResultBean.PromoDescListBean desc: promoDescList) {
                     if (desc.getPromoSubType() == 100) {
                         return AppMethod.changTVsize(AppMethod.setDefault(desc.getPrivilegePrice() + "")) + "";
                     }
                 }
             }
             return "";
         }
    }

}
