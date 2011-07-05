package com.ambientideas.cryptography;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHMAC 
{
	private static final String ALGORITHM = "HmacMD5";
	
    //Set up the message to be encoded
	final static String message1 = "Four score and seven years ago";
	final static String message2 = "Four score and seven tears ago";
	
	private static KeyGenerator keyGen = null;
	private static SecretKey secretKey = null;
	
	@BeforeClass
	public static void generateKey() throws NoSuchAlgorithmException {
	    // Generate symmetric key for HMAC-MD5
        keyGen = KeyGenerator.getInstance(ALGORITHM);
        secretKey = keyGen.generateKey();
	}

	/**
	 * This program demonstrates how to generate a secret-key object for
	 * HMAC-MD5, and initialize an HMAC-MD5 object with it.
	 */
	@Test
	public void test() throws Exception {
		// Get instance of MAC algorithm MD5 and initialize it with a symmetric key
		Mac clientMAC = Mac.getInstance(ALGORITHM);
		clientMAC.init(secretKey);
		
		//Crypt on the (conceptual) Client side
		byte[] clientResult1 = clientMAC.doFinal(message1.getBytes("UTF8"));
		byte[] clientResult2 = clientMAC.doFinal(message2.getBytes("UTF8"));
		System.out.println("Client result 1: "+ Base64.encodeBase64String(clientResult1));
		System.out.println("Client result 2: "+ Base64.encodeBase64String(clientResult2));
		
		/*
		 * Remote side would receive the plaintext and the MAC
		 * and perform the same operation with the same secret key
		 * and compare the results (should be identical).
		 */
		
		Mac serverMAC = Mac.getInstance(ALGORITHM);
        serverMAC.init(secretKey);
        
		//Crypt on the (conceptual) Server side
		byte[] serverResult1 = serverMAC.doFinal(message1.getBytes("UTF8"));
        byte[] serverResult2 = serverMAC.doFinal(message2.getBytes("UTF8"));
        System.out.println("Server result 1: "+ Base64.encodeBase64String(serverResult1));
        System.out.println("Server result 2: "+ Base64.encodeBase64String(serverResult2));
        
        Assert.assertEquals(new String(clientResult1), new String(serverResult1));
        Assert.assertEquals(new String(clientResult2), new String(serverResult2));
	}
}
