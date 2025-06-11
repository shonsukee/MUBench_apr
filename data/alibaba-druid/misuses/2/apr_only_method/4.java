import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public static String encrypt(byte[] keyBytes, String plainText)
		throws Exception {
	PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	KeyFactory factory = KeyFactory.getInstance("RSA");
	PrivateKey privateKey = factory.generatePrivate(spec);
	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
	String encryptedString = java.util.Base64.getEncoder().encodeToString(encryptedBytes);
	return encryptedString;
}
