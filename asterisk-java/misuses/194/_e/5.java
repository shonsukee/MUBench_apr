public class RtcpReceivedEvent extends AbstractRtcpEvent
{
    public void setPt(String ptString)
    {
        // Format is "PT: %d(%s)"
        if (ptString == null || ptString.isEmpty())
        {
            this.pt = null;
            return;
        }

        String numericPart;
        int parenIndex = ptString.indexOf('(');
        if (parenIndex > 0)
        {
            numericPart = ptString.substring(0, parenIndex);
        }
        else
        {
            numericPart = ptString;
        }

        // Remove "PT: " prefix if present
        numericPart = numericPart.replaceFirst("^PT:\\s*", "");

        try
        {
            this.pt = Long.valueOf(numericPart);
        }
        catch (NumberFormatException e)
        {
            this.pt = null;
        }
    }
}
