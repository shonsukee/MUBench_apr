public String decrypt(String value) {
    if (value == null || value.isEmpty()) {
        return value;
    }
    final String original = value;
    boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
    if (isEncrypted) {
        value = value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
    }
    byte[] data = convertHexString(value);
    if (data == null) {
        if (isEncrypted) {
            log.info("Failed to decrypt encrypted value");
            return null;
        }
        return original;
    }
    if (m_cipher == null) {
        initCipher();
    }
    if (m_cipher != null) {
        try {
            AlgorithmParameters ap = m_cipher.getParameters();
            m_cipher.init(Cipher.DECRYPT_MODE, m_key, ap);
            byte[] out = m_cipher.doFinal(data);
            return new String(out, StandardCharsets.UTF_8);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException ex) {
            log.info("Failed decrypting value", ex);
        }
    }
    return null;
}
