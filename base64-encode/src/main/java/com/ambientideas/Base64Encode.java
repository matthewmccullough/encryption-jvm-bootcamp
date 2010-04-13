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

import sun.misc.BASE64Encoder;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class Base64Encode 
{
  public static void main( String[] args ) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
  {
    final String message1 = "Four score and seven years ago";
    
    // Generate the Key Pair
    final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    
    final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
    keyGen.initialize(1024, random);
    
    KeyPair pair = keyGen.generateKeyPair();
    
    final PrivateKey privKey = pair.getPrivate();
    final PublicKey pubKey = pair.getPublic();
    
    
    //Encrypt using the private key
    Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    rsa.init(Cipher.ENCRYPT_MODE, privKey);
    byte[] encryptedBytes = rsa.doFinal(message1.getBytes());
    BASE64Encoder b64e = new sun.misc.BASE64Encoder();
    String base64Encrypted = b64e.encode(encryptedBytes);
    System.out.println("Encrypted text: " + base64Encrypted);
    
    //Decrypt using the private key
    rsa.init(Cipher.DECRYPT_MODE, pubKey);
    byte[] decryptedBytes = rsa.doFinal(encryptedBytes);
    String decryptedText = new String(decryptedBytes);
    System.out.println("Decrypted text: " + decryptedText);
  }
}
