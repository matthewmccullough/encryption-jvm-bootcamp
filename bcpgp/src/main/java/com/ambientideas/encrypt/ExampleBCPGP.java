package com.ambientideas.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPublicKey;

/*
 * Use the Bouncy Castle implementation of PGP to encrypt a file with an
 * existing private key.
 * 
 * Requires a non-ASCII-armored public key file which can be created via
 * the KeyBasedFileProcessorUtil class.
 */
public class ExampleBCPGP {

	public static void main(String[] args) throws Exception {
		//Register Bouncy Castle JCE provider
		Security.addProvider(new BouncyCastleProvider());
		
		//Load Public Key File
		FileInputStream keyIn = new FileInputStream("src/main/resources/keys/pub.bpg");
		PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(keyIn);
		
		//Output file
		FileOutputStream out = new FileOutputStream("target/encrypted.bpg");
		
		//Input file
		String inputFilename = "src/main/resources/cleartext.txt";
		
		//Other settings
		boolean armor = false;
		boolean integrityCheck = false;
		
		KeyBasedFileProcessorUtil.encryptFile(out, inputFilename, pubKey, armor, integrityCheck);
	}
}
