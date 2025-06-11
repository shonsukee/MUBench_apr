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
        
        return hexString.toString();
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
        return null;
    }
}
