package com.runjing.ui.search;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.bean.response.home.def.GoodBean;
import com.runjing.bean.response.home.def.GoodTagBean;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.widget.wrap.WrapView;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;
import org.runjing.rjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends TitleBarFragment implements AppMethod.PopBackListener {
    @BindView(id = R.id.wrap_view)
    private WrapView wrapView;  //历史搜索
    @BindView(id = R.id.ll_bg_history)
    private LinearLayout ll_bg_history;  //历史搜索
    @BindView(id = R.id.wrap_view1)
    private WrapView wrapViewHot; //热门搜索
    @BindView(id = R.id.ll_bg_remen)
    private LinearLayout ll_bg_remen; //热门搜索
    @BindView(id = R.id.edit_search_goods)
    private EditText edit_search_goods; //搜索框
    @BindView(id = R.id.txt_search_click,click = true)
    private TextView txt_search_click;//搜索按钮
    @BindView(id = R.id.layout_search_no_goods)
    private LinearLayout layout_search_no_goods; //搜索无内容
    @BindView(id = R.id.scroll_search)
    private ScrollView scrollView;
    @BindView(id = R.id.recyview_search_goods)
    private RecyclerView recyview_search_goods;
    @BindView(id = R.id.img_ljx,click = true)
    private ImageView img_ljx; //垃圾箱
    @BindView(id = R.id.img_search_back,click = true)
    private ImageView img_search_back; //垃圾箱
    List<String> listHistory,listHot;
    private List<GoodBean> goodsList;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_search_index, null);
    }



    @Override
    protected void initData() {
        super.initData();
        listHistory = new ArrayList<>();
        listHot = new ArrayList<>();
        String s = "22222222";
        String a = "大家说大家撒的健康撒娇的快乐是";
        String d = "白酒";
        String ds = "老板";
        String s1 = "22222";
        String a1 = "大家说大家撒";
        String d1 = "白酒ds";
        String ds1 = "老板dsds";
        listHistory.add(s);
        listHistory.add(a);
        listHistory.add(d);
        listHistory.add(ds);
        listHistory.add(s1);
        listHistory.add(a1);
        listHistory.add(d1);
        listHistory.add(ds1);
        listHot.add(s);
        listHot.add(a);
        listHot.add(d);
        listHot.add(ds);
        listHot.add(s1);
        listHot.add(a1);
        listHot.add(d1);
        listHot.add(ds1);
        if(listHistory!=null&&listHistory.size()>0) {
            svLayout(outsideAty, listHistory, wrapView);
        }else{
            ll_bg_history.setVisibility(View.GONE);
        }
        if(listHot!=null&&listHistory.size()>0) {
            svLayoutHot(outsideAty, listHot, wrapViewHot);
        }else{
            ll_bg_remen.setVisibility(View.GONE);
        }
    }



    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        edit_search_goods.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.img_ljx:
                AppMethod.showMsg(outsideAty,"","确认删除全部搜索记录吗?",this);
                break;
            case  R.id.txt_search_click:
                Bundle bundle = new Bundle();
                if(!StringUtils.isEmpty(edit_search_goods.getText().toString())) {
                    bundle.putString(Appconfig.DATA_KEY, edit_search_goods.getText().toString());
                }
                AppMethod.postShowWith(outsideAty, SimpleBackPage.SearchGoods,bundle);
                break;
            case  R.id.img_search_back:
                finish();
                break;
        }
    }

    /**
     * 动态布局
     *
     * @param context     上下文
     * @param list        内容集合
     * @param lay_gallery 自动换行的view
     */
    public void svLayout(final Context context, final List<String> list, WrapView lay_gallery) {//List<LinkedTreeMap<String, Object>> list
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.layout_wrapview_item, lay_gallery, false);//添加的view,这里很简单就是一个TextView
                final TextView tv_search_tag_name = (TextView) view.findViewById(R.id.textWrap);
                tv_search_tag_name.setText(list.get(i));
                final int finalI = i;
                tv_search_tag_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //处理点击事件
                    }
                });


                lay_gallery.addView(view);
            }
        }
    }

    /**
     * 动态布局
     *
     * @param context     上下文
     * @param list        内容集合
     * @param lay_gallery 自动换行的view
     */
    public void svLayoutHot(final Context context, final List<String> list, WrapView lay_gallery) {//List<LinkedTreeMap<String, Object>> list
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.layout_wv_item, lay_gallery, false);//添加的view,这里很简单就是一个TextView
                final TextView tv_search_tag_name = (TextView) view.findViewById(R.id.textWrap1);
                tv_search_tag_name.setText(list.get(i));
                final int finalI = i;
                tv_search_tag_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    @Override
    public void OnSuccess() {
        ll_bg_history.setVisibility(View.GONE);
        listHistory.clear();
        svLayout(outsideAty, listHistory, wrapView);
        ViewInject.toast("删除成功");
    }



    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("1111","sssssssssssssssssssssssssss");
            if (s.length() != 0 && s.toString().trim().length() != 0) {
                scrollView.setVisibility(View.GONE);
                getGoods(s.toString());
                buildAdapter();
            } else {
                scrollView.setVisibility(View.VISIBLE);
                goodsList.clear();
                buildAdapter();
            }
        }
    }

    private void buildAdapter(){
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        if(mManager==null) {
            mManager = new LinearLayoutManager(getActivity());
            recyview_search_goods.setHasFixedSize(false);
            recyview_search_goods.setLayoutManager(mManager);
            recyview_search_goods.setNestedScrollingEnabled(false);
            recyview_search_goods.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,
                    getResources().getColor(R.color.color_eeeeee), DensityUtils.dip2dp(getActivity(), 1), 0, 0));
        }
        SearchItemAdapter  searchItemAdapter = new SearchItemAdapter(getActivity(), goodsList);
        recyview_search_goods.setAdapter(searchItemAdapter);
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


}
