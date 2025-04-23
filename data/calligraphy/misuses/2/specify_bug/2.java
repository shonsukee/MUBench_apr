package com.example.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.StyleRes;

public final class CalligraphyUtils {

    private CalligraphyUtils() {
        // Prevent instantiation
    }

    public static String pullFontPathFromTheme(Context context, @StyleRes int styleResId, @AttrRes int attrResId) {
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();

        if (!theme.resolveAttribute(styleResId, typedValue, true)) {
            throw new Resources.NotFoundException("Style resource ID " + styleResId + " not found in theme");
        }

        TypedArray typedArray = null;
        try {
            typedArray = theme.obtainStyledAttributes(typedValue.resourceId, new int[]{attrResId});
            if (!typedArray.hasValue(0)) {
                throw new Resources.NotFoundException("Attribute ID " + attrResId + " not defined in style");
            }
            String fontPath = typedArray.getString(0);
            if (fontPath == null) {
                throw new Resources.NotFoundException("Failed to retrieve string for attribute ID " + attrResId);
            }
            return fontPath;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }
}
