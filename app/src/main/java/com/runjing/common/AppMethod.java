package com.runjing.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.runjing.MainActivity;
import com.runjing.MyApplication;
import com.runjing.base.SimpleBackActivity;
import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarActivity;
import com.runjing.bean.response.guild.GuildImageBean;
import com.runjing.bean.response.home.BannerBean;
import com.runjing.ui.good.DetailBannerAdapter;
import com.runjing.ui.home.BannerItemAdapter;
import com.runjing.ui.login.GuildBannerAdapter;
import com.runjing.utils.ColorPhrase;
import com.runjing.utils.PermissionUtils;
import com.runjing.utils.PopupWindowUtil;
import com.runjing.utils.store.MMKVUtil;
import com.runjing.widget.pickerview.SupplierPickerView;
import com.runjing.widget.pickerview.TimePickerView;
import com.runjing.widget.pop.MiddlePopupWindow;
import com.runjing.wineworld.R;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import androidx.fragment.app.Fragment;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.Fragment;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：公共调用方法
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class AppMethod {

    private static boolean isExist;

    private AppMethod() {
        throw new Error("我就是我，是一朵即将消逝的烟火。");
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     *
     * @param cxt
     * @param page
     * @param data
     */
    public static void postShowWith(Activity cxt, SimpleBackPage page,
                                    Bundle data) {
        Intent intent = new Intent(cxt, SimpleBackActivity.class);
        intent.putExtra(Appconfig.CONTENT_KEY, page.getValue());
        intent.putExtra(Appconfig.DATA_KEY, data);
        cxt.startActivity(intent);
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     *
     * @param cxt
     * @param page
     */
    public static void postShowWith(Activity cxt, SimpleBackPage page) {
        postShowWith(cxt, page, null);
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     *
     * @param code 启动码
     * @param page 要显示的Fragment
     * @param data 传递的Bundle数据
     */
    public static void postShowForResult(Fragment fragment, int code,
                                         SimpleBackPage page, Bundle data) {
        Intent intent = new Intent(fragment.getActivity(),
                SimpleBackActivity.class);
        intent.putExtra(Appconfig.CONTENT_KEY, page.getValue());
        intent.putExtra(Appconfig.DATA_KEY, data);
        fragment.startActivityForResult(intent, code);
    }


    /**
     * 跳转到maniacitivity
     *
     * @param a
     * @param bundle
     */
    public static void postShowForResult(Activity a, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(a, MainActivity.class);
        intent.putExtra(Appconfig.DATA_KEY, bundle);
        a.startActivity(intent);
    }

    /**
     * @param a
     * @param page
     * @param bundle
     */
    public static void postShowForActivity(Activity a, SimpleBackPage page, Bundle bundle) {
        Intent intent = new Intent(a, page.getPageByValue(page.getValue()));
        intent.putExtra(Appconfig.DATA_KEY, bundle);
        a.startActivity(intent);
    }

    /**
     * @param a
     * @param page
     * @param bundle
     */
    public static void postShowForActivity(Fragment a, SimpleBackPage page, Bundle bundle, int code) {
        Intent intent = new Intent(a.getActivity(), page.getPageByValue(page.getValue()));
        intent.putExtra(Appconfig.DATA_KEY, bundle);
        a.startActivityForResult(intent, code);
    }

    /**
     * activity  跳转到SimpleBackActivity时，只能使用该方法跳转
     *
     * @param mActivity
     * @param code      启动码
     * @param page      要显示的Fragment
     * @param data      传递的Bundle数据Appconfig.DATA_KEY
     */
    public static void postShowForResult(Activity mActivity, int code,
                                         SimpleBackPage page, Bundle data) {
        Intent intent = new Intent(mActivity, SimpleBackActivity.class);
        intent.putExtra(Appconfig.CONTENT_KEY, page.getValue());
        intent.putExtra(Appconfig.DATA_KEY, data);
        mActivity.startActivityForResult(intent, code);
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     *
     * @param code 启动码
     * @param page 要显示的Fragment
     */
    public static void postShowForResult(Fragment fragment, int code,
                                         SimpleBackPage page) {
        postShowForResult(fragment, code, page, null);
    }

    /**
     * @param fragment
     * @param activity
     */
    public static void postActivity(Fragment fragment, Class activity) {
        Intent intent = new Intent(fragment.getActivity(), activity);
        fragment.startActivity(intent);
    }

    public static void postActivity(Activity fragment, Class activity) {
        Intent intent = new Intent(fragment, activity);
        fragment.startActivity(intent);
    }

    public static void postActivityForResult(Fragment mActivity, int code,
                                             Class activity, Bundle data) {
        Intent intent = new Intent(mActivity.getActivity(), activity);
        intent.putExtra(Appconfig.DATA_KEY, data);
        mActivity.startActivityForResult(intent, code);
    }

    public static void postActivityForResult(Activity mActivity, int code,
                                             Class activity, Bundle data) {
        Intent intent = new Intent(mActivity, activity);
        intent.putExtra(Appconfig.DATA_KEY, data);
        mActivity.startActivityForResult(intent, code);
    }

    /**
     * 判断图片是否存在
     *
     * @param url
     * @return
     */
    public static boolean isExit(String url) {
        try {
            File mFile = new File(url);
            if (mFile.exists()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 创建w文件
     *
     * @param url
     * @return
     */
    public static boolean createFile(String url) {
        try {
            File mFile = new File(url);
            if (mFile.exists()) {
                mFile.mkdirs();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 创建w文件
     *
     * @param url
     * @return
     */
    public static void createFiles(String url) {
        try {
            File mFile = new File(url);
            if (mFile.exists()) {
                mFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件
     *
     * @param url
     * @return
     */
    public static boolean delFiles(String url) {
        try {
            File mFile = new File(url);
            return mFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置字符串颜色
     *
     * @param strings
     * @param inColor  前面字体颜色
     * @param outColor 后面字体颜色
     * @return
     */
    public static CharSequence setDiffCollors(CharSequence strings, int inColor, int outColor) {
        if (strings != null || !"".equals(strings)) {
            strings = ColorPhrase.from(strings).withSeparator("{}").innerColor(inColor).outerColor(outColor).format();
        }
        return strings;
    }


    /**
     * 分段显示数据并保留小数点后三位
     */
    public static String sectionDataThree(String data) {
        Double num = Double.parseDouble(data);
        DecimalFormat df = null;
        try {
            df = new DecimalFormat(",###,##0.000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return df.format(num);
    }

    /**
     * 设置默认数据
     *
     * @param str 判断内容
     * @return
     */
    public static String setDefault(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
    }


    /**
     * 设置默认数据
     *
     * @param str  判断内容
     * @param unit 单位   无单位时传空串 ""
     * @return
     */
    public static String setDefault(String str, String unit) {
        return StringUtils.isEmpty(str) ? "" : str + (StringUtils.isEmpty(unit) ? "" : " " + unit);
    }

    /**
     * 设置默认数据  数字
     *
     * @param str
     * @return
     */
    public static String setDefaultNumber(String str) {
        return StringUtils.isEmpty(str) ? "0" : str;
    }

    /**
     * 设置图片
     *
     * @param url
     * @param simpleImage
     */
    public static void setImageView(String url, SimpleDraweeView simpleImage) {
        try {
            if (url != null) {
                Uri uri = Uri.parse(url);
                simpleImage.setImageURI(uri);
            } else {
                setResImageView(String.valueOf(R.mipmap.ic_launcher), simpleImage);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置本地图片
     *
     * @param url
     * @param simpleImage
     */
    public static void setResImageView(String url, SimpleDraweeView simpleImage) {
        simpleImage.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(url)).build());
    }

    /**
     * 设置本地图片
     *
     * @param url
     * @param simpleImage
     */
    public static void setResImageView(int url, SimpleDraweeView simpleImage) {
        simpleImage.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(url)).build());
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static double getVersionCode(Context context) {
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8,7，其他位置的可以为0-9
    */
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[3587]"代表第二位可以为3、5、8,7中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (StringUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * @param context
     * @return
     */
    public static String getPackName(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param activity
     * @param msg
     */
    public static void showMsg(final Activity activity, String msg) {
        PopupWindowUtil.showPopWindow(activity,
                activity.getString(R.string.app_default), msg
                , true, new MiddlePopupWindow.PopupWindowCallBack() {
                    @Override
                    public void onNegativeButtonClick() {

                    }

                    @Override
                    public void onPositiveButtonClick() {
                        activity.finish();
                    }
                });
    }

    public interface PopBackListener {
        void OnSuccess();
    }

    /**
     * @param activity
     * @param msg
     * @param listener
     */
    public static void showMsg(final Activity activity, String title, String msg, final PopBackListener listener) {
        PopupWindowUtil.showPopWindow(activity,
                title, msg
                , true, new MiddlePopupWindow.PopupWindowCallBack() {
                    @Override
                    public void onNegativeButtonClick() {
                    }

                    @Override
                    public void onPositiveButtonClick() {
                        if (listener != null) listener.OnSuccess();
                    }
                });
    }


    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 隐藏或关闭软键盘
     *
     * @param mActivity
     */
    public static void closeSoftKeyboard(Activity mActivity, View view) {
        ((InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @param activity
     * @throws Exception
     */
    public static void AppOver(Activity activity) {
        try {
            if (!isExist) {
                ViewInject.longToast("再按一次退出");
                isExist = true;
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExist = false;
                    }
                }, 3000);
            } else {
                if (Integer.parseInt(android.os.Build.VERSION.SDK) >= android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                    MMKVUtil.getInstance().encode(Appconfig.address, "");
                    Intent mainActivity = new Intent(Intent.ACTION_MAIN);
                    mainActivity.addCategory(Intent.CATEGORY_HOME);
                    mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(mainActivity);
                    System.exit(0);//退出程序
                } else {
                    MMKVUtil.getInstance().encode(Appconfig.address, "");
                    ActivityManager activityMgr = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
                    activityMgr.restartPackage(activity.getPackageName());
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取时间选择器
     *
     * @return
     */
    public static void showTimePicker(Activity mActivity, final View tvTime, TimePickerView.Type type) {
        //时间选择器
        final TimePickerView pvTime = new TimePickerView(mActivity, type);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //弹出时间选择器
        pvTime.show();

        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                if (tvTime instanceof EditText) {
                    ((EditText) tvTime).setText(getTime_yMd(date));
                } else if (tvTime instanceof TextView) {
                    ((TextView) tvTime).setText(getTime_yMd(date));
                }
            }

        });

    }

    public static String getTime_yMd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取时间 格式
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * @param context
     * @param title
     * @param data
     * @param textView
     * @return 用于界面显示隐藏问题
     * @throws Throwable
     */
    public static SupplierPickerView AppSelectPicker(Context context,
                                                     String title,
                                                     ArrayList<String> data,
                                                     final TextView textView) throws Throwable {
        final SupplierPickerView optionsPickerView = new SupplierPickerView(context);
        optionsPickerView.setPicker(data)
                .setLineSpacing(1.8f)
                .setTextSize(15f)
                .setTitle(title)
                .setOnOptionsClickedListener(new SupplierPickerView.OnOptionsClickedListener() {
                    @Override
                    public void onOptionsClick(String s) {
                        if (textView != null) textView.setText(s);
                        optionsPickerView.dismiss();

                    }
                });
        optionsPickerView.show();
        return optionsPickerView;
    }

    /**
     * @param context
     * @param title
     * @param data
     * @param listener
     * @return
     * @throws Throwable
     */
    public static SupplierPickerView AppSelectPicker(Context context,
                                                     String title,
                                                     ArrayList<String> data,
                                                     SupplierPickerView.OnOptionsClickedListener listener) throws Throwable {
        final SupplierPickerView optionsPickerView = new SupplierPickerView(context);
        optionsPickerView.setPicker(data)
                .setLineSpacing(1.8f)
                .setTextSize(15f)
                .setTitle(title)
                .setOnOptionsClickedListener(listener);
        optionsPickerView.show();
        return optionsPickerView;
    }


    /**
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    public static String division(String a, String b) {
        try {
            if (TextUtils.isEmpty(a)) return "";
            if (TextUtils.isEmpty(b)) return "";
            if ("0".equals(b)) return "";
            Double c = 0.0;
            double d = Double.parseDouble(a);
            double e = Double.parseDouble(b);
            if (d != 0.0 || e != 0.0) {
                c = d / e;
            }
            return new BigDecimal(c).setScale(Appconfig.TAG_ZERO, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static String multiply(String a, String b) {
        try {
            if (TextUtils.isEmpty(a)) return "";
            if (TextUtils.isEmpty(b)) return "";
            Double c = 0.0;
            double d = Double.parseDouble(a);
            double e = Double.parseDouble(b);
            if (d != 0.0 || e != 0.0) {
                c = d * e;
            }
            return new BigDecimal(c).setScale(Appconfig.TAG_ZERO, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 校验车牌号
     *
     * @param carnumber
     * @return
     */
    public static boolean isCarnumberNO(String carnumber) {
        /*
         车牌号格式：省会 + A-Z + 5位A-Z或0-9
        （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）
          */
        String str = "京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼港澳台";
        if (!StringUtils.isEmpty()) {

            String s1 = carnumber.substring(0, 1);//获取字符串的第一个字符

            if (str.contains(s1)) {


            } else {
                return false;
            }


        } else {
            return false;
        }

        String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
        if (TextUtils.isEmpty(carnumber)) return false;
        else return carnumber.matches(carnumRegex);
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * @param enable
     */
    public static void setStatusBarVisibility(Activity activity, boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * @param enable
     */
    public static void setFullScreen(Activity activity, boolean enable) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        if (enable) {
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        activity.getWindow().setAttributes(lp);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    /**
     * @param activity
     */
    public static void clearAppInfo(Activity activity) {
        MMKVUtil.getInstance().clearAll();
        activity.startActivity(new Intent(activity, MainActivity.class));
        MyApplication.contextApp.exit();
        ViewInject.longToast(activity.getString(R.string.app_loginout_toast));
    }

    public static String getLocalPic(String uri) {
        return "drawable://" + uri;
    }

    /**
     * 导航页banner
     *
     * @param context
     * @param banner
     * @param images
     */
    public static void GuildBanner(TitleBarActivity context, Banner banner, List<GuildImageBean> images, int type) {
        GuildBannerAdapter adapter = new GuildBannerAdapter(images, type);
        banner.setDelayTime(4500);
        banner.setBannerRound(0);
        banner.isAutoLoop(false);
        banner.addBannerLifecycleObserver(context)
                .setAdapter(adapter, false)
                .setIndicator(new CircleIndicator(context))
                .start();
    }

    /**
     * 详情页banner
     *
     * @param context
     * @param banner
     * @param images
     */
    public static void DetailBanner(TitleBarActivity context, Banner banner, List<com.runjing.bean.response.home.def.BannerBean> images) {
        DetailBannerAdapter adapter = new DetailBannerAdapter(images);
        banner.setDelayTime(4500);
        banner.setBannerRound(0);
        banner.isAutoLoop(false);
        banner.addBannerLifecycleObserver(context)
                .setAdapter(adapter, true)
                .start();
    }

    /**
     * 首页banner
     *
     * @param activity
     * @param banner
     * @param images
     */
    public static void bannerWeight(TitleBarActivity activity, Banner banner, List<BannerBean.DataBean> images) {
        BannerItemAdapter adapter = new BannerItemAdapter(images);
        banner.setDelayTime(4500);
        banner.setBannerRound(20);
        banner.addBannerLifecycleObserver(activity)
                .setAdapter(adapter)
                .setIndicator(new CircleIndicator(activity), true)
                .start();
    }

    /**
     * //调用
     * SpannableString spannableString = changTVsize("53.9");
     * chooseMoviePrice.setText(spannableString);
     *
     * @param value
     * @return
     */
    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 设置中划线
     *
     * @param textView
     */
    public static void setTextViewLine(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线 
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        }
    }

    private static String replaceAction(String str, String regular) {
        return str.replaceAll(regular, "*");
    }

    // 判断字符串是否是数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.matches();
        return result;
    }

    //^[A-Za-z0-9]+$

    // 判断字符串是否是数字和字母
    public static boolean isNumberAndLeter(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * @param context
     * @param persions
     * @param listener
     */
    public static void getPermissions(Activity context, int requestCode, String[] persions, PermissionUtils.OnPermissionListener listener) {
        PermissionUtils.requestPermissionsResult(context, requestCode, persions, listener);
    }

    /**
     * 升级
     *
     * @param context
     */
//    public static void updateApp(final Activity context) {
//        UpgradeRequest request = new UpgradeRequest();
//        request.setAppCode("mnSupplier");
//        OkHttpUtil.postRequest(RJBaseUrl.AppUpdate, request, UpgradeRequest.class, new MyRequestCallBack<UpgradeResponse>() {
//                    @Override
//                    public void onPostResponse(UpgradeResponse response) {
//                        try {
//                            /*TODO::看着不爽你就删了他，反正我是不删，咋滴*/
//                            if (response == null) return;
//                            if (response.resultCode.equals(Appconfig.RequestSuccess)) {
//                                UpgradeBean upgradeBean = response.getData();
//                                String newVersion = upgradeBean.getAppVersion();
//                                if (newVersion != null && !newVersion.equals("")) {
//                                    String currentVersion = SystemTool
//                                            .getAppVersionName(context);
//                                    if (!newVersion.equals(currentVersion)) {
//                                        LibAutoUpdate autoUpdate = new LibAutoUpdate(context,
//                                                RJBaseUrl.BASE_PIC_URL + upgradeBean.getAppUrl(), Appconfig.TAG_ZERO,
//                                                upgradeBean.getAppVersion(), upgradeBean.getAppDescribe(),
//                                                upgradeBean.getAppForce(), AppMethod.getPackName(context), false);
//                                        Appconfig.isFource = upgradeBean.getAppForce();
//                                        if (Appconfig.isDownLoad.equals("F")) {
//                                            autoUpdate.checkUpByVersionName();
//                                        }
//                                        if (TextUtils.isEmpty(upgradeBean.getLimitUpdate())
//                                                && Appconfig.isDownLoad.equals("T")) {
//                                            MyApplication.isLimitUpdate = Appconfig.isLimtCode;
//                                        }
//                                    }
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onPostErrorResponse(Exception e, String msg) {
//                    }
//
//                    @Override
//                    public void onNoNetWork() {
//
//                    }
//                }
//
//        );
//    }

    /**
     *
     * @param context
     * @param photos
     * @param file
     * @param listener
     */
    public static void getPic(Context context, File photos, String file, OnCompressListener listener) {
        Luban.with(context)
                .load(photos)
                .ignoreBy(10)
                .setTargetDir(file)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return false;
                    }
                })
                .setCompressListener(listener).launch();
    }

}
