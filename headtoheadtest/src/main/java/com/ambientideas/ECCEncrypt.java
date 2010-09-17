package com.ambientideas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

import de.flexiprovider.common.ies.IESParameterSpec;
import de.flexiprovider.core.FlexiCoreProvider;
import de.flexiprovider.ec.FlexiECProvider;
import de.flexiprovider.ec.parameters.CurveParams;
import de.flexiprovider.ec.parameters.CurveRegistry.BrainpoolP256r1;

public class ECCEncrypt {
 	private KeyPair keyPair;
	private PublicKey pubKey;
	private PrivateKey privKey;
	private Cipher cipher;
	private IESParameterSpec iesParams;
	
	final static String CIPHERTEXT_FILENAME = "target/ciphertextECIES.txt";
	private FileInputStream fis;
	private FileOutputStream fos;
	
	public ECCEncrypt() throws Exception
	{
		initialize();
	}

	public static long runExample() throws Exception {
		long beforeECIES = java.lang.System.currentTimeMillis();
		
		ECCEncrypt eccEncryptor = new ECCEncrypt();

 		eccEncryptor.encrypt();
 		eccEncryptor.decrypt();
 		long afterECIES = java.lang.System.currentTimeMillis();

        return afterECIES - beforeECIES;   
 	}
	
	private void initialize() throws NoSuchAlgorithmException,
	InvalidAlgorithmParameterException, NoSuchProviderException, NoSuchPaddingException {
		Security.addProvider(new FlexiCoreProvider());
		Security.addProvider(new FlexiECProvider());

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");

		//Curve selection options can be found at:
		// http://www.flexiprovider.de/javadoc/flexiprovider/docs/de/flexiprovider/ec/parameters/CurveRegistry.html
		// Brainpool256 is similar to RSA 3072
		// http://webcache.googleusercontent.com/search?q=cache:iPlB5Jrlh70J:download.microsoft.com/download/5/1/b/51b66cdf-d606-4757-8cee-4ae88d462976/CanadianSecuritySummit-WBILLING_SuiteBCryptoL.ppt+ECIES+bit+strength+ecc&cd=3&hl=en&ct=clnk&gl=us
		CurveParams ecParams = new BrainpoolP256r1();   

		kpg.initialize(ecParams, new SecureRandom());
		keyPair = kpg.generateKeyPair();
		pubKey = keyPair.getPublic();
		privKey = keyPair.getPrivate();

		cipher = Cipher.getInstance("ECIES", "FlexiEC");
		iesParams = new IESParameterSpec("AES128_CBC", "HmacSHA1", null, null);
	}


	private void encrypt() throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
 		cipher.init(Cipher.ENCRYPT_MODE, pubKey, iesParams);

 		byte[] block = new byte[64];
 		fis = new FileInputStream(HeadToHeadTest.CLEARTEXT_FILENAME);
 		fos = new FileOutputStream(CIPHERTEXT_FILENAME);
 		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
 		
 		int i;
 		while ((i = fis.read(block)) != -1) {
 			cos.write(block, 0, i);
 		}
 		cos.close();
	}
	
	private void decrypt() throws InvalidKeyException,
	InvalidAlgorithmParameterException, FileNotFoundException,
	IOException {
		String BACKTOCLEARTEXT_FILENAME = "target/cleartextAgainECIES.txt";

		cipher.init(Cipher.DECRYPT_MODE, privKey, iesParams);

		byte[] block = new byte[64];
		fis = new FileInputStream(CIPHERTEXT_FILENAME);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		fos = new FileOutputStream(BACKTOCLEARTEXT_FILENAME);

		int i;
		while ((i = cis.read(block)) != -1) {
			fos.write(block, 0, i);
		}
		fos.close();
	}
}
