package com.runjing.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 设置editText限制两位小数
 * @author zm
 */

public class TextChangeListener implements TextWatcher {
    public EditText editText;
    public TextChangeListener(EditText edit){
        this.editText = edit;
    }

    @Override
    public void afterTextChanged(Editable edt) {
        String temp = edt.toString().trim();
        int posDot = temp.indexOf(".");

        if (!temp.contains(".") && temp.length()>10){

            edt.delete(10,temp.length());
        }

        if (temp.substring(0).equals(".")) {
            temp = "0" + temp;
            editText.setText(temp);
            editText.setSelection(temp.length());

        }
        if (posDot <= 0) return;
        if (temp.length() - posDot - 1 > 2)
        {
            edt.delete(posDot + 3, posDot + 4);
        }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
}