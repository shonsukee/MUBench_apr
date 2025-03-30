public static String encrypt(byte[] keyBytes, String plainText) throws Exception {
	PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	KeyFactory factory = KeyFactory.getInstance("RSA");
	PrivateKey privateKey = factory.generatePrivate(spec);
	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
	String encryptedString = java.util.Base64.getEncoder().encodeToString(encryptedBytes);
	return encryptedString;
}
