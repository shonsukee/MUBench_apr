public class RtcpReceivedEvent extends AbstractRtcpEvent {
    public void setPt(String ptString) {
        if (ptString == null || ptString.isEmpty()) {
            this.pt = null;
            return;
        }
        String trimmed = ptString.trim();
        if (trimmed.startsWith("PT:")) {
            trimmed = trimmed.substring(3).trim();
        }
        String number;
        int idx = trimmed.indexOf('(');
        if (idx > 0) {
            number = trimmed.substring(0, idx).trim();
        } else {
            number = trimmed;
        }
        try {
            this.pt = Long.valueOf(number);
        } catch (NumberFormatException e) {
            this.pt = null;
        }
    }
}
