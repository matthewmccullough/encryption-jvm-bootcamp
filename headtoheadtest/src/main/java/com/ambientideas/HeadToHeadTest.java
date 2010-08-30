package com.ambientideas;

import com.ambientideas.TestRsa2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import de.flexiprovider.common.ies.IESParameterSpec;
import de.flexiprovider.core.FlexiCoreProvider;
import de.flexiprovider.ec.FlexiECProvider;
import de.flexiprovider.ec.parameters.CurveParams;
import de.flexiprovider.ec.parameters.CurveRegistry.BrainpoolP160r1;


public class HeadToHeadTest
{

    private static String cleartextFile = "src/main/resources/cleartext.txt";

    public static void main(String[] args) throws Exception {
      System.out.println("Plaintext = " + getTextFromFile(cleartextFile));

      rsaExample();
      eciesExample();
    }




  	 public static void eciesExample() throws Exception {

		Security.addProvider(new FlexiCoreProvider());
		Security.addProvider(new FlexiECProvider());

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");

		CurveParams ecParams = new BrainpoolP160r1();   

		kpg.initialize(ecParams, new SecureRandom());
		KeyPair keyPair = kpg.generateKeyPair();
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey privKey = keyPair.getPrivate();

		Cipher cipher = Cipher.getInstance("ECIES", "FlexiEC");

		IESParameterSpec iesParams = new IESParameterSpec("AES128_CBC", "HmacSHA1", null, null);

		cipher.init(Cipher.ENCRYPT_MODE, pubKey, iesParams);
        String ciphertextFile = "target/ciphertextECIES.txt";

		byte[] block = new byte[64];
		FileInputStream fis = new FileInputStream(cleartextFile);
		FileOutputStream fos = new FileOutputStream(ciphertextFile);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);

		long beforeECIES = java.lang.System.currentTimeMillis();
		
		int i;
		while ((i = fis.read(block)) != -1) {
			cos.write(block, 0, i);
		}
		cos.close();
		String cleartextAgainFile = "target/cleartextAgainECIES.txt";

		cipher.init(Cipher.DECRYPT_MODE, privKey, iesParams);

		fis = new FileInputStream(ciphertextFile);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		fos = new FileOutputStream(cleartextAgainFile);

		while ((i = cis.read(block)) != -1) {
			fos.write(block, 0, i);
		}
		fos.close();

		long afterECIES = java.lang.System.currentTimeMillis();

//        System.out.println("After ECIES Encryption Ciphertext = " + getTextFromFile(ciphertextFile));
//        System.out.println("After ECIES Decryption Plaintext = " + getTextFromFile(cleartextAgainFile));

		System.out.println("ECIES Encrypt/Decrypt time in millis: " + (afterECIES - beforeECIES));
	}


    public static void rsaExample() throws Exception {

        TestRsa2 app = new TestRsa2();

        FileReader fReader = new FileReader(cleartextFile);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        String input = bReader.readLine();
        
        long beforeRSA = java.lang.System.currentTimeMillis();
        String ciphertext = app.encrypt(input);
//        System.out.println("After RSA Encryption Ciphertext = " + ciphertext);
//        System.out.println("After RSA Decryption Plaintext = " + app.decrypt(ciphertext));
		long afterRSA = java.lang.System.currentTimeMillis();
		System.out.println("RSA Encrypt/Decrypt time in millis: " + (afterRSA - beforeRSA));

	}

    private static String getTextFromFile(String file) throws Exception {
        FileReader fReader = new FileReader(file);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        return bReader.readLine();

    }

}
