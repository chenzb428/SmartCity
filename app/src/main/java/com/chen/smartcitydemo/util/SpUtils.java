package com.chen.smartcitydemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SpUtils {

    /**
     * SharedPreferences 文件名称
     */
    private static final String SP_NAME = "sp_SmartCity";

    /**
     * 第一次启动 APP
     */
    public static final String IS_FIRST_START = "is_first_start";

    /**
     * 网络地址
     */
    public static final String HOST_IP_PORT = "host_ip_port";

    /**
     * Token
     */
    public static final String TOKEN = "token";

    private static SpUtils spUtils;
    private static SharedPreferences sharedPreferences;

    private SpUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 初始化 SPUtils，在 Application 中初始化
     * @param context 上下文
     */
    public static void getInstance(Context context) {
        if (spUtils == null) {
            spUtils = new SpUtils(context);
        }
    }

    /**
     * 保存数据到 SharedPreferences
     * @param key 键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putData(String key, Object value) {
        boolean result;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String simpleName = value.getClass().getSimpleName();

        try {
            switch (simpleName) {
                case "String":
                    edit.putString(key, (String) value);
                    break;
                case "Integer":
                    edit.putInt(key, (Integer) value);
                    break;
                case "Long":
                    edit.putLong(key, (Long) value);
                    break;
                case "Float":
                    edit.putFloat(key, (Float) value);
                    break;
                case "Boolean":
                    edit.putBoolean(key, (Boolean) value);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = gson.toJson(value);
                    edit.putString(key, json);
                    break;
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        edit.apply();
        return result;
    }

    /**
     * 获取保存到 SharedPreferences 中的数据
     * @param key 键
     * @param defaultValue 默认值
     * @return 保存数据
     */
    public static Object getData(String key, Object defaultValue) {
        Object result = null;

        if (defaultValue != null) {
            String simpleName = defaultValue.getClass().getSimpleName();

            try {
                switch (simpleName) {
                    case "String":
                        result = sharedPreferences.getString(key, (String) defaultValue);
                        break;
                    case "Integer":
                        result = sharedPreferences.getInt(key, (Integer) defaultValue);
                        break;
                    case "Long":
                        result = sharedPreferences.getLong(key, (Long) defaultValue);
                        break;
                    case "Float":
                        result = sharedPreferences.getFloat(key, (Float) defaultValue);
                        break;
                    case "Boolean":
                        result = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
                        break;
                    default:
                        String json = sharedPreferences.getString(key, null);
                        if (json != null && json.length() > 0) {
                            Gson gson = new Gson();
                            result = gson.fromJson(json, defaultValue.getClass());
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = defaultValue;
            }
        }

        return result;
    }

    /**
     * 判断是否第一次打开 App
     */
    public static boolean isFirstStart() {
        return (boolean) getData(IS_FIRST_START, true);
    }

    /**
     * 设置服务器地址
     * @param host 地址
     */
    public static void setHost(String host) {
        putData(HOST_IP_PORT, host);
    }

    /**
     * 获取服务器地址
     * @param defaultValue 默认地址
     * @return 服务器地址
     */
    public static String getHost(String defaultValue) {
        return (String) getData(HOST_IP_PORT, defaultValue);
    }

    public static void setToken(String token) {
        putData(TOKEN, token);
    }

    public static String getToken() {
        return (String) getData(TOKEN, "");
    }
}
