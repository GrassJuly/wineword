package com.runjing.utils;

import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：限制edittext只能输入小数及小数点后两位   以及金额的3位
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class EditInputFilter implements InputFilter {

    private EditText editText;
    private int isMoney = -1; //小数点后的位数
    /**
     * 最大数字
     */
    public static final double MAX_VALUE = 9999999999.99;
    /**
     * 小数点后的数字的位数
     */
    public static final int PONTINT_LENGTH = 2;
    /**
     * 小数点后的数字的位数   默认3位
     */
    public static final int PONTINT_LENGTH_MONEY = 3;
    Pattern p;

    public EditInputFilter(EditText editText) {
        p = Pattern.compile("[0-9]*");   //  ^\d{1,8}(\.\d{1,2})?$   ^[0-9]{1,8}([.][0-9]{1,5})?$
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    /*
    * isMoney  传  EditInputFilter.ISMONEY  为了金额保留3位小数
    * */
    public EditInputFilter(EditText editText, int isMoney) {
        this.isMoney = isMoney;
        p = Pattern.compile("[0-9]*");   //除数字外的其他的   这里会限制位数
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.editText = editText;
    }

    /**
     * source    新输入的字符串
     * start    新输入的字符串起始下标，一般为0
     * end    新输入的字符串终点下标，一般为source长度-1
     * dest    输入之前文本框内容
     * dstart    原内容起始坐标，一般为0
     * dend    原内容终点坐标，一般为dest长度-1
     */

    @Override
    public CharSequence filter(CharSequence src, int start, final int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext = dest.toString();
        System.out.println(oldtext);
        System.out.println(src.toString());
        System.out.println(start);
        System.out.println(dend);


        if (start == 0 && dend == 0 && ".".equals(src)) {
            return "";
        }

        //验证删除等按键
        if ("".equals(src.toString())) {
            return null;
        }

        //验证非数字或者小数点的情况
        Matcher m = p.matcher(src);
        if (oldtext.contains(".")) {
            //已经存在小数点的情况下，只能输入数字
            if (!m.matches()) {
                return null;
            }
        } else {
            //未输入小数点的情况下，可以输入小数点和数字
            if (!m.matches() && !src.equals(".")) {
                return null;
            }
        }
        //验证输入金额的大小
        if (!src.toString().equals("")) {
            double dold = Double.parseDouble(oldtext + src.toString());
            if (dold > MAX_VALUE ) {
                return dest.subSequence(dstart, dend);
            }
            else if (dold == MAX_VALUE) {
                if (src.toString().equals(".")) {
                    return dest.subSequence(dstart, dend);
                }
            }
        }
        //验证小数位精度是否正确
        if (oldtext.contains(".")) {
            int index = oldtext.indexOf(".");
            int len = dend - index;
            //如果是金额,保留3位小数
            if (isMoney >=0) {
                //小数位只能3位

                if (len > isMoney) {
                    CharSequence newText = dest.subSequence(dstart, dend);
                    return newText;
                }
            } else {

                //小数位只能2位
                if (len > PONTINT_LENGTH) {
                    CharSequence newText = dest.subSequence(dstart, dend);
                    return newText;
                }
            }

        }
        return dest.subSequence(dstart, dend) + src.toString();
    }
}
