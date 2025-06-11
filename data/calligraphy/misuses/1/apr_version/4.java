static string PullFontPathFromStyle(Android.Content.Context context,Android.Util.IAttributeSet attrs,int attributeId)
{
    using(var typedArray=context.ObtainStyledAttributes(attrs,new int[]{attributeId}))
    {
        return typedArray.GetString(0);
    }
}
