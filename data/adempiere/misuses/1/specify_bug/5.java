public String encrypt(String value)
{
    String clearText = value;
    if (clearText == null)
        clearText = "";
    //	Init
    if (m_cipher == null)
        initCipher();
    //	Encrypt
    if (m_cipher != null)
    {
        try
        {
            m_cipher.init(Cipher.ENCRYPT_MODE, m_key);
            byte[] encBytes = m_cipher.doFinal(clearText.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            String encString = convertToHexString(encBytes);
            // globalqss - [ 1577737 ] Security Breach - show database password
            // log.log(Level.ALL, value + " => " + encString);
            return encString;
        }
        catch (java.security.GeneralSecurityException ex)
        {
            // log.log(Level.INFO, value, ex);
            log.log(Level.INFO, "Problem encrypting string", ex);
        }
    }
    //	Fallback
    return CLEARVALUE_START + clearText + CLEARVALUE_END;
}	//	encrypt
