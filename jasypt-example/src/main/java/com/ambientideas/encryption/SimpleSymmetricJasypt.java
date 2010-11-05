package com.ambientideas.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * A simple exercise of several flavor of symmetric password-based
 * encryption via the Jasypt encryption API.
 */
public class SimpleSymmetricJasypt 
{
    public static void main( String[] args )
    {        
        testDefaultAlgorithm();
        testPBEWithMD5AndDES();
        testPBEWithMD5AndTripleDES();
    }

	private static void testDefaultAlgorithm() {
		StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setPassword("thisissupersecret");
        
        String encryptedString1 = se.encrypt("Hello World");
        System.out.println("Encrypted string with default algorithm is: " + encryptedString1);
	}

	private static void testPBEWithMD5AndDES() {
		StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setAlgorithm("PBEWithMD5AndDES");
        se.setPassword("thisissupersecret");
        
        String encryptedString2 = se.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndDES is: " + encryptedString2);
	}
	
	private static void testPBEWithMD5AndTripleDES() {
		StandardPBEStringEncryptor se = new StandardPBEStringEncryptor();
        se.setAlgorithm("PBEWithMD5AndTripleDES");
        se.setPassword("thisissupersecret");
        
        String encryptedString2 = se.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndTripleDES is: " + encryptedString2);
	}
}
