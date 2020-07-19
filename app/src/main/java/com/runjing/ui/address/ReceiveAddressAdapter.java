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

import com.bumptech.glide.Glide;
import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.address.AddressBean;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.response.home.StoreBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.ui.home.HomeAdapter;
import com.runjing.wineworld.R;

import java.util.ArrayList;
import java.util.List;

import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_CITY;
import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_GOOD;
import static com.runjing.bean.response.home.HomeBean.TYPE_ITEM_STORE;

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


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mType == 1) {
            return new ReceiveAddressAdapter.AddressHolder(LayoutInflater.from(context).inflate(R.layout.layout_rec_address_item, null));
        } else {
            return new ReceiveAddressAdapter.NearAddressHolder(LayoutInflater.from(context).inflate(R.layout.layout_near_address_item, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mType == 1) {
            ((AddressHolder) holder).setData(mList, position);
        } else {
            ((NearAddressHolder)holder).setData(mList, position);
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

                tv_name.setText(AppMethod.isEntity(goods.get(position).getName()));
                if ("1".equals(AppMethod.isEntity(goods.get(position).getMark()))) {
                    btnMark.setText("公司");
                } else if ("2".equals(AppMethod.isEntity(goods.get(position).getMark()))) {
                    btnMark.setText("家");
                } else {
                    btnMark.setVisibility(View.GONE);
                }

                tv_address.setText(AppMethod.isEntity(goods.get(position).getAddress()));
                tv_phone.setText(AppMethod.isEntity(goods.get(position).getPhone()));
                lay_address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppMethod.postShowWith(context, SimpleBackPage.Home);
                    }
                });
            }
        }
    }

    public class NearAddressHolder extends RecyclerView.ViewHolder {
        private TextView tv_near_address;
        private LinearLayout lay_near_address;


        public NearAddressHolder(@NonNull View itemView) {
            super(itemView);

            tv_near_address = itemView.findViewById(R.id.tv_near_address);
            lay_near_address = itemView.findViewById(R.id.lay_near);
        }

        public void setData(List<AddressBean> goods, int position) {
            if (goods != null && goods.size() > 0) {

                tv_near_address.setText(AppMethod.isEntity(goods.get(position).getAddress()));
                lay_near_address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppMethod.postShowWith(context, SimpleBackPage.Home);
                    }
                });
            }
        }
    }

}
