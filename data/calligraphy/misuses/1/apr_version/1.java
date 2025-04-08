public static string PullFontPathFromStyle(Context context, IAttributeSet attrs, int attributeId)
{
    var typedArray = context.Theme.ObtainStyledAttributes(attrs, new int[] { attributeId }, 0, 0);
    try
    {
        return typedArray.GetString(0);
    }
    finally
    {
        typedArray.Recycle();
    }
}
