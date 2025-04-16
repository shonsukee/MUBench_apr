static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
    final Resources.Theme theme = context.getTheme();
    final TypedValue value = new TypedValue();

    theme.resolveAttribute(styleId, value, true);
    final TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
    try {
        return typedArray.getString(0);
    } finally {
        typedArray.recycle();
    }
}
