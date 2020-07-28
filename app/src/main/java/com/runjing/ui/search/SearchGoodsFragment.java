package com.runjing.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.home.GoodBean;
import com.runjing.bean.response.home.GoodTagBean;
import com.runjing.bean.response.home.HomeBean;
import com.runjing.bean.test.HomeData;
import com.runjing.common.Appconfig;
import com.runjing.utils.SpacesItemDecoration;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.runjing.utils.SpacesItemDecoration.STAGGEREDGRIDLAYOUT;

/**
 * 搜索商品结果列表
 */
public class SearchGoodsFragment  extends TitleBarFragment {
    private List<GoodBean> goodsList; //商品列表
    @BindView(id = R.id.recyview_search_goods)
    private RecyclerView recyview_search_goods;
    @BindView(id = R.id.scroll_search)
    private ScrollView scrollView;
    @BindView(id = R.id.edit_search_goods)
    private EditText edit_search_goods;
    @BindView(id = R.id.img_search_back,click = true)
    private ImageView img_search_back;
    private SearchGoodsAdapter goodsAdapter;
    private String keyboard;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        bundle = outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY);
        if(bundle!=null) {
            keyboard = bundle.getString(Appconfig.DATA_KEY);
        }
        return inflater.inflate(R.layout.layout_search_index, null);
    }

    @Override
    protected void initData() {
        super.initData();
        edit_search_goods.setText(keyboard);
        getGoods(keyboard);
    }


    public void getGoods(String keyboard){
        goodsList = new ArrayList<>();
        GoodBean good = new GoodBean();
        good.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1349551846,2489686808&fm=26&gp=0.jpg");
        good.setName("五粮液酿身佳品52度浓香型");
        good.setDesc("白酒500ml/瓶");
        good.setPrice("¥259.00");
        good.setFavorablePrice("¥259.00");
        good.setState(1);
        List<GoodTagBean> tags = new ArrayList<>();
        GoodTagBean tag = new GoodTagBean();
        tag.setTag("买一赠一");
        GoodTagBean tag1 = new GoodTagBean();
        tag.setTag("直降");
        tags.add(tag);
        tags.add(tag1);
        tags.add(tag1);
        good.setTags(tags);

        GoodBean good1 = new GoodBean();
        good1.setImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1553171949,3079781356&fm=26&gp=0.jpg");
        good1.setName("五粮液酿身佳品52度浓香型");
        good1.setDesc("白酒500ml/瓶");
        good1.setPrice("¥259.00");
        good1.setFavorablePrice("¥259.00");
        good1.setState(1);
        List<GoodTagBean> tags2 = new ArrayList<>();
        GoodTagBean tag2 = new GoodTagBean();
        tag2.setTag("买一赠一");
        GoodTagBean tag22 = new GoodTagBean();
        tag22.setTag("直降");
        tags2.add(tag2);
        tags2.add(tag22);
        good1.setTags(tags2);

        GoodBean good2 = new GoodBean();
        good2.setImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1902839288,3768379962&fm=26&gp=0.jpg");
        good2.setName("五粮液酿身佳品52度浓香型");
        good2.setDesc("白酒500ml/瓶");
        good2.setPrice("¥259.00");
        good2.setFavorablePrice("¥259.00");
        good2.setState(1);
        List<GoodTagBean> tags3 = new ArrayList<>();
        GoodTagBean tag3 = new GoodTagBean();
        tag3.setTag("买一赠一");
        GoodTagBean tag33 = new GoodTagBean();
        tag.setTag("直降");
        tags3.add(tag3);
        tags3.add(tag33);
        good2.setTags(tags3);

        GoodBean good3 = new GoodBean();
        good3.setState(1);
        good3.setImage("http://a3.att.hudong.com/55/22/20300000929429130630222900050.jpg");
        good3.setName("五粮液酿身佳品52度浓香型");
        good3.setDesc("白酒500ml/瓶");
        good3.setPrice("¥259.00");
        good3.setFavorablePrice("¥259.00");
        List<GoodTagBean> tags4 = new ArrayList<>();
        GoodTagBean tag4 = new GoodTagBean();
        tag4.setTag("买一赠一");
        GoodTagBean tag44 = new GoodTagBean();
        tag44.setTag("直降");
        tags4.add(tag4);
        tags4.add(tag44);
        good3.setTags(tags4);

        GoodBean good4 = new GoodBean();
        good4.setImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3714408895,354969059&fm=26&gp=0.jpg");
        good4.setName("五粮液酿身佳品52度浓香型");
        good4.setDesc("白酒500ml/瓶");
        good4.setPrice("¥259.00");
        good4.setFavorablePrice("¥259.00");
        List<GoodTagBean> tags5 = new ArrayList<>();
        GoodTagBean tag5 = new GoodTagBean();
        tag5.setState(0);
        tag5.setTag("买一赠一");
        GoodTagBean tag55 = new GoodTagBean();
        tag55.setTag("直降");
        tags5.add(tag);
        tags5.add(tag5);
        tags5.add(tag55);
        good4.setTags(tags);
        good4.setState(0);
        goodsList.add(good);
        goodsList.add(good1);
        goodsList.add(good2);
        goodsList.add(good3);
        goodsList.add(good4);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        scrollView.setVisibility(View.GONE);
        goodsAdapter = new SearchGoodsAdapter(outsideAty,goodsList);
        recyview_search_goods.setHasFixedSize(false);
        recyview_search_goods.setNestedScrollingEnabled(false);
        recyview_search_goods.setLayoutManager(new StaggeredGridLayoutManager(Appconfig.TAG_TWO, StaggeredGridLayoutManager.VERTICAL));
        recyview_search_goods.addItemDecoration(new SpacesItemDecoration(DensityUtils.dip2dp(getActivity(), 7), STAGGEREDGRIDLAYOUT));
        recyview_search_goods.setAdapter(goodsAdapter);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.img_search_back:
                finish();
                break;
        }
    }
}
