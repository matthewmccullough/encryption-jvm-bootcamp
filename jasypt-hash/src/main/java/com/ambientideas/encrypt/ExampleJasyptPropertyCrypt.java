package com.ambientideas.encrypt;

import java.io.FileInputStream;
import java.util.Properties;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

/*
 * Use BouncyCastle for SHA Hashing
 */
public class ExampleJasyptPropertyCrypt {  
	public static void main(String[] args) throws Exception {
		final String DATA = "Four score and seven years ago.";
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("thisismypassword");
		 
		Properties props = new EncryptableProperties(encryptor);
		props.load(new FileInputStream("sample.properties"));

		String datasourceUsername = props.getProperty("datasource.username");
		String datasourcePassword = props.getProperty("datasource.password");

		System.out.println("Plaintext Username: " + datasourceUsername);
		System.out.println("Decrypted Password: " + datasourcePassword);
	}
	
	public static String crypt(String data) {
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
