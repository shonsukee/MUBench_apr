import java.security.PublicKey;
import java.security.Key;
import java.security.KeyFactory;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class ConfigTools {
    public static String decrypt(PublicKey publicKey, String cipherText)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        try {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(
                    rsaPublicKey.getModulus(),
                    rsaPublicKey.getPublicExponent()
            );
            Key fakeKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            cipher.init(Cipher.DECRYPT_MODE, fakeKey);
        }

        if (cipherText == null || cipherText.isEmpty()) {
            return cipherText;
        }

        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}
