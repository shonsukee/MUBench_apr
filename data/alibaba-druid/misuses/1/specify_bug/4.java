import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class ConfigTools {
    public static String decrypt(PrivateKey privateKey, String cipherText)
            throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        if (cipherText == null || cipherText.isEmpty()) {
            return cipherText;
        }

        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}
