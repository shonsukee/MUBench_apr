private static byte[] generateSecretKey() {
    UUID uuid = DeviceUtils.getDeviceUUID(AndroidFactory.getApplicationContext());
    byte[] key;
    if (uuid != null) {
        key = uuid.toString().getBytes(StandardCharsets.UTF_8);
    } else {
        String t = "" + System.currentTimeMillis();
        key = t.getBytes(StandardCharsets.UTF_8);
    }
    byte[] secretKey = new byte[16];
    for (int i = 0; i < 16; i++) {
        if (key != null && key.length >= 16) {
            secretKey[i] = key[i];
        } else {
            secretKey[i] = '0';
        }
    }
    return secretKey;
}

public synchronized static String getContributionId(String callId) {
    try {
        SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
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
    } catch(Exception e) {
        return null;
    }
}
