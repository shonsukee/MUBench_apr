import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ContributionUtil {
    private static final byte[] secretKey = /* your secret key here */;

    public synchronized static String getContributionId(String callId) {
        try {
            SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(sks);
            byte[] contributionId = mac.doFinal(callId.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(32);
            for (int i = 0; i < 16 && i < contributionId.length; i++) {
                String hex = Integer.toHexString(0xFF & contributionId[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
