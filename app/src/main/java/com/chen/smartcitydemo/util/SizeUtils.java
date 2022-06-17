package com.chen.smartcitydemo.util;

import android.content.Context;
import android.util.TypedValue;

public class SizeUtils {

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }
}
