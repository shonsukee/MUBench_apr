package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by chris on 20/12/2013
 * Project: Calligraphy
 */
public final class CalligraphyUtils {

    public static final boolean applyFontToTextView(final TextView textView, final Typeface typeface) {
        if (textView == null || typeface == null) return false;
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return true;
    }

    public static final boolean applyFontToTextView(final Context context, final TextView textView, final String filePath) {
        if (textView == null || context == null) return false;
        final AssetManager assetManager = context.getAssets();
        final Typeface typeface = TypefaceUtils.load(assetManager, filePath);
        return applyFontToTextView(textView, typeface);
    }

    public static final void applyFontToTextView(final Context context, final TextView textView, final CalligraphyConfig config) {
        if (context == null || textView == null || config == null) return;
        if (!config.isFontSet()) return;
        applyFontToTextView(context, textView, config.getFontPath());
    }

    public static void applyFontToTextView(final Context context, final TextView textView, final CalligraphyConfig config, final String textViewFont) {
        if (context == null || textView == null || config == null) return;
        if (!TextUtils.isEmpty(textViewFont) && applyFontToTextView(context, textView, textViewFont)) {
            return;
        }
        applyFontToTextView(context, textView, config);
    }

    static final String pullFontPath(Context context, AttributeSet attrs, int attributeId) {
        String attributeName;
        try {
            attributeName = context.getResources().getResourceEntryName(attributeId);
        } catch (Resources.NotFoundException e) {
            // invalid attribute ID
            return null;
        }
        final int stringResourceId = attrs.getAttributeResourceValue(null, attributeName, -1);
        return stringResourceId > 0
                ? context.getString(stringResourceId)
                : attrs.getAttributeValue(null, attributeName);
    }

    static final String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, new int[]{attributeId});
            TypedValue typedValue = typedArray.peekValue(0);
            if (typedValue != null) {
                if (typedValue.resourceId != 0) {
                    return context.getString(typedValue.resourceId);
                } else if (typedValue.string != null) {
                    return typedValue.string.toString();
                }
            }
            return null;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
        Resources.Theme theme = context.getTheme();
        TypedValue value = new TypedValue();
        if (!theme.resolveAttribute(styleId, value, true)) {
            return null;
        }
        TypedArray typedArray = null;
        try {
            typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
            TypedValue typedValue = typedArray.peekValue(0);
            if (typedValue != null) {
                if (typedValue.resourceId != 0) {
                    return context.getString(typedValue.resourceId);
                } else if (typedValue.string != null) {
                    return typedValue.string.toString();
                }
            }
            return null;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    private CalligraphyUtils() {
    }
}
