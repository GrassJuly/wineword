package com.runjing.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runjing.bean.response.home.DistrictBean;
import com.runjing.common.AppMethod;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.19 05:38.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.19 05:38.
 * @Remark:
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder>  {

    private Context context;
    private List<DistrictBean.DataBean.ChildrenListBean> citys;

    public CityAdapter(Context context) {
        this.context = context;
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
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        holder.tv_city.setText(AppMethod.setDefault(citys.get(position).getName()));
        holder.tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewInject.showCenterToast(holder.tv_city.getContext(), citys.get(position).getName());
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
