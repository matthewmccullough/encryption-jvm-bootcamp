package com.ambientideas;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class SymmetricEncryptAES
{
  private static final int AES_BIT_STRENGTH = 128;

public static void main( String[] args )
    throws NoSuchAlgorithmException, NoSuchProviderException,
    NoSuchPaddingException, InvalidKeyException,
    IllegalBlockSizeException, BadPaddingException
  {
    final String message1 = "Four score and seven years ago";
    
    long beforeAES = java.lang.System.currentTimeMillis();
    
    //Build a new encryption key
    final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    //256 bit fails on Worldwide Policy File (Strong) but succeeds on Unlimited
    keyGen.init(AES_BIT_STRENGTH);
    final SecretKey aesKey = keyGen.generateKey();
    
    //Set up the cipher
    final Cipher aesCipher = Cipher.getInstance("AES");
    
    //////////////////////////////////////
    //Put the cipher in encryption mode
    aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
    
    //Encrypt and output the base64 data
    byte[] clearText = message1.getBytes();
    byte[] encryptedBytes = aesCipher.doFinal(clearText);
    long afterAES = java.lang.System.currentTimeMillis();
    BASE64Encoder b64e = new sun.misc.BASE64Encoder();
    String base64Encrypted = b64e.encode(encryptedBytes);
    System.out.println("Encrypted text: " + base64Encrypted);
    System.out.println("AES took: " + (afterAES - beforeAES) + "ms");
    
    
    //////////////////////////////////////
    //Put the cipher in decryption mode
    aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
    
    //Decrypt and output the original string
    byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted text: " + decryptedText);
  }
}
