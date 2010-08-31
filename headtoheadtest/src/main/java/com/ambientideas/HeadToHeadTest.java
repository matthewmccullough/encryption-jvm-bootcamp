package com.ambientideas;

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
import de.flexiprovider.ec.parameters.CurveRegistry.BrainpoolP256r1;


public class HeadToHeadTest
{

    private static String cleartextFile = "src/main/resources/cleartext.txt";

    public static void main(String[] args) throws Exception {

        int iterations = 10;
        long rsaTotalTime = 0;
        long eciesTotalTime = 0;

        System.out.println("Plaintext = " + getTextFromFile(cleartextFile));

        for (int i = 0; i < iterations; i++) {
            rsaTotalTime = rsaTotalTime + rsaExample();
        }

        for (int i = 0; i < iterations; i++) {
            eciesTotalTime = eciesTotalTime + eciesExample();
        }

        System.out.println("Total RSA time in millis: " +
            rsaTotalTime + ", Average time: " + rsaTotalTime / iterations + ", Iterations: " + iterations);
        System.out.println("Total ECIES time in millis: " +
            eciesTotalTime + ", Average time: " + eciesTotalTime / iterations + ", Iterations: " + iterations);
    }




  	 public static long eciesExample() throws Exception {

		Security.addProvider(new FlexiCoreProvider());
		Security.addProvider(new FlexiECProvider());

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");

        //Curve selection options can be found at:
        // http://www.flexiprovider.de/javadoc/flexiprovider/docs/de/flexiprovider/ec/parameters/CurveRegistry.html
        // Brainpool256 is similar to RSA 3072
        // http://webcache.googleusercontent.com/search?q=cache:iPlB5Jrlh70J:download.microsoft.com/download/5/1/b/51b66cdf-d606-4757-8cee-4ae88d462976/CanadianSecuritySummit-WBILLING_SuiteBCryptoL.ppt+ECIES+bit+strength+ecc&cd=3&hl=en&ct=clnk&gl=us
		CurveParams ecParams = new BrainpoolP256r1();   

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

        return afterECIES - beforeECIES;   
	}


    public static long rsaExample() throws Exception {

        TestRsa2 app = new TestRsa2();

        FileReader fReader = new FileReader(cleartextFile);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        String input = bReader.readLine();
        
        long beforeRSA = java.lang.System.currentTimeMillis();
        String ciphertext = app.encrypt(input);
        app.decrypt(ciphertext);      
		long afterRSA = java.lang.System.currentTimeMillis();

        return afterRSA - beforeRSA;
    }

    private static String getTextFromFile(String file) throws Exception {
        FileReader fReader = new FileReader(file);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        return bReader.readLine();

    }

}
