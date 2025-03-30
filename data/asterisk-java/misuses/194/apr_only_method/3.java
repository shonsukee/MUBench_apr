public void setPt(String ptString)
{
    if (ptString == null || ptString.isEmpty())
    {
        this.pt = null;
        return;
    }
    if (ptString.indexOf('(') > 0)
    {
        this.pt = Long.parseLong(ptString.substring(0, ptString.indexOf('(')));
    }
    else
    {
        this.pt = Long.parseLong(ptString);
    }
}
