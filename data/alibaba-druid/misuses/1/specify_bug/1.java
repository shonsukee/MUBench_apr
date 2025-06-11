import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class ConfigTools {
    public static String decrypt(PublicKey publicKey, String cipherText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        try {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            try {
                RSAPublicKeySpec spec = new RSAPublicKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
                PublicKey freshPublicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
                cipher.init(Cipher.DECRYPT_MODE, freshPublicKey);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                throw new InvalidKeyException("Failed to initialize cipher with workaround key", ex);
            }
        }

        if (cipherText == null || cipherText.isEmpty()) {
            return cipherText;
        }

        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}
