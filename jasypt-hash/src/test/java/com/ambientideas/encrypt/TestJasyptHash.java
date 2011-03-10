package com.ambientideas.encrypt;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestJasyptHash {
    public static String DATA = "Four score and seven years ago.";

    @Test
    public void testHashConsistencyBetweenRunsWithZeroSalt() throws Exception {
        final String expectedHash = ExampleJasyptHash.hashDefaultAlgorithmZeroSalt(DATA);
        String hashAgain = ExampleJasyptHash.hashDefaultAlgorithmZeroSalt(DATA);
        Assert.assertEquals(expectedHash, hashAgain);
    }
    
    @Test
    public void testOutput() throws Exception {
        System.out.println("Plaintext: " + DATA);
        System.out.println("Hash First: " + ExampleJasyptHash.hashDefaultAlgorithmZeroSalt(DATA));
        System.out.println("Hash Again: " + ExampleJasyptHash.hashDefaultAlgorithmZeroSalt(DATA));
    }
    
    @Test
    public void testMessageMatches() throws Exception {
        Assert.assertTrue(ExampleJasyptHash.matchesWithSalt(DATA, ExampleJasyptHash.hashDefaultAlgorithmWithSalt(DATA)));
    }

    @Test
    public void testHashSHA512ConsistencyBetweenRuns() throws Exception {
        final String expectedHash = ExampleJasyptHash.hashSHA512(DATA);
        String hashAgain = ExampleJasyptHash.hashSHA512(DATA);
        Assert.assertEquals(expectedHash, hashAgain);
    }

    @Test
    public void testHash50000ConsistencyBetweenRuns() throws Exception {
        final String expectedHash = ExampleJasyptHash.hashSHA512By50000(DATA);
        String hashAgain = ExampleJasyptHash.hashSHA512By50000(DATA);
        Assert.assertEquals(expectedHash, hashAgain);
    }
}
