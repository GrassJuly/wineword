package com.runjing.ui.search;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runjing.base.TitleBarFragment;
import com.runjing.common.Appconfig;
import com.runjing.widget.wrap.WrapView;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends TitleBarFragment {
    @BindView(id = R.id.wrap_view)
    WrapView wrapView;
    @BindView(id = R.id.wrap_view1)
    WrapView wrapViewHot;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_search_index, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = Appconfig.DEFAULT_VALUE_LONG;
        actionBarRes.titleBarColor = R.color.color_ffffff;
    }

    @Override
    protected void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        String s = "22222222";
        String a = "大家说大家撒的健康撒娇的快乐是";
        String d = "白酒";
        String ds = "老板";
        String s1 = "22222";
        String a1 = "大家说大家撒";
        String d1 = "白酒ds";
        String ds1 = "老板dsds";
        list.add(s);
        list.add(a);
        list.add(d);
        list.add(ds);
        list.add(s1);
        list.add(a1);
        list.add(d1);
        list.add(ds1);
        svLayout(outsideAty, list, wrapView);
        svLayoutHot(outsideAty, list, wrapViewHot);
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
                        //处理点击事件
                    }
                });


                lay_gallery.addView(view);
            }
        }
    }
}
