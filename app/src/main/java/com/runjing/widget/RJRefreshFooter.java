package com.runjing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.runjing.wineworld.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Created: qianxs  on 2020.07.18 00:33.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.18 00:33.
 * @Remark:
 */
public class RJRefreshFooter extends InternalAbstract implements RefreshFooter {

    public RJRefreshFooter(@NonNull View wrapped) {
        super(wrapped);
    }

    public RJRefreshFooter(@NonNull View wrappedView, @Nullable RefreshInternal wrappedInternal) {
        super(wrappedView, wrappedInternal);
    }

    public RJRefreshFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
