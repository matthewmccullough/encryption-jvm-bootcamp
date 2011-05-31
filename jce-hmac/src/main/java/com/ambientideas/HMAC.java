package com.ambientideas;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class HMAC 
{
	//Set up the message to be encoded
	final static String message1 = "Four score and seven years ago";
	final static String message2 = "Four score and seven tears ago";

	/**
	 * This program demonstrates how to generate a secret-key object for
	 * HMAC-MD5, and initialize an HMAC-MD5 object with it.
	 */
	public static void main(String[] args) throws Exception {
		// Generate symmetric key for HMAC-MD5
		KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
		SecretKey sk = kg.generateKey();

		// Get instance of MAC algorithm MD5 and initialize it with a symmetric key
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(sk);
		byte[] result1 = mac.doFinal(message1.getBytes("UTF8"));
		byte[] result2 = mac.doFinal(message2.getBytes("UTF8"));
		
		System.out.println(new sun.misc.BASE64Encoder().encode(result1));
		System.out.println(new sun.misc.BASE64Encoder().encode(result2));
		
		//Remote side would receive the plaintext and the MAC
		// and perform the same operation with the same secret key
		// and compare the results (should be identical).
	}
}