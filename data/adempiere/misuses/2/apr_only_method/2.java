public String decrypt(String value)
{
    if(value == null || value.isEmpty())
        return value;
    boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
    if(isEncrypted)
        value = value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
    byte[] data = convertHexString(value);
    if(data == null)
    {
        if(isEncrypted)
        {
            log.info("Failed");
            return null;
        }
        return value;
    }
    if(m_cipher == null)
        initCipher();
    if(m_cipher != null)
    {
        try
        {
            AlgorithmParameters ap = m_cipher.getParameters();
            m_cipher.init(Cipher.DECRYPT_MODE, m_key, ap);
            byte[] out = m_cipher.doFinal(data);
            String retValue = new String(out, java.nio.charset.StandardCharsets.UTF_8);
            return retValue;
        }
        catch(Exception ex)
        {
            log.log(java.util.logging.Level.INFO, "Failed decrypting", ex);
        }
    }
    return null;
}
