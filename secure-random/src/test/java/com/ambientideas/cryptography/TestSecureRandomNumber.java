package com.ambientideas.cryptography;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Use the SecureRandom java security class to generate a more expensive, but
 * cryptographically secure random number.
 */
public class TestSecureRandomNumber {
    @Test
    public void testRandom() throws NoSuchAlgorithmException {
        int iterations = 1;
        
        long utilRandomTime = javaUtilRandom(iterations);
        long javaSecureRandomTime = javaSecureRandomPRNG(iterations);
        
        Assert.assertTrue(utilRandomTime < 1);
        Assert.assertTrue(javaSecureRandomTime > 40);
    }

    @Test
    public void test10000Random() throws NoSuchAlgorithmException {
        int iterations = 1000000;

        javaUtilRandom(iterations);
        javaSecureRandomPRNG(iterations);
    }

    private static long javaUtilRandom(int iterations) throws NoSuchAlgorithmException {
        long beforeRandom = java.lang.System.currentTimeMillis();
        @SuppressWarnings("unused")
        int randomNum = 0;
        for (int i = 0; i < iterations; i++) {
            // Setup the random number generator instance
            Random random = new java.util.Random();

            // Get the next random number
            randomNum = random.nextInt();
        }

        long afterRandom = java.lang.System.currentTimeMillis();

        // System.out.println("Cheap random number: " + randomNum);
        long duration = afterRandom - beforeRandom;
        System.out.println("Cheap random number generation time in millis: "
                + duration + ", for Iterations: " + iterations);
        return duration;
    }

    private static long javaSecureRandomPRNG(int iterations) throws NoSuchAlgorithmException {
        long beforePRNG = java.lang.System.currentTimeMillis();
        @SuppressWarnings("unused")
        int randomNum = 0;
        for (int i = 0; i < iterations; i++) {
            // Do the expensive one time setup of the
            // random number generator instance
            SecureRandom prng = java.security.SecureRandom
                    .getInstance("SHA1PRNG");

            // Get the next random number
            randomNum = prng.nextInt();
        }

        long afterPRNG = java.lang.System.currentTimeMillis();
        long duration = afterPRNG - beforePRNG;
        
        // System.out.println("PRNG random number: " + randomNum);
        System.out.println("PRNG random number generation time in millis: "
                + duration + ", for Iterations: " + iterations);
        return duration;
    }
}
