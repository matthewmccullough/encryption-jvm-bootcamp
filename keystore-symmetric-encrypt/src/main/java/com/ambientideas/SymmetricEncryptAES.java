package com.ambientideas;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.KeyStore;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class SymmetricEncryptAES
{
    private static final String message1 = "Four score and seven years ago";
    private static Cipher aesCipher;
    private static Key aesKey;
    
    public static void main( String[] args )
      throws NoSuchAlgorithmException, NoSuchProviderException,
      NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException, Exception
  {
      loadKeystoreAndKey();
      byte[] encryptedBytes = encrypt();
      decrypt(encryptedBytes);
  }
  
  public static void loadKeystoreAndKey() throws Exception {
      //Get a new encryption key from the keystore
      FileInputStream fis = new FileInputStream("mykeys.keystore");
      KeyStore ks = KeyStore.getInstance("JCEKS");
      ks.load(fis, "password".toCharArray());
      aesKey = ks.getKey("mykey", "password".toCharArray());
      //Set up the cipher
      aesCipher = Cipher.getInstance("AES");
  }
  
  public static byte[] encrypt() throws Exception {
      //////////////////////////////////////
      //Put the cipher in encryption mode
      aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

      //Encrypt and output the base64 data
      byte[] clearText = message1.getBytes();
      byte[] encryptedBytes = aesCipher.doFinal(clearText);
      
      //Base64 Encoded for display only
      BASE64Encoder b64e = new sun.misc.BASE64Encoder();
      String base64Encrypted = b64e.encode(encryptedBytes);
      System.out.println("Encrypted text: " + base64Encrypted);
      
      return encryptedBytes;
  }
  
  public static String decrypt(byte[] encryptedBytes) throws Exception {
      //////////////////////////////////////
      //Put the cipher in decryption mode
      aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

      //Decrypt and output the original string
      byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
      String decryptedText = new String(decryptedBytes);
      System.out.println("Decrypted text: " + decryptedText);
      
      return decryptedText;
  }
}
