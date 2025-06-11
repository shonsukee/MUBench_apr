    static final String pullFontPathFromStyle(Context context, AttributeSet attrs, int attributeId) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[]{attributeId});
        try {
            return typedArray.getString(0);
        } finally {
            typedArray.recycle();
        }
    }
