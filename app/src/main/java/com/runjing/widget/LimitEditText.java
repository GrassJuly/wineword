package com.runjing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

import java.util.ArrayList;

/**
 * @Created: xiaoyu  on 2017.04.28 10:44.
 * @Describe： 自定义输入框样式
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2017.04.28 10:44.
 * @Blog:http://blog.csdn.net/noteschapter
 * @Github:https://github.com/mrxiaoyu100001
 * @Resources:
 * @Remark:
 */

public class LimitEditText extends EditText {

    /*限制小数点数*/
    private int Limit_num;
    /*添加逗号*/
    private int Add_num;
    /*限制输入长度*/
    private int Num;
    /*插入的分割符*/
    private int Tag;
    private String[] tags = {"", ",", "-"};
    private String flag;
    private ArrayList<TextWatcher> mListeners;
    private onValueCallBack listener;

    public LimitEditText(Context context) {
        super(context);
        addTextChangedListener(new changeWatcher());
        mListeners = new ArrayList<>();
        Limit_num = Integer.MAX_VALUE;
        Add_num = Integer.MAX_VALUE;
        Num = Integer.MAX_VALUE;
        flag = tags[Tag];
    }

    public LimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditLimit, 0, 0);
        Limit_num = a.getInteger(R.styleable.EditLimit_limit, Integer.MAX_VALUE);
        Add_num = a.getInteger(R.styleable.EditLimit_type, Integer.MAX_VALUE);
        Num = a.getInteger(R.styleable.EditLimit_length, Integer.MAX_VALUE);
        Tag = a.getInteger(R.styleable.EditLimit_Tag, Appconfig.TAG_ZERO);
        addTextChangedListener(new changeWatcher());
        flag = tags[Tag];
        a.recycle();
    }

    public LimitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditLimit, defStyleAttr, 0);
        Limit_num = a.getInteger(R.styleable.EditLimit_limit, Integer.MAX_VALUE);
        Add_num = a.getInteger(R.styleable.EditLimit_type, Integer.MAX_VALUE);
        Num = a.getInteger(R.styleable.EditLimit_length, Integer.MAX_VALUE);
        Tag = a.getInteger(R.styleable.EditLimit_Tag, Appconfig.TAG_ZERO);
        addTextChangedListener(new changeWatcher());
        flag = tags[Tag];
        a.recycle();
    }

    /**
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
     * @param str
     * @return
     */
    private String setFlag(String str) {
        if ("0".equals(str.substring(0, 1))) str = str.substring(1, str.length());
        int length = str.length();
        if (str.length() <= Add_num) {
            return str;
        } else {
            StringBuffer sb = new StringBuffer();
            int round = length / Add_num;
            int remainder = length % Add_num;
            if (remainder == 1) {
                for (int i = 0; i < round; i++) {
                    int a = (length - (Add_num * (i + 1)));
                    int b = (length - (Add_num * i));
                    sb.append(str.substring(a, b));
                    System.out.println("    a    " + a + "\r   b   " + b);
                    sb.append(flag);
                }
            }
            System.out.println("     sb     " + sb);
            return sb.toString();
        }
    }

    /**
     * @param str
     * @return
     */
    private String setReverse(String str) {
        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        if (mListeners == null) {
            mListeners = new ArrayList<TextWatcher>();
        }
        mListeners.add(watcher);
        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        if (mListeners != null) {
            int i = mListeners.indexOf(watcher);
            if (i >= 0) {
                mListeners.remove(i);
            }
        }
        super.removeTextChangedListener(watcher);
    }

    public void clearTextChangedListeners() {
        if (mListeners != null) {
            for (TextWatcher watcher : mListeners) {
                super.removeTextChangedListener(watcher);
            }
            mListeners.clear();
            mListeners = null;
        }
    }

    /**
     * @param listener
     */
    public void setOnValueBack(onValueCallBack listener) {
        this.listener = listener;
    }

    /**
     * @return
     */
    public String getString() {
        return getText().toString().replaceAll(flag, "");
    }

    class changeWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence text, int start, int before, int count) {
            String str = text.toString();
            if (".".equals(str)) {
                str = "0.";
                setText(str);
//                setSelection(str.length());
            } else {
                if (!TextUtils.isEmpty(str)) {
                    if (str.length() > 1 && str.indexOf("0") == 0) {
                        if (Limit_num == Appconfig.TAG_ZERO) {
                            if (str.length() > Add_num) str = str.replaceAll(flag, "");
                            if (str.indexOf(".") == 1) {
                                str = str.substring(0, 1);
                            } else {
                                str = str.substring(1, str.length());
                            }
                            if (str.length() > Num) str = str.substring(0, Num);
                            str = setReverse(setLine(setReverse(str)));
                            clearTextChangedListeners();
                            setText(str);
                            setSelection(str.length());
                            addTextChangedListener(this);
                        } else {
                            if (str.length() > Add_num) str = str.replaceAll(flag, "");
                            String integer = "";
                            if (str.contains(".")) {
                                if (str.indexOf(".") == 1) {
                                    integer = str.substring(0, 1);
                                    str = str.substring(1, str.length());
                                } else {
                                    integer = str.substring(0, str.indexOf("."));
                                    str = str.substring(str.indexOf("."), str.length());
                                }
                                if (str.length() > (Limit_num + 1))
                                    str = str.substring(0, (Limit_num + 1));
                                str = integer + setReverse(setLine(setReverse(str)));
                            } else {
                                if (str.contains("0") && str.indexOf("0") == 0)
                                    str = str.substring(1, str.length());
                                if (str.length() > Num) str = str.substring(0, Num);
                                str = setReverse(setLine(setReverse(str)));
                            }
                            clearTextChangedListeners();
                            setText(str);
                            setSelection(str.length());
                            addTextChangedListener(this);
                        }
                    } else {
                        if (str.contains(".")) {
                            if (Limit_num == Appconfig.TAG_ZERO) {
                                String integer = str.substring(0, str.lastIndexOf("."));
                                if (integer.length() > Add_num)
                                    integer = integer.replaceAll(flag, "");
                                if (integer.length() > Num) integer = integer.substring(0, Num);
//                            integer = setReverse(setFlag(setReverse(integer)));
                                str = setReverse(setLine(setReverse(integer)));
                                clearTextChangedListeners();
                                setText(str);
                                setSelection(str.length());
                                addTextChangedListener(this);
                            } else {
                                String integer = str.substring(0, str.lastIndexOf("."));
                                String dot = str.substring((str.lastIndexOf(".") + 1), str.length());
                                if (integer.length() > Add_num)
                                    integer = integer.replaceAll(flag, "");
                                if (integer.length() > Num) integer = integer.substring(0, Num);
                                if (dot.length() > Limit_num) dot = dot.substring(0, Limit_num);
//                            integer = setReverse(setFlag(setReverse(integer)));
                                integer = setReverse(setLine(setReverse(integer)));
                                clearTextChangedListeners();
                                str = integer + "." + dot;
                                setText(str);
                                setSelection(str.length());
                                addTextChangedListener(this);
                            }
                        } else {
                            if (str.length() > Add_num) str = str.replaceAll(flag, "");
                            if (str.length() > Num) str = str.substring(0, Num);
//                            str = setReverse(setFlag(setReverse(str)));
                            str = setReverse(setLine(setReverse(str)));
                            clearTextChangedListeners();
                            setText(str);
                            setSelection(str.length());
                            addTextChangedListener(this);
                        }
                    }
                }
            }
            if (listener != null) listener.onValueBack(LimitEditText.this, text);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface onValueCallBack {
        void onValueBack(LimitEditText v, CharSequence charSequence);
    }
}
