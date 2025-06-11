public final class CalligraphyUtils {
    static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
        final Resources.Theme theme = context.getTheme();
        final TypedValue value = new TypedValue();

        if (!theme.resolveAttribute(styleId, value, true)) {
            return null;
        }
        final int[] attrs = new int[]{attributeId};
        TypedArray typedArray = null;
        try {
            typedArray = theme.obtainStyledAttributes(value.resourceId, attrs);
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
