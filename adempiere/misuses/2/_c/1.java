	public String decrypt (String value)
	{
		if (value == null || value.isEmpty())
			return value;
		boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
		if (isEncrypted)
			value = value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
		//	Needs to be hex String	
		byte[] data = convertHexString(value);
		if (data == null)	//	cannot decrypt
		{
			if (isEncrypted)
			{
				log.info("Failed");
				return null;
			}
			//	assume not encrypted
			return value;
		}
		//	Init
		if (m_cipher == null)
			initCipher();
		//	Encrypt
		if (m_cipher != null && !value.isEmpty())
		{
			try
			{
				AlgorithmParameters ap = m_cipher.getParameters();
				m_cipher.init(Cipher.DECRYPT_MODE, m_key, ap);
				byte[] out = m_cipher.doFinal(data);
				String retValue = new String(out, java.nio.charset.StandardCharsets.UTF_8);
				// globalqss - [ 1577737 ] Security Breach - show database password
				// log.log (Level.ALL, value + " => " + retValue);
				return retValue;
			}
			catch (Exception ex)
			{
				log.info("Failed decrypting " + ex.toString());
			}
		}
		return null;
	}	//	decrypt
