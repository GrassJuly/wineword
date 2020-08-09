package com.runjing.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runjing.bean.response.home.GoodBean;
import com.runjing.wineworld.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : zlf
 * date    : 2019/9/6
 * github  : https://github.com/mamumu
 * blog    : https://www.jianshu.com/u/281e9668a5a6
 * desc    :
 */
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagHolder> {

    private List<GoodBean.DataBean.ListBean.SkuPromotionResultBean.PromoDescListBean> tags;

    public TagAdapter(List<GoodBean.DataBean.ListBean.SkuPromotionResultBean.PromoDescListBean> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public TagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TagHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TagHolder holder, int position) {
        //后期可以放图片自行决定， 后来人看你了。
        if (tags != null) {
            int type = tags.get(position).getPromoSubType();
            switch (type) {
                case 100:
                    holder.tv_tag.setText("直降");
                    break;
                case 101:
                    holder.tv_tag.setText("单品折扣");
                    break;
                case 102:
                    holder.tv_tag.setText("买赠");
                    break;
                case 200:
                    holder.tv_tag.setText("买赠");
                    break;
                case 300:
                    holder.tv_tag.setText("满减");
                    break;
                case 301:
                    holder.tv_tag.setText("满折");
                    break;
                case 302:
                    holder.tv_tag.setText("满赠");
                    break;
                case 307:
                    holder.tv_tag.setText("几元几件");
                    break;
                case 1001:
                    holder.tv_tag.setText("酒币抵现");
                    break;
                case 1002:
                    holder.tv_tag.setText("买送酒币");
                    break;
                case 1003:
                    holder.tv_tag.setText("PLUS专享");
                    break;
                default:
                    //plus会员
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return tags == null ? 0 : tags.size();
    }

    class TagHolder extends RecyclerView.ViewHolder {
        private TextView tv_tag;

        public TagHolder(@NonNull View itemView) {
            super(itemView);
            tv_tag = itemView.findViewById(R.id.lay_tv_tag);
        }
    }
}
