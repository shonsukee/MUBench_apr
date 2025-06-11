public void setPt(String ptString)
{
    if (ptString == null || ptString.isEmpty())
    {
        this.pt = null;
        return;
    }
    int idx = ptString.indexOf('(');
    if (idx > 0)
    {
        this.pt = Long.parseLong(ptString.substring(0, idx));
    }
    else
    {
        this.pt = Long.parseLong(ptString);
    }
}
