package org.runjing.rjframe.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 注解工具类
 * Created by zm on 2016/4/27.
 */
public class AnnotateUtil {

    /**
     *
     * @param currentClass 当前类，一般为Activity或Fragment
     * @param sourceView 待绑定控件的直接或间接父控件
     */
    public static void initBindView(Object currentClass,View sourceView){
      Field[] fields = currentClass.getClass().getDeclaredFields();
        if(fields != null && fields.length>0){
            for(Field field : fields){
                BindView bindView = field.getAnnotation(BindView.class);
                if(bindView != null){
                    int viewId = bindView.id();
                    boolean clickLis = bindView.click();

                    try {
                        field.setAccessible(true);
                        if(clickLis){
                            sourceView.findViewById(viewId).setOnClickListener((View.OnClickListener)currentClass);
                        }

                        field.set(currentClass, sourceView.findViewById(viewId));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    /**
     * 必须在setContentview之后调用
     * @param aty Activity对象
     */
    public static void initBindView(Activity aty){
     initBindView(aty,aty.getWindow().getDecorView());
    }

    public static void initBindView(View view){
        Context cxt = view.getContext();
        if(cxt instanceof Activity){
            initBindView((Activity)cxt);
        }else{
            throw new RuntimeException("view must into activity");
        }
    }

    /**
     * 必须在setContentView之后调用
     * @param frag 要初始化的Fragment
     */
    public static void initBindView(Fragment frag){
        initBindView(frag,frag.getActivity().getWindow().getDecorView());
    }

}
