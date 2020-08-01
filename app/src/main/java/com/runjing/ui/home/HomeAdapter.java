package com.runjing.ui.home;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeData;
import com.runjing.bean.response.home.HomeStoreBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.utils.GlideUtils;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        //根据接口返回数据调整
        if (response != null) {
            return response.getItemTpye();
        }
        return super.getItemViewType(position);
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

        public GoodHolder(@NonNull View itemView) {
            super(itemView);
            ll_detail = itemView.findViewById(R.id.lay_ll_order_detail);
            iv_good = itemView.findViewById(R.id.lay_iv_good);
            tv_name = itemView.findViewById(R.id.lay_tv_name);
            tv_desc = itemView.findViewById(R.id.lay_tv_desc);
            tv_price = itemView.findViewById(R.id.lay_tv_price);
            tv_favprice = itemView.findViewById(R.id.lay_tv_favorablePrice);
        }

        public void setData(List<GoodBean.DataBean.ListBean> goods, int position) {
            if (goods != null && goods.size() > 0) {
                GlideUtils.getInstance().displayImageCenter(iv_good, goods.get(position).getImages().get(0).getImgUrl(), iv_good.getContext(), R.mipmap.iv_default);
                //这个后期根据后台切图动态删除， 我找的图太大 尺寸不对
                setImageWH(iv_good);
                tv_name.setText(AppMethod.setDefault(goods.get(position).getGoodsName()));
                tv_desc.setText(AppMethod.setDefault(goods.get(position).getGoodsName()));
                tv_price.setText(AppMethod.setDefault(goods.get(position).getGoodsName()));
                tv_favprice.setText(AppMethod.setDefault(goods.get(position).getGoodsName()));
                ll_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppMethod.postShowWith(context, SimpleBackPage.GoodDetail);
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
                tv_distance.setText(AppMethod.setDefault(stores.get(position).getDistance()));
                if (stores.get(position).getStatus() == 0) {
                    tv_rest.setVisibility(View.GONE);
                } else if (stores.get(position).getStatus() == 1) {
                    tv_rest.setVisibility(View.VISIBLE);
                }
                ll_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewInject.longToast("导航页面");
                    }
                });
                tv_store.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewInject.longToast("门店详情");
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
