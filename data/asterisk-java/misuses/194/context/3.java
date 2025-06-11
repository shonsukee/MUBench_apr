public void setPt(String ptString)
{
    if (ptString == null || ptString.isEmpty())
    {
        this.pt = null;
        return;
    }
    int index = ptString.indexOf('(');
    if (index > 0)
    {
        this.pt = Long.parseLong(ptString.substring(0,index));
    }
    else
    {
        this.pt = Long.parseLong(ptString);
    }
}
