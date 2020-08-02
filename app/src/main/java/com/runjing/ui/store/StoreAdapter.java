package com.runjing.ui.store;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.base.SimpleBackActivity;
import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.def.CityAdapter;
import com.runjing.bean.response.home.def.GoodBean;
import com.runjing.bean.response.home.def.HomeBean;
import com.runjing.bean.response.home.def.ProvinceBean;
import com.runjing.bean.response.home.def.StoreBean;
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

import static com.runjing.bean.response.home.def.HomeBean.TYPE_ITEM_CITY;
import static com.runjing.bean.response.home.def.HomeBean.TYPE_ITEM_GOOD;
import static com.runjing.bean.response.home.def.HomeBean.TYPE_ITEM_STORE;


/**
 * @Created: qianxs  on 2020.07.17 11:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.17 11:33.
 * @Remark:
 */
public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private HomeBean response;
    private List<GoodBean> data;

    public StoreAdapter(Activity context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(HomeBean data) {
        if (data != null) {
            this.response = data;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (response != null) {
            if (viewType == TYPE_ITEM_STORE) {
                return new StoreHolder(LayoutInflater.from(context).inflate(R.layout.layout_store_msg, parent, false));
            } else if (viewType == TYPE_ITEM_CITY) {
                return new ProvincesHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_proviences, parent, false  ));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (response != null) {
            if (response.getItemTpye() == TYPE_ITEM_STORE) {
                ((StoreHolder) holder).setData(response.getStores(), position);
            } else if (response.getItemTpye() == TYPE_ITEM_CITY) {
                ((ProvincesHolder) holder).setData(response.getProvinces(), position);
            }
        }
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

        public void setData(List<StoreBean> stores, int position) {
            if (stores != null && stores.size() > 0) {
                GlideUtils.getInstance().displayImageCenter(iv_store, stores.get(position).getStoreImage(), iv_store.getContext(), R.mipmap.ic_launcher);
                tv_name.setText(AppMethod.setDefault(stores.get(position).getName()));
                tv_address.setText(AppMethod.setDefault(stores.get(position).getAddress()));
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
                        AppMethod.postShowWith((SimpleBackActivity)tv_store.getContext(), SimpleBackPage.StoreDetail);
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

        public void setData(List<ProvinceBean> provinces, int position) {
            tv_provinces.setText(AppMethod.setDefault(provinces.get(position).getProvince()));
            CityAdapter adapter = new CityAdapter(tv_provinces.getContext());
            rv_city.setHasFixedSize(false);
            rv_city.setLayoutManager(new GridLayoutManager(tv_provinces.getContext(), 3));
            rv_city.setNestedScrollingEnabled(false);//禁止滑动
            rv_city.setAdapter(adapter);
            adapter.setData(provinces.get(position).getCitys());
        }
    }


}
