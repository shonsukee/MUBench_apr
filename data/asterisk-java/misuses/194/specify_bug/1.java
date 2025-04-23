public class RtcpReceivedEvent extends AbstractRtcpEvent {
    public void setPt(String ptString) {
        if (ptString == null || ptString.isEmpty()) {
            this.pt = null;
            return;
        }
        String numberString;
        int parenIndex = ptString.indexOf('(');
        if (parenIndex > 0) {
            numberString = ptString.substring(0, parenIndex).trim();
        } else {
            numberString = ptString.trim();
        }
        if (numberString.startsWith("PT:")) {
            numberString = numberString.substring(3).trim();
        }
        try {
            this.pt = Long.parseLong(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid pt value: " + ptString, e);
        }
    }
}
