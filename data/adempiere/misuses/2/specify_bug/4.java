public String decrypt(String value)
{
    if (value == null || value.length() == 0)
        return value;
    boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
    String rawValue = value;
    if (isEncrypted)
        rawValue = value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
    byte[] data = convertHexString(rawValue);
    if (data == null)
    {
        if (isEncrypted)
        {
            log.error("Failed to convert hex string to bytes: {}", rawValue);
            return null;
        }
        return rawValue;
    }
    if (m_cipher == null)
        initCipher();
    if (m_cipher != null && data.length > 0)
    {
        try
        {
            java.security.AlgorithmParameters ap = m_cipher.getParameters();
            m_cipher.init(Cipher.DECRYPT_MODE, m_key, ap);
            byte[] out = m_cipher.doFinal(data);
            return new String(out, java.nio.charset.StandardCharsets.UTF_8);
        }
        catch (java.security.GeneralSecurityException ex)
        {
            log.error("Failed decrypting value", ex);
        }
    }
    return null;
}
