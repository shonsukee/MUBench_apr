public final class CalligraphyUtils {
    static final String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        String fontPath;
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{attributeId});
        try {
            fontPath = a.getString(0);
        } finally {
            a.recycle();
        }
        if (fontPath == null) {
            TypedArray themeArray = context.getTheme().obtainStyledAttributes(new int[]{attributeId});
            try {
                fontPath = themeArray.getString(0);
            } finally {
                themeArray.recycle();
            }
        }
        return fontPath;
    }
}
