package com.runjing.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

import com.alibaba.fastjson.JSON;
import com.runjing.base.BaseRequest;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.bean.response.search.SearchHotBean;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.common.RJBaseUrl;
import com.runjing.http.ApiServices;
import com.runjing.http.net.BaseSubscriber;
import com.runjing.http.net.ExceptionHandle;
import com.runjing.http.net.RetrofitClient;
import com.runjing.utils.KeyBoardUtil;
import com.runjing.utils.RecyclerViewItemDecoration;
import com.runjing.utils.StatusBarUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.widget.wrap.WrapView;
import com.runjing.wineworld.R;
import com.socks.library.KLog;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.DensityUtils;
import org.runjing.rjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends TitleBarFragment implements AppMethod.PopBackListener, TextWatcher {
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
    @BindView(id = R.id.txt_search_click, click = true)
    private TextView txt_search_click;//搜索按钮
    @BindView(id = R.id.layout_search_no_goods)
    private LinearLayout layout_search_no_goods; //搜索无内容
    @BindView(id = R.id.scroll_search)
    private ScrollView scrollView;
    @BindView(id = R.id.recyview_search_goods)
    private RecyclerView recyview_search_goods;
    @BindView(id = R.id.img_ljx, click = true)
    private ImageView img_ljx; //垃圾箱
    @BindView(id = R.id.img_search_back, click = true)
    private ImageView img_search_back; //垃圾箱

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        StatusBarUtil.setColor(outsideAty, getResources().getColor(R.color.color_ffffff));
        StatusBarUtil.setDarkMode(outsideAty);
        KeyBoardUtil.init(outsideAty);
        return inflater.inflate(R.layout.layout_search_index, null);
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        edit_search_goods.addTextChangedListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_ljx:
                AppMethod.showMsg(outsideAty, "", "确认删除全部搜索记录吗?", this);
                break;
            case R.id.txt_search_click:
                Bundle bundle = new Bundle();
                if (!StringUtils.isEmpty(edit_search_goods.getText().toString())) {
                    bundle.putString(Appconfig.DATA_KEY, edit_search_goods.getText().toString());
                }
                AppMethod.postShowWith(outsideAty, SimpleBackPage.SearchGoods, bundle);
                saveHistory(edit_search_goods.getText().toString());
                break;
            case R.id.img_search_back:
                finish();
                break;
        }
    }

    public void getData() {
        RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl).execute(
                RetrofitClient.getInstance(outsideAty, RJBaseUrl.BaseUrl)
                        .create(ApiServices.class)
                        .searchHistory(ApiServices.MyRequestBody.createBody(new BaseRequest())),
                new BaseSubscriber<SearchHotBean>(outsideAty) {

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("Lyk", e.getMessage());

                    }

                    @Override
                    public void onNext(SearchHotBean response) {
                        KLog.i(response.getData());
                        setData(response);
                    }
                });
    }

    private void setData(SearchHotBean response) {
        SearchHotBean history = getHistory();
        if (history != null) {
            if (history.getData() != null && history.getData().size() > 0) {
                svLayout(outsideAty, history.getData(), wrapView);
            } else {
                ll_bg_history.setVisibility(View.GONE);
            }
        } else {
            ll_bg_history.setVisibility(View.GONE);
        }
        if (response != null) {
            if (response.getData() != null && response.getData().size() > 0) {
                svLayoutHot(outsideAty, response.getData(), wrapViewHot);
            } else {
                ll_bg_remen.setVisibility(View.GONE);
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
                        Bundle bundle = new Bundle();
                        if (!StringUtils.isEmpty(tv_search_tag_name.getText().toString())) {
                            bundle.putString(Appconfig.DATA_KEY, list.get(finalI));
                        }
                        AppMethod.postShowWith(outsideAty, SimpleBackPage.SearchGoods, bundle);
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
                        Bundle bundle = new Bundle();
                        if (!StringUtils.isEmpty(tv_search_tag_name.getText().toString())) {
                            bundle.putString(Appconfig.DATA_KEY, list.get(finalI));
                        }
                        AppMethod.postShowWith(outsideAty, SimpleBackPage.SearchGoods, bundle);
                        saveHistory(tv_search_tag_name.getText().toString());
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    @Override
    public void OnSuccess() {
        ll_bg_history.setVisibility(View.GONE);
        svLayout(outsideAty, getHistory().getData(), wrapView);
        MMKVUtil.getInstance().clear(Appconfig.SEARCH_HISTORY);
        ViewInject.toast("删除成功");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() != 0 && s.toString().trim().length() != 0) {
            scrollView.setVisibility(View.GONE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 保存搜索历史
     *
     * @param name
     */
    private void saveHistory(String name) {
        String history = MMKVUtil.getInstance().decodeString(Appconfig.SEARCH_HISTORY);
        SearchHotBean bean;
        if (TextUtils.isEmpty(history)) {
            bean = new SearchHotBean();
            List<String> list = new ArrayList<>();
            list.add(name);
            bean.setData(list);
        } else {
            bean = JSON.parseObject(history, SearchHotBean.class);
            List<String> list = bean.getData();
            list.add(name);
        }
        MMKVUtil.getInstance().encode(Appconfig.SEARCH_HISTORY, JSON.toJSONString(bean));
    }

    /**
     * 获取搜索历史数据
     *
     * @return
     */
    private SearchHotBean getHistory() {
        return JSON.parseObject(MMKVUtil.getInstance().decodeString(Appconfig.SEARCH_HISTORY), SearchHotBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        SearchHotBean history = getHistory();
        if (history != null) {
            if (history.getData() != null && history.getData().size() > 0) {
                svLayout(outsideAty, history.getData(), wrapView);
            } else {
                ll_bg_history.setVisibility(View.GONE);
            }
        }
    }
}
