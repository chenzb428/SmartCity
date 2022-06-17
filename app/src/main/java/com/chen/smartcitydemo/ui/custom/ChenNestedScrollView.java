package com.chen.smartcitydemo.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class ChenNestedScrollView extends NestedScrollView {

    public ChenNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public ChenNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChenNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        int headViewHeight = getChildAt(0).getMeasuredHeight() - getMeasuredHeight();

        boolean isNeedToHideTop = dy > 0 && getScrollY() < headViewHeight;
        if (isNeedToHideTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }
}
