package com.runjing.utils.store;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * @Created: qianxs  on 2020.07.20 20:03.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.20 20:03.
 * @Remark:
 */
public class MMKVUtil {
    private static volatile MMKVUtil instance;
    private MMKV mmkv;

    private MMKVUtil() {
        mmkv = MMKV.defaultMMKV();
    }

    public static MMKVUtil getInstance() {
        if (instance == null) {
            synchronized (MMKVUtil.class) {
                if (instance == null) {
                    instance = new MMKVUtil();
                }
            }
        }
        return instance;
    }
    public void encode(String key, Object  value) {
        if (value instanceof String) {
            mmkv.encode(key, (String) value);
        } else if (value instanceof Integer) {
            mmkv.encode(key, (Integer) value);
        } else if (value instanceof Long) {
            mmkv.encode(key, (Long) value);
        } else if (value instanceof Double) {
            mmkv.encode(key, (Double) value);
        } else if (value instanceof Boolean) {
            mmkv.encode(key, (Boolean) value);
        }else if (value instanceof Set) {
            mmkv.encode(key, (Set<String>) value);
        }
    }


    /**
     * @param key
     * @param value
     */
    public void encode(String key, String value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, int value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, float value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, double value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, long value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, boolean value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, Set<String> value) {
        mmkv.encode(key, value);
    }

    public void encode(String key, Parcelable value) {
        mmkv.encode(key, value);
    }

    public String decodeString(String key) {
        return mmkv.decodeString(key);
    }

    public int decodeInt(String key) {
        return mmkv.decodeInt(key);
    }

    public long decodeLong(String key) {
        return mmkv.decodeLong(key);
    }

    public double decodeDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public float decodeFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public Set<String> decodeSet(String key) {
        return mmkv.decodeStringSet(key);
    }

    public boolean decodeBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    public Parcelable decodeParceable(String key, Class tClass) {
        return mmkv.decodeParcelable(key, tClass);
    }

    public void clear(String key) {
        mmkv.removeValueForKey(key);
    }

    public void clearAll() {
        mmkv.clearAll();
    }


}
