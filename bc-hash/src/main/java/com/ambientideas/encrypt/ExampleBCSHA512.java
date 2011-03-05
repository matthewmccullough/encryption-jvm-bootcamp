package com.ambientideas.encrypt;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*
 * Use BouncyCastle for SHA Hashing
 */
public class ExampleBCSHA512 {
    
	public static void main(String[] args) throws Exception {
		//Register Bouncy Castle JCE provider
		Security.addProvider(new BouncyCastleProvider());
		
		final String DATA = "Four score and seven years ago.";
	}
	
	public static byte[] hash(byte[] data) {
	    return new byte[10];
	}
}
