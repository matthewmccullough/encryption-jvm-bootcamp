package com.ambientideas.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        testDefaultAlgorithm();
        testPBEWithMD5AndDES();
        testPBEWithMD5AndTripleDES();
    }

	private static void testDefaultAlgorithm() {
		StandardPBEStringEncryptor se1 = new StandardPBEStringEncryptor();
        se1.setPassword("thisissupersecret");
        
        String encryptedString1 = se1.encrypt("Hello World");
        System.out.println("Encrypted string with default algorithm is: " + encryptedString1);
	}

	private static void testPBEWithMD5AndDES() {
		StandardPBEStringEncryptor se2 = new StandardPBEStringEncryptor();
        se2.setAlgorithm("PBEWithMD5AndDES");
        se2.setPassword("thisissupersecret");
        
        String encryptedString2 = se2.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndDES is: " + encryptedString2);
	}
	
	private static void testPBEWithMD5AndTripleDES() {
		StandardPBEStringEncryptor se2 = new StandardPBEStringEncryptor();
        se2.setAlgorithm("PBEWithMD5AndTripleDES");
        se2.setPassword("thisissupersecret");
        
        String encryptedString2 = se2.encrypt("Hello World");
        System.out.println("Encrypted string with PBEWithMD5AndDES is: " + encryptedString2);
	}
}
