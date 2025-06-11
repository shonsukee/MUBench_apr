import javax.crypto.Cipher;
import javax.crypto.AlgorithmParameters;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YourClass {
    private static final Logger log = Logger.getLogger(YourClass.class.getName());
    private static final String ENCRYPTEDVALUE_START = "{ENC}";
    private static final String ENCRYPTEDVALUE_END = "{/ENC}";
    private Cipher m_cipher;
    private java.security.Key m_key;

    public String decrypt(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
        String payload = value;
        if (isEncrypted) {
            payload = value.substring(
                ENCRYPTEDVALUE_START.length(),
                value.length() - ENCRYPTEDVALUE_END.length()
            );
        }
        byte[] data = convertHexString(payload);
        if (data == null) {
            if (isEncrypted) {
                log.log(Level.WARNING, "Failed decrypting encrypted value: {0}", payload);
                return null;
            }
            return value;
        }
        if (m_cipher == null) {
            initCipher();
        }
        if (m_cipher != null && !payload.isEmpty()) {
            try {
                AlgorithmParameters params = m_cipher.getParameters();
                m_cipher.init(Cipher.DECRYPT_MODE, m_key, params);
                byte[] out = m_cipher.doFinal(data);
                String retValue = new String(out, StandardCharsets.UTF_8);
                return retValue;
            } catch (InvalidKeyException
                    | InvalidAlgorithmParameterException
                    | IllegalBlockSizeException
                    | BadPaddingException ex) {
                log.log(Level.WARNING, "Failed decrypting payload", ex);
            }
        }
        return null;
    }

    private void initCipher() {
        // initialization logic for m_cipher and m_key
    }

    private byte[] convertHexString(String hex) {
        // implementation converting hex string to byte[]
        return null;
    }
}
