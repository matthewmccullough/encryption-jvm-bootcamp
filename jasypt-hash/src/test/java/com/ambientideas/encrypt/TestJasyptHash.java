package com.ambientideas.encrypt;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestJasyptHash {
    public static String DATA = "Four score and seven years ago.";

    @Test
    public void testHashConsistencyBetweenRuns() throws Exception {
        final String expectedHash = ExampleJasyptHash.hash(DATA);
        String hashAgain = ExampleJasyptHash.hash(DATA);
        Assert.assertEquals(expectedHash, hashAgain);
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
