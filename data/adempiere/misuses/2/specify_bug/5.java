import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public String decrypt(String value)
{
    if (value == null || value.isEmpty())
        return value;
    boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
    String content = isEncrypted
        ? value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length())
        : value;
    byte[] data = convertHexString(content);
    if (data == null)
    {
        if (isEncrypted)
        {
            log.info("Failed to decrypt encrypted value");
            return null;
        }
        return value;
    }
    if (m_cipher == null)
        initCipher();
    if (m_cipher != null && data.length > 0)
    {
        try
        {
            AlgorithmParameters ap = m_cipher.getParameters();
            m_cipher.init(Cipher.DECRYPT_MODE, m_key, ap);
            byte[] out = m_cipher.doFinal(data);
            return new String(out, StandardCharsets.UTF_8);
        }
        catch (GeneralSecurityException ex)
        {
            log.info("Failed decrypting", ex);
        }
    }
    return null;
}
