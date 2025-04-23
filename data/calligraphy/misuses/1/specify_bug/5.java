public final class CalligraphyUtils {
    static final String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        try (TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, new int[]{attributeId}, 0, 0)) {
            return typedArray.getString(0);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}
