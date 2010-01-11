package com.ambientideas;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class SecureRandomNumber 
{
  public static void main( String[] args ) throws NoSuchAlgorithmException
  {
    //Do the expensive one time setup of the
    // random number generator instance
    SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
    
    //Get the next random number
    String randomNum = new Integer( prng.nextInt() ).toString();
    
    System.out.println("Random number: " + randomNum);
  }
}
