public class RtcpReceivedEvent extends AbstractRtcpEvent {
    public void setPt(String ptString) {
        if (ptString == null) {
            this.pt = null;
            return;
        }
        ptString = ptString.trim();
        if (ptString.isEmpty()) {
            this.pt = null;
            return;
        }
        final String prefix = "PT:";
        if (ptString.startsWith(prefix)) {
            ptString = ptString.substring(prefix.length()).trim();
        }
        int idx = ptString.indexOf('(');
        String numberPart = (idx > 0) ? ptString.substring(0, idx).trim() : ptString;
        try {
            this.pt = Long.parseLong(numberPart);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid payload type: " + numberPart, e);
        }
    }
}
