package org.compiere.util;

import java.math.*;
import java.security.*;
import java.sql.Timestamp;
import java.util.logging.*;
import javax.crypto.*;

public class Secure implements SecureInterface
{
	public static int hash(String key)
	{
		long tableSize = 2147483647;
		long hashValue = 0;
		for (int i = 0; i < key.length(); i++)
			hashValue = (37 * hashValue) + (key.charAt(i) - 31);
		hashValue %= tableSize;
		if (hashValue < 0)
			hashValue += tableSize;
		int retValue = (int) hashValue;
		return retValue;
	}

	public static String convertToHexString(byte[] bytes)
	{
		int size = bytes.length;
		StringBuffer buffer = new StringBuffer(size * 2);
		for (int i = 0; i < size; i++)
		{
			int x = bytes[i];
			if (x < 0)
				x += 256;
			String tmp = Integer.toHexString(x);
			if (tmp.length() == 1)
				buffer.append("0");
			buffer.append(tmp);
		}
		return buffer.toString();
	}

	public static byte[] convertHexString(String hexString)
	{
		if (hexString == null || hexString.length() == 0)
			return null;
		int size = hexString.length() / 2;
		byte[] retValue = new byte[size];
		String inString = hexString.toLowerCase();
		try
		{
			for (int i = 0; i < size; i++)
			{
				int index = i * 2;
				int ii = Integer.parseInt(inString.substring(index, index + 2), 16);
				retValue[i] = (byte) ii;
			}
			return retValue;
		}
		catch (Exception e)
		{
			log.finest(hexString + " - " + e.getLocalizedMessage());
		}
		return null;
	}

	public Secure()
	{
		initCipher();
	}

	private Cipher m_cipher = null;
	private SecretKey m_key = null;
	private MessageDigest m_md = null;
	private static Logger log = Logger.getLogger(Secure.class.getName());

	private synchronized void initCipher()
	{
		if (m_cipher != null)
			return;
		Cipher cc = null;
		try
		{
			cc = Cipher.getInstance("DES/ECB/PKCS5Padding");
			if (false)
			{
				KeyGenerator keygen = KeyGenerator.getInstance("DES");
				m_key = keygen.generateKey();
				byte[] key = m_key.getEncoded();
				StringBuffer sb = new StringBuffer("Key ").append(m_key.getAlgorithm()).append("(").append(key.length).append(")= ");
				for (int i = 0; i < key.length; i++)
					sb.append(key[i]).append(",");
				log.info(sb.toString());
			}
			else
				m_key = new javax.crypto.spec.SecretKeySpec(new byte[] { 100, 25, 28, -122, -26, 94, -3, -26 }, "DES");
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		m_cipher = cc;
	}

	public String encrypt(String value)
	{
		String clearText = value;
		if (clearText == null)
			clearText = "";
		if (m_cipher == null)
			initCipher();
		if (m_cipher != null)
		{
			try
			{
				m_cipher.init(Cipher.ENCRYPT_MODE, m_key);
				byte[] encBytes = m_cipher.doFinal(clearText.getBytes());
				String encString = convertToHexString(encBytes);
				return encString;
			}
			catch (Exception ex)
			{
				log.log(Level.INFO, "Problem encrypting string", ex);
			}
		}
		return CLEARVALUE_START + value + CLEARVALUE_END;
	}

	public String decrypt(String value)
	{
		if (value == null || value.length() == 0)
			return value;
		boolean isEncrypted = value.startsWith(ENCRYPTEDVALUE_START) && value.endsWith(ENCRYPTEDVALUE_END);
		if (isEncrypted)
			value = value.substring(ENCRYPTEDVALUE_START.length(), value.length() - ENCRYPTEDVALUE_END.length());
		byte[] data = convertHexString(value);
		if (data == null)
		{
			if (isEncrypted)
			{
				log.info("Failed");
				return null;
			}
			return value;
		}
		if (m_cipher == null)
			initCipher();
		if (m_cipher != null && value != null && value.length() > 0)
		{
			try
			{
				m_cipher.init(Cipher.DECRYPT_MODE, m_key);
				byte[] out = m_cipher.doFinal(data);
				String retValue = new String(out);
				return retValue;
			}
			catch (Exception ex)
			{
				log.info("Failed decrypting " + ex.toString());
			}
		}
		return null;
	}

	public Integer encrypt(Integer value)
	{
		return value;
	}

	public Integer decrypt(Integer value)
	{
		return value;
	}

	public BigDecimal encrypt(BigDecimal value)
	{
		return value;
	}

	public BigDecimal decrypt(BigDecimal value)
	{
		return value;
	}

	public Timestamp encrypt(Timestamp value)
	{
		return value;
	}

	public Timestamp decrypt(Timestamp value)
	{
		return value;
	}

	public String getDigest(String value)
	{
		if (m_md == null)
		{
			try
			{
				m_md = MessageDigest.getInstance("MD5");
			}
			catch (NoSuchAlgorithmException nsae)
			{
				nsae.printStackTrace();
			}
		}
		m_md.reset();
		byte[] input = value.getBytes();
		m_md.update(input);
		byte[] output = m_md.digest();
		m_md.reset();
		return convertToHexString(output);
	}

	public boolean isDigest(String value)
	{
		if (value == null || value.length() != 32)
			return false;
		return (convertHexString(value) != null);
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer("Secure[");
		sb.append(m_cipher).append("]");
		return sb.toString();
	}
}
