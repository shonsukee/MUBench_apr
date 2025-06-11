public final class CalligraphyUtils {
    static String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        if (attrs == null) {
            return null;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[] { attributeId }, 0, 0);
        try {
            return typedArray.getString(0);
        } finally {
            typedArray.recycle();
        }
    }
}
