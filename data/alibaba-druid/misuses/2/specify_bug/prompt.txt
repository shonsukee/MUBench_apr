## Instruction
You are a software engineer specializing in REST API.
Use the guidelines below to make any necessary modifications.

### Modification Procedure
0. First, familiarise yourself with the following steps and ### Notes.
1. Check the technical specifications of the Java API that you have studied and the official documentation of ## Context. If you don't know, output the ### Input Code as it is.
2. Based on the technical specifications of the Java API you have reviewed in step 1, identify the code according to the deprecated specifications contained in the ### Input Code. In this case, the deprecated specifications are the Java API calls that have been deprecated. If no code according to the deprecated specification is found, identify code with maintainability issues. If you are not sure, output the ### Input Code as it is.
3. If you find code according to the deprecated specification or maintainability issues in step 2, check the technical specifications in the Java API that you have studied and the official documentation of ## Context. If you are not sure, output the ### Input Code as it is.
4. With attention to the points listed in ### Notes below, modify the code identified in step 2 to follow the recommended specification analysed in step 3.
5. Verify again that the modified code works correctly.
6. If you determine that it works correctly, output the modified code.
7. If it is judged to fail, output the ### Input Code as it is.
8. If you are not sure, output the ### Input Code as it is.

### Notes.
- You must follow the ## Context.
- ### Java API documentation is official documentation and must follow.
- Be sure to fix any maintainability issues that exist.
- If Java API documentation include throws Exception, it must be handled.
- Bug exists in line 8 of the ## Input code.

## Input Code
```java
public class ConfigTools {
	public static String encrypt(byte[] keyBytes, String plainText)
			throws Exception {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = factory.generatePrivate(spec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
		String encryptedString = Base64.byteArrayToBase64(encryptedBytes);

		return encryptedString;
	}
}
```

## Context
### Java API documentation
```
init
public final void init(int opmode,
                       Key key)
                throws InvalidKeyException
Initializes this cipher with a key.
The cipher is initialized for one of the following four operations: encryption, decryption, key wrapping or key unwrapping, depending on the value of opmode.

If this cipher requires any algorithm parameters that cannot be derived from the given key, the underlying cipher implementation is supposed to generate the required parameters itself (using provider-specific default or random values) if it is being initialized for encryption or key wrapping, and raise an InvalidKeyException if it is being initialized for decryption or key unwrapping. The generated parameters can be retrieved using getParameters or getIV (if the parameter is an IV).

If this cipher requires algorithm parameters that cannot be derived from the input parameters, and there are no reasonable provider-specific default values, initialization will necessarily fail.

If this cipher (including its underlying feedback or padding scheme) requires any random bytes (e.g., for parameter generation), it will get them using the SecureRandom implementation of the highest-priority installed provider as the source of randomness. (If none of the installed providers supply an implementation of SecureRandom, a system-provided source of randomness will be used.)

Note that when a Cipher object is initialized, it loses all previously-acquired state. In other words, initializing a Cipher is equivalent to creating a new instance of that Cipher and initializing it.

Parameters:
opmode - the operation mode of this cipher (this is one of the following: ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE or UNWRAP_MODE)
key - the key
Throws:
InvalidKeyException - if the given key is inappropriate for initializing this cipher, or requires algorithm parameters that cannot be determined from the given key, or if the given key has a keysize that exceeds the maximum allowable keysize (as determined from the configured jurisdiction policy files).
UnsupportedOperationException - if (@code opmode} is WRAP_MODE or UNWRAP_MODE but the mode is not implemented by the underlying CipherSpi.
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
