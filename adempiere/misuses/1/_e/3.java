import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.logging.Level;

public String encrypt(String value) {
    String clearText = (value != null) ? value : "";
    if (m_cipher == null) {
        initCipher();
    }
    if (m_cipher != null) {
        try {
            m_cipher.init(Cipher.ENCRYPT_MODE, m_key);
            byte[] encBytes = m_cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8));
            String encString = convertToHexString(encBytes);
            return encString;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            log.log(Level.INFO, "Problem encrypting string", ex);
        }
    }
    return CLEARVALUE_START + clearText + CLEARVALUE_END;
}
