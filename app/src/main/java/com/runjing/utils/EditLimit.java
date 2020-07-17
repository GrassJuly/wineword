package com.runjing.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by xiaoyu on 2016/8/31.
 * Describe：用于 小数限制 及 符号切割
 * TODO：设置EditText输入类型为小数类型
 * android:inputType="numberDecimal"
 * Review by：
 * Modify by：
 */
public class EditLimit {

    /*限制小数点数*/
    private int Limit_num;
    /*添加逗号*/
    private int Add_num;
    /*限制输入长度*/
    private int Num;
    /*插入的分割符*/
    private String flag;
    /*事件监听*/
    private MyTextWatcher myTextWatcher;

    public EditLimit(EditText et, LimitType type, SqlitType type1) {
        setType(et, type, type1);
    }
    public EditLimit(EditText et, LimitType type) {
        setType(et, type);
    }
    /**
     * 设置 限制类型
     *
     * @param et
     * @param type
     */
    private void setType(EditText et, LimitType type) {
        setTypeDef(type);
        setListener(et);
    }

    /**
     * @param et
     * @param type
     * @param type1
     */
    private void setType(EditText et, LimitType type, SqlitType type1) {
        setFlag(type1);
        setTypeDef(type);
        setListener(et);
    }

    /**
     * 添加监听
     *
     * @param et
     */
    private void setListener(EditText et) {
        myTextWatcher = new MyTextWatcher(et);
        et.addTextChangedListener(myTextWatcher);
    }

    /**
     * 添加分割线
     *
     * @param str
     */
    private void setSplit(EditText et, String str) {
        String str_int = "";
        String str_dec = "";
        if (str.contains(".")) {
            str_int = str.substring(0, str.lastIndexOf("."));
            str_dec = str.substring(str.lastIndexOf("."), str.length());
            str_int = setReverse(str_int);
        } else {
            str_int = setReverse(str);
        }
        str_int = setLine(str_int);
        str_int = setReverse(str_int);
        str = str_int + str_dec;
        et.removeTextChangedListener(myTextWatcher);
        myTextWatcher = null;
        et.setText(str);
        et.setSelection(str.length());
    }

    /**
     * 添加分隔符
     *
     * @param str
     */
    private String setLine(String str) {
        int length = str.length();
        StringBuffer sb = new StringBuffer(str);
        for (int i = 1; i < (Num + 1); i++) {
            for (int j = 0; j <= length; j++) {
                if (j == ((Add_num * i) + i)) {
                    sb.insert((j - 1), flag);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 设置 转换位置
     *
     * @param str
     * @return
     */
    private String setReverse(String str) {
        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }

    /**
     * 设置 添加 类型
     *
     * @param type
     */
    private void setTypeDef(LimitType type) {
        if (type == LimitType.Type_one) {
            Limit_num = 0;
            Add_num = 3;
            Num = 3;
        } else if (type == LimitType.Type_two) {
            Limit_num = 0;
            Add_num = 3;
            Num = 4;
        } else if (type == LimitType.Type_three) {
            Limit_num = 2;
            Add_num = 3;
            Num = 6;
        } else if (type == LimitType.Type_four) {
            Limit_num = 3;
            Add_num = 3;
            Num = 6;
        } else if (type == LimitType.Type_Ten) {
            Limit_num = 2;
            Add_num = 3;
            Num = 10;
        } else if (type == LimitType.Type_MAX) {
            Limit_num = 2;
            Add_num = 3;
            Num = Integer.MAX_VALUE;
        }
        ;
    }

    private void setFlag(SqlitType type) {
        if (type == SqlitType.Type_One) {
            flag = "";
        } else if (type == SqlitType.Type_Two) {
            flag = ",";
        } else if (type == SqlitType.Type_Three) {
            flag = "-";
        }
    }

    public enum LimitType {
        Type_one,
        Type_two,
        Type_three,
        Type_four,
        Type_Ten,
        Type_MAX;
    }

    public enum SqlitType {
        Type_One,
        Type_Two,
        Type_Three,
        Type_Four;
    }

    private class MyTextWatcher implements TextWatcher {

        private EditText et;

        public MyTextWatcher(EditText editText) {
            this.et = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = s.toString();
            if (str.contains(",")) {
                str = str.replaceAll(",", "");
            }
            if (str.indexOf(".") == 0) {
                et.removeTextChangedListener(myTextWatcher);
                myTextWatcher = null;
                str = "0.";
                et.setText(str);
                et.setSelection(str.length());
            }
            if (str.length() >= 2) {
                if ("0".equals(Character.toString(str.charAt(0))) && "0".equals(Character.toString(str.charAt(1)))) {
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    str = "0";
                    et.setText(str);
                    et.setSelection(str.length());
                } else {
                    if ("0".equals(Character.toString(str.charAt(0))) && !"0".equals(Character.toString(str.charAt(1)))) {
                        if (!".".equals(Character.toString(str.charAt(1)))) {
                            et.removeTextChangedListener(myTextWatcher);
                            myTextWatcher = null;
                            str = String.valueOf(Integer.parseInt(str));
                            et.setText(str);
                            et.setSelection(str.length());
                        }
                    }
                }
            }
            if (str.contains(".")) {
                int length = str.length() - (str.lastIndexOf(".") + 1);
                String str_int = str.substring(0, str.lastIndexOf("."));
                String str_dec = str.substring(str.lastIndexOf("."), str.length());
                if (str_int.length() >= Num) {
                    str_int = str_int.substring(0, Num);
                    str = str_int + str_dec;
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    setSplit(et, str);
                } else {
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    setSplit(et, str);
                }
                if (length > Limit_num) {
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    String new_str = str.substring(0, (str.lastIndexOf(".") + 1 + Limit_num));
                    setSplit(et, new_str);
                } else {
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    setSplit(et, str);
                }
            } else {
                if (str.length() <= Num) {
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    setSplit(et, str);
                } else {
                    str = str.substring(0, Num);
                    et.removeTextChangedListener(myTextWatcher);
                    myTextWatcher = null;
                    setSplit(et, str);
                }

            }

            if (myTextWatcher == null) {
                myTextWatcher = new MyTextWatcher(et);
                et.addTextChangedListener(myTextWatcher);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }


}
