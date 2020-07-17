package com.runjing.widget.pickerview.widget;

import android.view.View;

import com.runjing.widget.pickerview.adapter.ArrayWheelAdapter;
import com.runjing.widget.pickerview.widget.wheelview.WheelView;
import com.runjing.wineworld.R;

import java.util.ArrayList;

public class SupplierOptions<T> {

    private View view;
    private WheelView wv_option1;
    private ArrayList<T> mOptions1Items;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public SupplierOptions(View view) {
        super();
        this.view = view;
        setView(view);
    }

    public void setPicker(ArrayList<T> optionsItems) {
        this.mOptions1Items = optionsItems;
        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        wv_option1 = (WheelView) view.findViewById(R.id.item_supplier);
        wv_option1.setAdapter(new ArrayWheelAdapter(mOptions1Items, len));// 设置显示数据
        wv_option1.setCurrentItem(0);// 初始化时显示的数据
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     */
    public void setLabels(String label1) {
        if (label1 != null)
            wv_option1.setLabel(label1);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wv_option1.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
     *
     * @return 索引数组
     */
    public int[] getCurrentItems() {
        int[] currentItems = new int[3];
        currentItems[0] = wv_option1.getCurrentItem();
        return currentItems;
    }

    public void setCurrentItems(int option1, int option2, int option3) {
        wv_option1.setCurrentItem(option1);
    }
    /**
     * 设置滚动文字大小
     */
    public void setTextSize(float size) {
        if (wv_option1 != null)
            wv_option1.setTextSize(size);
    }

    public void setLineSpacing(float value){
        if (wv_option1 != null) wv_option1.setLineSpacingMultiplier(value);
    }

    public WheelView getWheelView() {
        return wv_option1;
    }
}
