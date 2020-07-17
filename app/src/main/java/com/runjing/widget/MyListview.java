package com.runjing.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by xiaoyu on 2016/5/18.
 * Describe：解决 listview  ExpandableListView 嵌套之间冲突
 */
public class MyListview extends ListView {
    public MyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListview(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        super.setAdapter(adapter);

        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                setHeight();
            }
        });

//        setHeight();///有问题 遇到再解决  待定！
    }

    private void setHeight() {
        ListAdapter adapter = getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, this);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = this.getLayoutParams();
        int dividerHeights = getDividerHeight()
                * (adapter.getCount() == 0 ? 0 : adapter.getCount() - 1);
        params.height = totalHeight + dividerHeights;
        setLayoutParams(params);
    }

}
