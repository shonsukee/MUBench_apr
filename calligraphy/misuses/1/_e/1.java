public final class CalligraphyUtils {
    private static final String TAG = CalligraphyUtils.class.getSimpleName();

    static String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, new int[]{attributeId});
            return typedArray.getString(0);
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Failed to obtain styled attribute for id: " + attributeId, e);
            return null;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }
}
