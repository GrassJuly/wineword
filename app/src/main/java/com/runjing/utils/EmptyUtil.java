package com.runjing.utils;

public class EmptyUtil {

    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }
}
