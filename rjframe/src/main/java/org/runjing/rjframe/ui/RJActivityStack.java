package org.runjing.rjframe.ui;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Activity的管理类，运用于Activity的管理以及应用程序的退出
 * Created by zm on 2016/4/27.
 */
public class RJActivityStack {
    private static Stack<I_RJActivity> activityStack;
    private static final RJActivityStack instance = new RJActivityStack();

    private RJActivityStack(){}

    public static RJActivityStack create(){
        return instance;
    }

    public int getCount(){
        return activityStack.size();
    }

    /**
     *  添加Activity到栈
     * @param activity
     */
    public void addActivity(I_RJActivity activity){
        if(activityStack == null){
            activityStack = new Stack<I_RJActivity>();
        }
        activityStack.add(activity);
    }


    /**
     * 获取当前activity(栈顶Activity)
     * @return
     */
    public Activity topActivity(){
        if(activityStack == null){
            throw new NullPointerException("Activity stack is Null,your Activity must extends MNActivity");
        }
        if(activityStack.isEmpty()){
            return null;
        }
        I_RJActivity activity = activityStack.lastElement();
        return  (Activity)activity;
    }

    /**
     * 获取当前Activity(栈顶Activity) 没有找到则返回null
     * @param cls
     * @return
     */
    public Activity findActivity(Class<?> cls){
        I_RJActivity activity = null;
        for(I_RJActivity aty :activityStack){
            if(aty.getClass().equals(cls)){
                activity = aty;
                break;
            }
        }
        return (Activity)activity;
    }

    /**
     * 结束当前Activity(栈顶Activity)
     */
    public void finishActivity(){
        I_RJActivity activity = activityStack.lastElement();
        finishActivity((Activity) activity);
    }

    /**
     * 结束指定的Activity(重载)
     * @param activity
     */
    public void finishActivity(Activity activity){
       if(activity != null){
           activityStack.remove(activity);
           activity = null;
       }
    }

    /**
     * 结束指定
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        for(I_RJActivity activity :activityStack){
             if(activity.getClass().equals(cls)){
                 finishActivity((Activity)activity);
             }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在栈中，则栈全部清空
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls){
        for(I_RJActivity activity : activityStack){
            if(!(activity.getClass().equals(cls))){
                finishActivity((Activity)activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for(int i = 0, size = activityStack.size();i<size;i++){
            if(null != activityStack.get(i)){
                ((Activity)activityStack.get(i)).finish();
            }
        }
        activityStack.clear();
    }
    @Deprecated
    public void AppExit(Context cxt){
        appExit(cxt);
    }

    /**
     * 应用程序退出
     * @param context
     */
    public void appExit(Context context){
        try {
            finishAllActivity();
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
