static string PullFontPathFromTheme(Android.Content.Context context, int styleId, int attributeId)
{
    var theme = context.Theme;
    var value = new Android.Util.TypedValue();
    theme.ResolveAttribute(styleId, value, true);
    var typedArray = context.ObtainStyledAttributes(value.ResourceId, new int[] { attributeId });
    try
    {
        return typedArray.GetString(0);
    }
    finally
    {
        typedArray.Recycle();
    }
}
