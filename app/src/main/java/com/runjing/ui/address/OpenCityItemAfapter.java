package com.runjing.ui.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.TitleBarActivity;
import com.runjing.bean.response.home.DistrictBean;
import com.runjing.bean.response.home.def.CityAdapter;
import com.runjing.bean.response.home.def.CityBean;
import com.runjing.common.AppMethod;
import com.runjing.utils.location.LocalUtil;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class OpenCityItemAfapter extends RecyclerView.Adapter<OpenCityItemAfapter.CityHolder>   {
    private TitleBarActivity mActivity;
    private List<DistrictBean.DataBean.ChildrenListBean> citys;

    public OpenCityItemAfapter(TitleBarActivity outActivity) {
        this.mActivity = outActivity;
        citys = new ArrayList<>();
    }

    public void setData(List<DistrictBean.DataBean.ChildrenListBean> data) {
        if (data != null && data.size() > 0) {
            this.citys = data;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public OpenCityItemAfapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OpenCityItemAfapter.CityHolder(LayoutInflater.from(mActivity).inflate(R.layout.layout_item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OpenCityItemAfapter.CityHolder holder, int position) {
        holder.tv_city.setText(AppMethod.setDefault(citys.get(position).getName()));
        holder.tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalUtil.city = citys.get(position).getName();
                mActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return citys.size() > 0 ? citys.size() : 0;
    }

    class CityHolder extends RecyclerView.ViewHolder {
        private TextView tv_city;
        public CityHolder(@NonNull View itemView) {
            super(itemView);
            tv_city = itemView.findViewById(R.id.lay_item_tv_);
        }
    }
}
