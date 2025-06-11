public final class CalligraphyUtils {
    static String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
        Resources.Theme theme = context.getTheme();
        TypedValue value = new TypedValue();
        if (!theme.resolveAttribute(styleId, value, true)) {
            return null;
        }
        TypedArray typedArray = null;
        try {
            typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
            return typedArray.getString(0);
        } catch (Resources.NotFoundException e) {
            return null;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }
}
