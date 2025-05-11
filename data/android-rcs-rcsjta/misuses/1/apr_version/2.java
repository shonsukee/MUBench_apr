import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

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

        String id = hexString.toString();
        return id;
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
        return null;
    }
}
