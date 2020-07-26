package com.runjing.ui.good;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.runjing.bean.response.good.DiscountBean;
import com.runjing.bean.response.good.GoodDetailBean;
import com.runjing.common.AppMethod;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.23 11:12.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.23 11:12.
 * @Remark:
 */
public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.Holder> {

    private Context context;
    private List<DiscountBean> data;

    public DiscountAdapter(Context context) {
        this.context = context;
        if (data == null) data = new ArrayList<>();
    }

    public DiscountAdapter setData(GoodDetailBean response) {
        if (response != null) {
            data = getDisData(response);
            notifyDataSetChanged();
        }
        notifyDataSetChanged();
        return this;
    }

    /**
     * 数据封装
     *
     * @param response
     * @return
     */
    public List<DiscountBean> getDisData(GoodDetailBean response) {
        List<DiscountBean> disList = new ArrayList<>();
        return disList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("走了吗？？？  onCreateViewHolder " + parent);
        return new Holder(LayoutInflater.from(context).inflate(R.layout.layout_goode_discount, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        holder.tv_tag.setText(AppMethod.setDefault(data.get(position).getTagName()));
//        holder.tv_value.setText(AppMethod.setDefault(data.get(position).getName()));
//        if (data.get(position).isArrow()) {
//            holder.iv_right.setVisibility(View.VISIBLE);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ViewInject.showCenterToast(holder.iv_right.getContext(), "进入门店");
//                }
//            });
//        } else {
//            holder.iv_right.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
//        return data == null ? 10 : data.size();
        return 7;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView tv_tag;
        private TextView tv_value;
        private ImageView iv_right;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.lay_tv_tag);
            tv_value = itemView.findViewById(R.id.lay_tv_tagVal);
            iv_right = itemView.findViewById(R.id.lay_iv_right);
        }
    }
}
