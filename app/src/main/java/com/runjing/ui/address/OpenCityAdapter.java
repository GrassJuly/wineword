package com.runjing.ui.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.TitleBarActivity;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.def.CityAdapter;
import com.runjing.bean.response.home.def.HomeAdapter;
import com.runjing.bean.response.home.def.ProvinceBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

import java.util.List;

public class OpenCityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private TitleBarActivity mActivity;
    List<DistrictBean.DataBean> proviceList;
    public OpenCityAdapter(TitleBarActivity outsideAty, List<DistrictBean.DataBean> mList) {
        this.mActivity = outsideAty;
        this.proviceList =mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OpenCityAdapter.ProvincesHolder(LayoutInflater.from(mActivity).inflate(R.layout.layout_item_proviences, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OpenCityAdapter.ProvincesHolder)holder).setData(proviceList, position);
    }

    @Override
    public int getItemCount() {
        return proviceList != null ? proviceList.size() : Appconfig.TAG_ZERO;
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
            OpenCityItemAfapter adapter = new OpenCityItemAfapter(mActivity);
            rv_city.setHasFixedSize(false);
            rv_city.setLayoutManager(new GridLayoutManager(tv_provinces.getContext(), 3));
            rv_city.setNestedScrollingEnabled(false);//禁止滑动
            rv_city.setAdapter(adapter);
            adapter.setData(provinces.get(position).getChildrenList());
        }
    }
}
