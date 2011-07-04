package com.ambientideas;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class EncryptRSA 
{
    @Test
  public void testEncryptRSA() throws NoSuchAlgorithmException, NoSuchProviderException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
  {
    final String message1 = "Four score and seven years ago";
    
    // Generate the Key Pair
    final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    
    final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
    keyGen.initialize(1024, random);
    
    KeyPair pair = keyGen.generateKeyPair();
    
    final PrivateKey privKey = pair.getPrivate();
    final PublicKey pubKey = pair.getPublic();
    
    
    //Encrypt using the public key
    //Mode choices (ECB, CBC): http://download.oracle.com/javase/1.5.0/docs/guide/security/jce/JCERefGuide.html
    Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    rsa.init(Cipher.ENCRYPT_MODE, pubKey);
    byte[] encryptedBytes = rsa.doFinal(message1.getBytes("UTF8"));
    String base64Encrypted = Base64.encodeBase64String(encryptedBytes);
    System.out.println("Encrypted text: " + base64Encrypted);
    
    //Decrypt using the private key
    rsa.init(Cipher.DECRYPT_MODE, privKey);
    byte[] decryptedBytes = rsa.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted text: " + decryptedText);
  }
}
