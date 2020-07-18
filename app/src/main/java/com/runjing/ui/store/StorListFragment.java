package com.runjing.ui.store;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.runjing.base.TitleBarFragment;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;

import java.lang.reflect.Field;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Created: qianxs  on 2020.07.16 23:21.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 23:21.
 * @Remark:
 */
public class StorListFragment extends TitleBarFragment {


//    @BindView(id = R.id.toolbar)
//    private Toolbar toolbar;
//    @BindView(id = R.id.frag_al_title)
//    private AppBarLayout app_bar;
    @BindView(id= R.id.lay_rv_content)
    private RecyclerView mRecyclerView;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.frag_good_detail, null);
    }
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initToolBar();
        initView();
    }

    private void initView() {
//        final int alphaMaxOffset = dpToPx(150);
//        toolbar.getBackground().setAlpha(0);
//        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                // 设置 toolbar 背景
//                if (verticalOffset > -alphaMaxOffset) {
//                    toolbar.getBackground().setAlpha(255 * -verticalOffset / alphaMaxOffset);
//                } else {
//                    toolbar.getBackground().setAlpha(255);
//                }
//            }
//        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(outsideAty));
        mRecyclerView.setAdapter(new ContentAdapter());
    }

    protected void initToolBar() {
        try {
            Toolbar toolbar = (Toolbar) outsideAty.findViewById(R.id.toolbar);
            if (toolbar != null) {
                // 沉浸模式
                int statusBarHeight = getStatusBarHeight();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    openAndroidLStyle();

                    toolbar.setPadding(0, statusBarHeight, 0, 0);
                    toolbar.getLayoutParams().height = dpToPx(46) + statusBarHeight;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启沉浸式模式支持
     */
    public void openAndroidLStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = outsideAty.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            outsideAty.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * dp转换为px
     */
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    private class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
        @Override
        public ContentAdapter.ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContentHolder(LayoutInflater.from(outsideAty).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ContentAdapter.ContentHolder holder, int position) {
            holder.itemTv.setText("item");
        }

        @Override
        public int getItemCount() {
            return 35;
        }

        class ContentHolder extends RecyclerView.ViewHolder {

            private TextView itemTv;

            public ContentHolder(View itemView) {
                super(itemView);
                itemTv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
