static string PullFontPathFromTheme(Context context, int styleId, int attributeId)
{
    var theme = context.Theme;
    var value = new TypedValue();
    theme.ResolveAttribute(styleId, value, true);
    using (var typedArray = theme.ObtainStyledAttributes(value.ResourceId, new int[] { attributeId }))
    {
        return typedArray.GetString(0);
    }
}
