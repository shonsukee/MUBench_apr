static final String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
    final TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[]{attributeId});
    try {
        return typedArray.getString(1);
    } finally {
        typedArray.recycle();
    }
}
