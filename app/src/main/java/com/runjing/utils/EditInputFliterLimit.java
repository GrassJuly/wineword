package com.runjing.utils;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ymx on 2017/11/7.
 */

public class EditInputFliterLimit implements InputFilter {

    private  EditText editText;
    private  int length;
    private Pattern p;

    public EditInputFliterLimit(EditText editText, int length){
        p = Pattern.compile("[^a-zA-Z0-9\u4E00-\u9FA5]");
        this.editText = editText;
        this.length = length;


    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        if (start == 0 && dend == 0 && ".".equals(source)) {
            return "";
        }

        //验证删除等按键
        if ("".equals(source.toString())) {
            return null;
        }

        Matcher m = p.matcher(source);
        if (!m.matches() ) {

            int my_length = (dest.toString().length()+source.toString().length());
            if ( my_length > length) {
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText;
            }
            return null;
        }


        return dest.subSequence(dstart, dend);

    }
}
