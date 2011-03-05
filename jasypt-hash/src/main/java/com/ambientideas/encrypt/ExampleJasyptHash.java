package com.ambientideas.encrypt;

import org.jasypt.digest.StandardStringDigester;

/*
 * Use BouncyCastle for SHA Hashing
 */
public class ExampleJasyptHash {
    
	public static void main(String[] args) throws Exception {
		//Register Bouncy Castle JCE provider
		final String DATA = "Four score and seven years ago.";
		
		System.out.println(hash(DATA));
	}
	
	public static String hash(String data) {
	    StandardStringDigester digester = new StandardStringDigester();
        digester.setAlgorithm("SHA-512"); // optionally set the algorithm
        digester.setIterations(50000);
        String digest = digester.digest(data);
	    
	    return digest;
	}
}
