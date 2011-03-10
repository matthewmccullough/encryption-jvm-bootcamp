package com.ambientideas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class KeygenRSA 
{
  public static void main( String[] args ) throws NoSuchAlgorithmException, NoSuchProviderException, IOException
  {
    final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    
    final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
    keyGen.initialize(1024, random);
    
    KeyPair pair = keyGen.generateKeyPair();
    
    final PrivateKey priv = pair.getPrivate();
    final PublicKey pub = pair.getPublic();
    
    //Write the private key to a file
    FileOutputStream privOS = new FileOutputStream("RSAPrivateKey.key");
    privOS.write(priv.getEncoded());
    privOS.close();
    
  //Write the private key to a file
    FileOutputStream publicOS = new FileOutputStream("RSAPublicKey.key");
    publicOS.write(pub.getEncoded());
    publicOS.close();
  }
}
