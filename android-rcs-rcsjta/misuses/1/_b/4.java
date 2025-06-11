import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

private static final byte[] secretKey = generateSecretKey();

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
    int length = key != null ? Math.min(key.length, 16) : 0;
    if (length > 0) {
        System.arraycopy(key, 0, secretKey, 0, length);
    }
    if (length < 16) {
        for (int i = length; i < 16; i++) {
            secretKey[i] = (byte)'0';
        }
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
        int len = Math.min(16, contributionId.length);
        for (int i = 0; i < len; i++) {
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
