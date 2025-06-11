public static synchronized String getContributionId(String callId) {
    final String algorithm = "HmacSHA1";
    try {
        SecretKeySpec sks = new SecretKeySpec(secretKey, algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(sks);
        byte[] contributionId = mac.doFinal(callId.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(32);
        for (int i = 0; i < Math.min(16, contributionId.length); i++) {
            hexString.append(String.format("%02x", contributionId[i] & 0xFF));
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
        return null;
    }
}
