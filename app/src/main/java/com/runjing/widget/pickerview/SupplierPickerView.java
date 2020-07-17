package com.runjing.widget.pickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.runjing.widget.pickerview.listener.OnItemClickedListener;
import com.runjing.widget.pickerview.widget.BasePickerView;
import com.runjing.widget.pickerview.widget.SupplierOptions;
import com.runjing.wineworld.R;

import java.util.ArrayList;


/**
 * 条件选择器
 * Created by Sai on 15/11/22.
 */
public class SupplierPickerView<T> extends BasePickerView implements OnItemClickedListener {

    private Context mContext;

    private OnOptionsClickedListener onOptionsClickListener;
    private SupplierOptions<T> mWheelOptions;
    private TextView mTxtTitle;
    private ArrayList<T> optionsItems;

    public SupplierPickerView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.pickerview_supplier, contentContainer);
        mWheelOptions = new SupplierOptions<>(findViewById(R.id.item_supplier));
        mTxtTitle = (TextView) findViewById(R.id.item_tv_title);
    }

    /**
     * 设置一级数据
     */
    public SupplierPickerView setPicker(ArrayList<T> optionsItems) {
        this.optionsItems = optionsItems;
        mWheelOptions.setPicker(optionsItems);
        mWheelOptions.getWheelView().setOnItemClickedListener(this);
        return this;
    }

    /**
     * 设置选中的item位置
     *
     * @param option1 位置
     */
    public void setSelectOptions(int option1) {
        mWheelOptions.setCurrentItems(option1, 0, 0);
    }

    public void setSelectOptions(int option1, int option2) {
        mWheelOptions.setCurrentItems(option1, option2, 0);
    }

    public void setSelectOptions(int option1, int option2, int option3) {
        mWheelOptions.setCurrentItems(option1, option2, option3);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     */
    public void setLabels(String label1) {
        mWheelOptions.setLabels(label1);
    }

    /**
     * 设置是否循环滚动
     */
    public void setCyclic(boolean cyclic) {
        mWheelOptions.setCyclic(cyclic);
    }

    /**
     * 设置头部背景颜色
     */
    public void setHeadBackgroundColor(int color) {
        mTxtTitle.setBackgroundColor(color);
    }

    /**
     * 设置标题
     */
    public SupplierPickerView setTitle(String title) {
        mTxtTitle.setText(title);
        return this;
    }

    /**
     * 设置标题颜色
     */
    public void setTitleColor(int resId) {
        mTxtTitle.setTextColor(resId);
    }

    /**
     * 设置标题大小
     */
    public void setTitleSize(float size) {
        mTxtTitle.setTextSize(size);
    }

    /**
     * 设置滚动文字大小
     */
    public SupplierPickerView setTextSize(float size) {
        mWheelOptions.setTextSize(size);
        return this;
    }

    /**
     * @param size
     */
    public SupplierPickerView setLineSpacing(float size) {
        mWheelOptions.setLineSpacing(size);
        return this;
    }

    public interface OnOptionsClickedListener {
        void onOptionsClick(String s);
    }

    public SupplierPickerView setOnOptionsClickedListener(OnOptionsClickedListener onOptionsClickListener) {
        this.onOptionsClickListener = onOptionsClickListener;
        return this;
    }

    @Override
    public void onItemClicked(int index) {
        if (onOptionsClickListener != null)
            onOptionsClickListener.onOptionsClick(optionsItems.get(index).toString());
    }
}
