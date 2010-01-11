package com.ambientideas;

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
  public static void main( String[] args ) throws NoSuchAlgorithmException, NoSuchProviderException
  {
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    
    byte[] userSeed = {0x9, 12, 0xA};
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
    random.setSeed(userSeed);
    keyGen.initialize(1024, random);
    
    KeyPair pair = keyGen.generateKeyPair();
    
    PrivateKey priv = pair.getPrivate();
    PublicKey pub = pair.getPublic();
    
    //System.out.println("Random number: " + priv.getEncoded());
  }
}
