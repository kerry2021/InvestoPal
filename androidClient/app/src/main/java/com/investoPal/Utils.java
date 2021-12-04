package com.investoPal;

import android.content.Context;
import android.util.TypedValue;

public class Utils {
    //A collection of useful functions that deal with some conversions within the context of android studio

    private Context context;

    Utils(Context context){
        this.context = context;
    }

    public int DpToPixel(int dp){
        int conversion_rate = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                context.getResources().getDisplayMetrics());
        return dp * conversion_rate;
    }
}
