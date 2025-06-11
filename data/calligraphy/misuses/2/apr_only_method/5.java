static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
    final Resources.Theme theme = context.getTheme();
    final TypedValue value = new TypedValue();
    if (!theme.resolveAttribute(styleId, value, true)) {
        return null;
    }
    final TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
    try {
        return typedArray.getString(0);
    } finally {
        typedArray.recycle();
    }
}
