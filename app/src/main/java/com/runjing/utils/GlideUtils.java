package com.runjing.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.runjing.rjframe.utils.RJLoger;

/**
 * @Created: qianxs  on 2020.07.14 13:00.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.14 13:00.
 * @Remark:
 */
public class GlideUtils {

    private String TAG = GlideUtils.class.getSimpleName();

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    private GlideUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideUtils INSTANCE = new GlideUtils();
    }

    public static GlideUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    /**
     *
     * @param imageview
     * @param url
     * @param context
     * @param defultPic
     */
    public static void displayImageCenter(final ImageView imageview, String url, Context context, int defultPic) {
        if (TextUtils.isEmpty(url + "")) {
            imageview.setImageResource(defultPic);
        } else {
            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defultPic).error(defultPic).dontAnimate();
            Glide.with(context).load(url).apply(options).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    if(drawable!=null){
                        imageview.setImageDrawable(drawable);
                    }
                }
            });
        }

    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public void glideLoad(Context context, String url, ImageView imageView) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        if (context != null) {
            Glide.with(context).load(url).into(imageView);
        } else {
            RJLoger.debug(TAG, "Picture loading failed,context is null");
        }
    }

    public void glideLoadAsGif(Context context, String url, ImageView imageView) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        if (context != null) {
            Glide.with(context).asGif().load(url).into(imageView);
        } else {
            RJLoger.debug(TAG, "Picture loading failed,context is null");
        }
    }

    public void glideLoad(Context context, String url, ImageView imageView, RequestOptions options) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        } else {
            RJLoger.debug(TAG, "Picture loading failed,context is null");
        }
    }

    public void glideLoad(Context context, int res, ImageView imageView, RequestOptions options) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        if (context != null) {
            Glide.with(context).load(res).apply(options).into(imageView);
        } else {
            RJLoger.debug(TAG, "Picture loading failed,context is null");
        }
    }

}