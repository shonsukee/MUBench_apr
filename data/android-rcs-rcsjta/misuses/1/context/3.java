import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

private static byte[] generateSecretKey() {
    UUID uuid = DeviceUtils.getDeviceUUID(AndroidFactory.getApplicationContext());
    byte[] key;
    if (uuid != null) {
        key = uuid.toString().getBytes(StandardCharsets.UTF_8);
    } else {
        String t = String.valueOf(System.currentTimeMillis());
        key = t.getBytes(StandardCharsets.UTF_8);
    }
    byte[] secretKey = new byte[16];
    if (key != null && key.length >= 16) {
        System.arraycopy(key, 0, secretKey, 0, 16);
    } else if (key != null) {
        System.arraycopy(key, 0, secretKey, 0, key.length);
        Arrays.fill(secretKey, key.length, 16, (byte)'0');
    } else {
        Arrays.fill(secretKey, (byte)'0');
    }
    return secretKey;
}

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
    } catch(Exception e) {
        return null;
    }
}
