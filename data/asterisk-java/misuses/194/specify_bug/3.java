public class RtcpReceivedEvent extends AbstractRtcpEvent
{
    public void setPt(String ptString)
    {
        if (ptString == null || ptString.isEmpty())
        {
            this.pt = null;
            return;
        }

        String numericPart;
        int idx = ptString.indexOf('(');
        if (idx > 0)
        {
            numericPart = ptString.substring(0, idx).trim();
        }
        else
        {
            numericPart = ptString.trim();
        }

        try
        {
            this.pt = Long.valueOf(numericPart);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid PT value: " + ptString, e);
        }
    }
}
