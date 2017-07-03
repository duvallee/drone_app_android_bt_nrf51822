package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.util.DisplayMetrics;
/**
 * Created by duval on 2017-07-03.
 */

public class PixelUtil {
    private PixelUtil() {
    }

    public static int dpToPx(Context context, int dp) {
        int px = Math.round(dp * getPixelScaleFactor(context));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        int dp = Math.round(px / getPixelScaleFactor(context));
        return dp;
    }

    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
