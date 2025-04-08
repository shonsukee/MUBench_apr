static string PullFontPathFromStyle(Context context, IAttributeSet attrs, int attributeId)
{
    using (var a = context.ObtainStyledAttributes(attrs, new int[] { attributeId }))
    {
        return a.GetString(0);
    }
}
