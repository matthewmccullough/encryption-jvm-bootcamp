package com.ambientideas.encrypt;

import org.jasypt.digest.StandardStringDigester;

/*
 * Use BouncyCastle for SHA Hashing
 */
public class ExampleJasyptHash {  
	public static void main(String[] args) throws Exception {
		//Register Bouncy Castle JCE provider
		final String DATA = "Four score and seven years ago.";
		
		System.out.println("Plaintext: " + DATA);
		System.out.println("Hash First: " + hash(DATA));
		System.out.println("Hash Again: " + hash(DATA));
	}
	
	public static String hash(String data) {
	    StandardStringDigester digester = new StandardStringDigester();
	    digester.setSaltSizeBytes(0);
        String digest = digester.digest(data);
	    
	    return digest;
	}
	
	   public static String hashSHA512(String data) {
	        StandardStringDigester digester = new StandardStringDigester();
	        digester.setSaltSizeBytes(0);
	        digester.setAlgorithm("SHA-512");
//	        digester.setIterations(50000);
	        String digest = digester.digest(data);
	        
	        return digest;
	    }
	   
       public static String hashSHA512By50000(String data) {
           StandardStringDigester digester = new StandardStringDigester();
           digester.setSaltSizeBytes(0);
           digester.setAlgorithm("SHA-512");
           digester.setIterations(50000);
           String digest = digester.digest(data);
           
           return digest;
       }
}
