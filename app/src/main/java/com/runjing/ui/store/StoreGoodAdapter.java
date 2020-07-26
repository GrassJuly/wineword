package com.runjing.ui.store;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runjing.base.SimpleBackPage;
import com.runjing.bean.response.home.CityAdapter;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.response.home.ProvinceBean;
import com.runjing.bean.response.home.StoreBean;
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
public class StoreGoodAdapter extends RecyclerView.Adapter<StoreGoodAdapter.GoodHolder> {
    private Activity context;
    private HomeBean response;
    private List<GoodBean> data;

    public StoreGoodAdapter(Activity context) {
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
    public StoreGoodAdapter.GoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_good, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreGoodAdapter.GoodHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
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
                GlideUtils.getInstance().displayImageCenter(iv_good, goods.get(position).getImage(), iv_good.getContext(), R.mipmap.ic_launcher);
                //这个后期根据后台切图动态删除， 我找的图太大 尺寸不对
                setImageWH(iv_good);
                tv_name.setText(AppMethod.setDefault(goods.get(position).getName()));
                tv_desc.setText(AppMethod.setDefault(goods.get(position).getDesc()));
                tv_price.setText(AppMethod.setDefault(goods.get(position).getPrice()));
                tv_favprice.setText(AppMethod.setDefault(goods.get(position).getFavorablePrice()));
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


}
