package UseITJavaEncryptDecryptTool.Launch;



import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionDecryption {
	
	private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;
    private static BASE64Encoder base64encoder = new BASE64Encoder();
    private static BASE64Decoder base64decoder = new BASE64Decoder();
    private static SecretKeySpec skeySpec = null;
	
	private static EncryptionDecryption theInstance;
	public static EncryptionDecryption getInstance() throws Exception
	{
		if( theInstance == null){
			theInstance = new EncryptionDecryption();
		}
		return theInstance;
	}
	
	
	public EncryptionDecryption() throws Exception
    {
    	setUp();
    }

    private static void setUp() throws Exception {
   	 	//key = KeyGenerator.getInstance(algorithm).generateKey();
   	 	//String keyString = base64encoder.encode(key.getEncoded());
   	 	//System.out.println("keyString : " + keyString);
    	String keyString = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
    	byte[] keyB = base64decoder.decodeBuffer(keyString);
    	key = new SecretKeySpec(keyB, "DESede");
    	
    	//skeySpec = new SecretKeySpec(key, algorithm);
   	 	//String keyString = "Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I";
   	 	//key = base64decoder.decodeBuffer(keyString);
   	 	
        cipher = Cipher.getInstance("DESede");
    	//cipher = Cipher.getInstance(algorithm);
    }
    
    public static String encrypt(String input) throws InvalidKeyException, 
           BadPaddingException,
           IllegalBlockSizeException {
    	cipher.init(Cipher.ENCRYPT_MODE, key);
    	byte[] inputBytes = input.getBytes();
    	byte[] encryptionBytes = cipher.doFinal(inputBytes);
    	String encodedString = base64encoder.encode(encryptionBytes);
    	return encodedString;
    }

    public static String decrypt(String encryptedString) throws InvalidKeyException, 
           BadPaddingException,
           IllegalBlockSizeException, IOException, NullPointerException {
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	byte[] encryptedBytes = base64decoder.decodeBuffer(encryptedString);
    	byte[] recoveredBytes = cipher.doFinal(encryptedBytes);
    	String recovered = new String(recoveredBytes);
    	return recovered;
    }
}

