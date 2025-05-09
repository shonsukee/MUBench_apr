import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

public class ConfigTools {
    public static String decrypt(PublicKey publicKey, String cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        try {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(
                rsaPublicKey.getModulus(),
                rsaPublicKey.getPublicExponent()
            );
            PrivateKey fakeKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
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
