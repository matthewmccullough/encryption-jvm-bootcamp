package com.ambientideas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class ECCEncrypt {
 	 public static long runExample(String cleartextFilename) throws Exception {

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
 		FileInputStream fis = new FileInputStream(cleartextFilename);
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
}
