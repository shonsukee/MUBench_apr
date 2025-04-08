static string PullFontPathFromStyle(Context context, IAttributeSet attrs, int attributeId)
{
    using (var typedArray = context.ObtainStyledAttributes(attrs, new int[]{attributeId}))
    {
        return typedArray.GetString(0);
    }
}
