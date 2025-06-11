public static String decrypt(PublicKey publicKey, String cipherText) throws Exception {
	Cipher cipher = Cipher.getInstance("RSA");
	try {
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
	} catch (InvalidKeyException e) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
		Key fakePublicKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
		cipher.init(Cipher.DECRYPT_MODE, fakePublicKey);
	}
	if (cipherText == null || cipherText.length() == 0) {
		return cipherText;
	}
	byte[] cipherBytes = java.util.Base64.getDecoder().decode(cipherText);
	byte[] plainBytes = cipher.doFinal(cipherBytes);
	return new String(plainBytes, java.nio.charset.StandardCharsets.UTF_8);
}
