package com.ambientideas.encrypt;

import junit.framework.Assert;

import org.jasypt.digest.StandardStringDigester;
import org.junit.Test;

/**
 * Jasypt makes hashing (digests) much simpler than straight JCE.
 */
public class TestJasyptHash {
    public static String DATA = "Four score and seven years ago.";
    
    @Test
    public void testHashConsistencyBetweenRunsWithZeroSalt() throws Exception {
        System.out.println("Plaintext: " + DATA);
        
        StandardStringDigester digester1 = new StandardStringDigester();
        digester1.setSaltSizeBytes(0);// Must set salt to 0
        final String expectedHash = digester1.digest(DATA);
        
        StandardStringDigester digester2 = new StandardStringDigester();
        digester2.setSaltSizeBytes(0);// Must set salt to 0
        final String hashAgain = digester2.digest(DATA);
        
        System.out.println("Hash First: " + expectedHash);
        System.out.println("Hash Again: " + hashAgain);
        
        Assert.assertEquals(expectedHash, hashAgain);
    }
        
    @Test
    public void testMessageMatches() throws Exception {
        StandardStringDigester digester1 = new StandardStringDigester();
        String digest = digester1.digest(DATA);

        StandardStringDigester digester2 = new StandardStringDigester();
        boolean matches = digester2.matches(DATA, digest);

        //Matches when using the function that extracts the salt
        Assert.assertTrue(matches);
        
        //Does not match if compared directly (different random salt)
        Assert.assertFalse(DATA.equals(digest));
    }

    @Test
    public void testHashSHA512ConsistencyBetweenRunsWithNoSalt() throws Exception {
        StandardStringDigester digester1 = new StandardStringDigester();
        digester1.setSaltSizeBytes(0);
        digester1.setAlgorithm("SHA-512");
        final String expectedHash = digester1.digest(DATA);

        StandardStringDigester digester2 = new StandardStringDigester();
        digester2.setSaltSizeBytes(0);
        digester2.setAlgorithm("SHA-512");
        String hashAgain = digester2.digest(DATA);
        
        Assert.assertEquals(expectedHash, hashAgain);
    }

    @Test
    public void testHash50000ConsistencyBetweenRuns() throws Exception {
        StandardStringDigester digester1 = new StandardStringDigester();
        digester1.setSaltSizeBytes(0);
        digester1.setAlgorithm("SHA-512");
        digester1.setIterations(50000);
        String expectedHash = digester1.digest(DATA);

        StandardStringDigester digester2 = new StandardStringDigester();
        digester2.setSaltSizeBytes(0);
        digester2.setAlgorithm("SHA-512");
        digester2.setIterations(50000);
        String hashAgain = digester2.digest(DATA);

        Assert.assertEquals(expectedHash, hashAgain);
    }
}
