package org.runjing.rjframe.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zm on 2016/6/20.
 */
public class TimeUtils {

    public static String getDateTime(){
        SimpleDateFormat formatter    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        return str;
    }


    public static String getPrintTime(){
        SimpleDateFormat formatter    = new SimpleDateFormat("yy-MM-dd HH:mm");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        return str;
    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }
}
