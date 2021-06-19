package com.chen.smartcity.utils;

public class UrlUtils {

    public static String createHomeRecommendUrl(int id) {
        return "prod-api/press/press/list?type=" + id;
    }
}
