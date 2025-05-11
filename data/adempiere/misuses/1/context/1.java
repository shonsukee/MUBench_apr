public String encrypt(String value)
{
    String clearText = value;
    if(clearText == null)
        clearText = "";
    if(m_cipher == null)
        initCipher();
    if(m_cipher != null)
    {
        try
        {
            m_cipher.init(Cipher.ENCRYPT_MODE, m_key);
            byte[] encBytes = m_cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8));
            String encString = convertToHexString(encBytes);
            return encString;
        }
        catch(Exception ex)
        {
            log.log(Level.INFO, "Problem encrypting string", ex);
        }
    }
    return CLEARVALUE_START + value + CLEARVALUE_END;
}
