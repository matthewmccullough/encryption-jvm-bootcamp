package com.ambientideas.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

/**
 * Unit test for simple SimpleSymmetricJasypt.
 */
public class TestPBEJasypt
{
    @Test
    public void testDefaultAlgorithm() {
        StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setPassword("thisissupersecret");
        
        String encryptedString1 = se.encrypt("Hello World");
        System.out.println("Encrypted string with default algorithm is: " + encryptedString1);
    }

    @Test
    public void testPBEWithMD5AndDES() {
        StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setAlgorithm("PBEWithMD5AndDES");
        se.setPassword("thisissupersecret");
        
        String encryptedString2 = se.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndDES is: " + encryptedString2);
    }
    
    @Test
    public void testPBEWithMD5AndTripleDES() {
        StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setAlgorithm("PBEWithMD5AndTripleDES");
        se.setPassword("thisissupersecret");
        
        String encryptedString2 = se.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndTripleDES is: " + encryptedString2);
    }
}
