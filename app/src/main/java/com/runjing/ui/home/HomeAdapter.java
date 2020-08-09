package com.runjing.ui.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeData;
import com.runjing.bean.response.home.HomeStoreBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.http.ApiServices;
import com.runjing.utils.GlideUtils;
import com.runjing.utils.StorageUtil;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.runjing.bean.response.home.HomeData.TYPE_ITEM_CITY;
import static com.runjing.bean.response.home.HomeData.TYPE_ITEM_GOOD;
import static com.runjing.bean.response.home.HomeData.TYPE_ITEM_STORE;

/**
 * @Created: qianxs  on 2020.07.17 11:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:33.
 * @Remark:
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private HomeData response;

    public HomeAdapter(Activity context) {
        this.context = context;
    }

    public void setData(HomeData data) {
        if (data != null) {
            this.response = data;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (response != null) {
            if (viewType == TYPE_ITEM_GOOD) {
                return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_good, null));
            } else if (viewType == TYPE_ITEM_STORE) {
                return new StoreHolder(LayoutInflater.from(context).inflate(R.layout.layout_store_msg, parent, false));
            } else if (viewType == TYPE_ITEM_CITY) {
                return new ProvincesHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_proviences, parent, false));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
                ((GoodHolder) holder).setData(response.getHomeGoodBean().getData().getList(), position);
            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
                ((StoreHolder) holder).setData(response.getHomeStoreBean().getData().getFreshStoreVOList(), position);
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                ((ProvincesHolder) holder).setData(response.getDistrictBean().getData(), position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return response.getItemTpye();
    }

    @Override
    public int getItemCount() {
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
                return response.getHomeGoodBean().getData().getList() != null ? response.getHomeGoodBean().getData().getList().size() : Appconfig.TAG_ZERO;
            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
                return response.getHomeStoreBean().getData().getFreshStoreVOList() != null ? response.getHomeStoreBean().getData().getFreshStoreVOList().size() : Appconfig.TAG_ZERO;
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                return response.getDistrictBean().getData() != null ? response.getDistrictBean().getData().size() : Appconfig.TAG_ZERO;
            }
        }
        return Appconfig.TAG_ZERO;
    }


    public class GoodHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_detail;
        private ImageView iv_good;
        private TextView tv_name;
        private TextView tv_desc;
        private TextView tv_price;
        private TextView tv_favprice;
        private RecyclerView rv_tag;
        private LinearLayout ll_plus;
        private TextView tv_PlusPrice;

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

        public void setMargin(CardView ll_content, int position) {
            CardView.LayoutParams params = (CardView.LayoutParams) ll_content.getLayoutParams();
            params.topMargin = DensityUtils.dip2dp(ll_content.getContext(), 10);
            params.rightMargin = DensityUtils.dip2dp(ll_content.getContext(), 10);
            if (isOne(position)) {
                params.leftMargin = DensityUtils.dip2dp(ll_content.getContext(), 0);
            } else {
                params.leftMargin = DensityUtils.dip2dp(ll_content.getContext(), 10);
            }
        }

        /**
         * 设置编剧
         *
         * @param n
         * @return
         */
        public boolean isOne(int n) {
            if (n <= 0) return false;
            return (n &= (n - 1)) == 0;
        }
    }

    public class StoreHolder extends RecyclerView.ViewHolder {

        private ImageView iv_store;
        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_distance;
        private TextView tv_rest;
        private LinearLayout ll_location;
        private TextView tv_store;

        public StoreHolder(@NonNull View itemView) {
            super(itemView);
            iv_store = itemView.findViewById(R.id.lay_item_iv_store);
            tv_name = itemView.findViewById(R.id.lay_item_tv_name);
            tv_rest = itemView.findViewById(R.id.lay_item_tv_rest);
            tv_address = itemView.findViewById(R.id.lay_item_tv_address);
            tv_distance = itemView.findViewById(R.id.lay_item_tv_distance);
            ll_location = itemView.findViewById(R.id.lay_item_ll_location);
            tv_store = itemView.findViewById(R.id.lay_item_tv_store);
        }

        public void setData(List<HomeStoreBean.DataBean.FreshStoreVOListBean> stores, int position) {
            if (stores != null && stores.size() > 0) {
                GlideUtils.getInstance().displayImageCenter(iv_store, stores.get(position).getImage(), iv_store.getContext(), R.mipmap.ic_launcher);
                tv_name.setText(AppMethod.setDefault(stores.get(position).getName()));
                tv_address.setText(AppMethod.setDefault(stores.get(position).getAddressDetail()));
                tv_distance.setText(AppMethod.setDefault(stores.get(position).getDistance()) + "km");
                if (stores.get(position).getStatus() == 1) {
                    tv_rest.setVisibility(View.GONE);
                } else if (stores.get(position).getStatus() == 2){
                    tv_rest.setVisibility(View.VISIBLE);
                }
                ll_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ViewInject.longToast("导航页面");
                    }
                });
                tv_store.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(Appconfig.TAG, stores.get(position).getId());
                        AppMethod.postShowWith(tv_store.getContext(), SimpleBackPage.StoreDetail, bundle);
                    }
                });
            }
        }
    }

    public class ProvincesHolder extends RecyclerView.ViewHolder {
        private TextView tv_provinces;
        private RecyclerView rv_city;

        public ProvincesHolder(@NonNull View itemView) {
            super(itemView);
            tv_provinces = itemView.findViewById(R.id.lay_item_tv_proviences);
            rv_city = itemView.findViewById(R.id.lay_item_rv_city);
        }

        public void setData(List<DistrictBean.DataBean> provinces, int position) {
            tv_provinces.setText(AppMethod.setDefault(provinces.get(position).getName()));
            CityAdapter adapter = new CityAdapter(tv_provinces.getContext());
            rv_city.setHasFixedSize(false);
            rv_city.setLayoutManager(new GridLayoutManager(tv_provinces.getContext(), 3));
            rv_city.setNestedScrollingEnabled(false);//禁止滑动
            rv_city.setAdapter(adapter);
            adapter.setData(provinces.get(position).getChildrenList());
        }
    }


}
