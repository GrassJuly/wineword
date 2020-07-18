package com.runjing.ui.home;

import android.app.Activity;
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
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.response.home.ProvinceBean;
import com.runjing.bean.response.home.StoreBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_CITY;
import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_GOOD;
import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_STORE;

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
    private HomeBean response;
    private List<GoodBean> data;

    public HomeAdapter(Activity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(HomeBean data, int type) {
        if (response != null) {
            this.response = data;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
                return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_good, null));
            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
                return new StoreHolder(LayoutInflater.from(context).inflate(R.layout.layout_store_msg, null));
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                return new ProvincesHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_proviences, null));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (response != null) {
//            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
//                return response.getItemTpye();
//            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
//                return response.getItemTpye();
//            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
//                return response.getItemTpye();
//            }
//        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据接口返回数据调整
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
                return response.getItemTpye();
            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
                return response.getItemTpye();
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                return response.getItemTpye();
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_GOOD) {
                return response.getGoods() != null ? response.getGoods().size() : Appconfig.TAG_ZERO;
            } else if (response.getItemTpye() == TYPE_ITEM_STORE) {
                return response.getStores() != null ? response.getStores().size() : Appconfig.TAG_ZERO;
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                return response.getProvinces() != null ? response.getProvinces().size() : Appconfig.TAG_ZERO;
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

        public void setData(List<GoodBean> goods, int position) {
            if (goods != null && goods.size() > 0) {
                Glide.with(context).load(data.get(position).getImage()).into(iv_good);
                //这个后期根据后台切图动态删除， 我找的图太大 尺寸不对
                setImageWH(iv_good);
                tv_name.setText(AppMethod.isEntity(goods.get(position).getName()));
                tv_desc.setText(AppMethod.isEntity(goods.get(position).getDesc()));
                tv_price.setText(AppMethod.isEntity(goods.get(position).getPrice()));
                tv_favprice.setText(AppMethod.isEntity(goods.get(position).getFavorablePrice()));
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
        private LinearLayout ll_location;
        private TextView tv_store;

        public StoreHolder(@NonNull View itemView) {
            super(itemView);
            iv_store = itemView.findViewById(R.id.lay_item_iv_store);
            tv_name = itemView.findViewById(R.id.lay_item_tv_name);
            tv_address = itemView.findViewById(R.id.lay_item_tv_address);
            tv_distance = itemView.findViewById(R.id.lay_item_tv_distance);
            ll_location = itemView.findViewById(R.id.lay_item_ll_location);
            tv_store = itemView.findViewById(R.id.lay_item_tv_store);
        }

        public void setData(List<StoreBean> stores, int position) {
            if (stores != null && stores.size() > 0) {
                Glide.with(context).load(stores.get(position).getStoreImage()).into(iv_store);
                tv_name.setText(AppMethod.isEntity(stores.get(position).getName()));
                tv_address.setText(AppMethod.isEntity(stores.get(position).getAddress()));
            }
        }
    }

    public class ProvincesHolder extends RecyclerView.ViewHolder {
        public ProvincesHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
