package com.runjing.ui.address;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.address.AddressBean;
import com.runjing.bean.response.home.GoodBean;

import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;

import com.runjing.wineworld.R;

import java.util.List;


/**
 * 选择地址定位adapter
 */
public class ReceiveAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private GoodBean response;
    private List<AddressBean> mList;
    private int mType;

    public ReceiveAddressAdapter(Activity context, List<AddressBean> data, int type) {
        this.context = context;
        mList = data;
        mType = type;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mType == Appconfig.TAG_ONE) {
            return new ReceiveAddressAdapter.AddressHolder(LayoutInflater.from(context).inflate(R.layout.layout_rec_address_item, null));
        } else if(mType == Appconfig.TAG_TWO){
            return new ReceiveAddressAdapter.NearAddressHolder(LayoutInflater.from(context).inflate(R.layout.layout_near_address_item, null));
        }else if(mType == Appconfig.TAG_THREE){
            return new ReceiveAddressAdapter.SearchAddressHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_result_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (mType){
            case  Appconfig.TAG_ONE:
                ((AddressHolder) holder).setData(mList, position);
                break;
            case  Appconfig.TAG_TWO:
                ((NearAddressHolder)holder).setData(mList, position);
                break;
            case  Appconfig.TAG_THREE:
                ((SearchAddressHolder) holder).setData(mList, position);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        //根据接口返回数据调整

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : Appconfig.TAG_ZERO;

    }

    /**
     * 收货地址
     */
    public class AddressHolder extends RecyclerView.ViewHolder {
        private Button btnMark;
        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_phone;
        private LinearLayout lay_address;


        public AddressHolder(@NonNull View itemView) {
            super(itemView);

            btnMark = itemView.findViewById(R.id.btn_mark);
            tv_name = itemView.findViewById(R.id.tv_rece_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_phone = itemView.findViewById(R.id.tv_rece_phone);
            lay_address = itemView.findViewById(R.id.lay_address);
        }

        public void setData(List<AddressBean> goods, int position) {
            if (goods != null && goods.size() > 0) {

                tv_name.setText(AppMethod.setDefault(goods.get(position).getName()));
                if ("1".equals(AppMethod.setDefault(goods.get(position).getMark()))) {
                    btnMark.setText("公司");
                } else if ("2".equals(AppMethod.setDefault(goods.get(position).getMark()))) {
                    btnMark.setText("家");
                } else {
                    btnMark.setVisibility(View.GONE);
                }

                tv_address.setText(AppMethod.setDefault(goods.get(position).getAddress()));
                tv_phone.setText(AppMethod.setDefault(goods.get(position).getPhone()));

            }
        }
    }

    /**
     * 附近地址
     */
    public class NearAddressHolder extends RecyclerView.ViewHolder {
        private TextView tv_near_address,tv_select_address;
        private LinearLayout lay_near_address;


        public NearAddressHolder(@NonNull View itemView) {
            super(itemView);

            tv_near_address = itemView.findViewById(R.id.tv_near_address);

            lay_near_address = itemView.findViewById(R.id.lay_near);

        }

        public void setData(List<AddressBean> goods, int position) {
            if (goods != null && goods.size() > 0) {

                tv_near_address.setText(AppMethod.setDefault(goods.get(position).getAddress()));
//                lay_near_address.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }
        }
    }

    /**
     * 搜索地址
     */
    public class SearchAddressHolder extends RecyclerView.ViewHolder {
        private TextView tv_near_address,tv_select_address;
        private LinearLayout lay_near_address;


        public SearchAddressHolder(@NonNull View itemView) {
            super(itemView);

            tv_near_address = itemView.findViewById(R.id.tv_search_name);
            tv_select_address = itemView.findViewById(R.id.tv_select_result_address);
            lay_near_address = itemView.findViewById(R.id.lay_search_res);
        }

        public void setData(List<AddressBean> goods, int position) {
            if (goods != null && goods.size() > 0) {
                tv_select_address.setText(AppMethod.setDefault(goods.get(position).getPoiAddress()));
                tv_near_address.setText(AppMethod.setDefault(goods.get(position).getAddress()));
                lay_near_address.setOnClickListener(v -> AppMethod.postShowWith(context, SimpleBackPage.Home));
            }
        }
    }

}
