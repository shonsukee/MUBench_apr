public static String decrypt(PrivateKey privateKey, String cipherText)
        throws Exception {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    if (cipherText == null || cipherText.length() == 0) {
        return cipherText;
    }
    byte[] cipherBytes = java.util.Base64.getDecoder().decode(cipherText);
    byte[] plainBytes = cipher.doFinal(cipherBytes);
    return new String(plainBytes, java.nio.charset.StandardCharsets.UTF_8);
}
