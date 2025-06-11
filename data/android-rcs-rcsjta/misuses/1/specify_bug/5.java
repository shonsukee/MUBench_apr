public static String getContributionId(String callId) {
    try {
        SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(sks);
        byte[] contributionId = mac.doFinal(callId.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(32);
        for (int i = 0; i < 16 && i < contributionId.length; i++) {
            hexString.append(String.format("%02x", contributionId[i] & 0xFF));
        }
        
        return hexString.toString();
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
        throw new IllegalStateException("Failed to generate contribution ID", e);
    }
}
