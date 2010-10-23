package com.ambientideas;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class SecureRandomNumber 
{
  public static void main( String[] args ) throws NoSuchAlgorithmException
  {
	int iterations = 10000;
	
	random(iterations);
	prng(iterations);
  }

  private static void random(int iterations) throws NoSuchAlgorithmException {
	long beforeRandom = java.lang.System.currentTimeMillis();
	int randomNum = 0;
	for (int i = 0; i < iterations; i++) {
	    //Setup the random number generator instance
	    Random random = new java.util.Random();
	    
	    //Get the next random number
	    randomNum = random.nextInt();
	}
    
    long afterRandom = java.lang.System.currentTimeMillis();
    
    //System.out.println("Cheap random number: " + randomNum);
    System.out.println("Cheap random number generation time in millis: " + (afterRandom - beforeRandom) + " Iterations: " + iterations);
  }

  private static void prng(int iterations) throws NoSuchAlgorithmException {
	long beforePRNG = java.lang.System.currentTimeMillis();
	int randomNum = 0;
	for (int i = 0; i < iterations; i++) {
	    //Do the expensive one time setup of the
	    // random number generator instance
	    SecureRandom prng = java.security.SecureRandom.getInstance("SHA1PRNG");
	    
	    //Get the next random number
	    randomNum =  prng.nextInt();
	}
    
    long afterPRNG = java.lang.System.currentTimeMillis();
    
    //System.out.println("PRNG random number: " + randomNum);
    System.out.println("PRNG random number generation time in millis: " + (afterPRNG - beforePRNG) + " Iterations: " + iterations);
  }
}
