public final class CalligraphyUtils {
    static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
        final Resources.Theme theme = context.getTheme();
        final TypedValue value = new TypedValue();
        try {
            if (!theme.resolveAttribute(styleId, value, true) || value.resourceId == 0) {
                return null;
            }
            final TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
            try {
                return typedArray.hasValue(0) ? typedArray.getString(0) : null;
            } finally {
                typedArray.recycle();
            }
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}
