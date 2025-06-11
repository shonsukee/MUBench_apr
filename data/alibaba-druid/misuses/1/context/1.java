import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Base64;

public class ConfigTools {
    public static String decrypt(PrivateKey privateKey, String cipherText)
            throws GeneralSecurityException {
        if (cipherText == null || cipherText.isEmpty()) {
            return cipherText;
        }

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] plainBytes = cipher.doFinal(cipherBytes);

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}
