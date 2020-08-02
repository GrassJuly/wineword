package com.runjing.utils;

import android.graphics.Rect;
import android.view.View;
/**
 * @Created: qianxs  on 2020.08.02 21:05.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.02 21:05.
 * @Remark:
 */

import androidx.recyclerview.widget.RecyclerView;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public ItemOffsetDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left, top, right, bottom);
    }
}
