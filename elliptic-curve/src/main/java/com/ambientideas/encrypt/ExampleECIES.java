package com.ambientideas.encrypt;

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
import de.flexiprovider.ec.parameters.CurveRegistry.BrainpoolP160r1;

/*
 * Inspiration code, with mistakes and typos that prevented it from functioning:
 * http://www.cdc.informatik.tu-darmstadt.de/flexiprovider/examples/ExampleECIES.html
 *
 * FlexiProvider JCE Libs:
 * http://www.cdc.informatik.tu-darmstadt.de/flexiprovider/download/
 * http://www.cdc.informatik.tu-darmstadt.de/flexiprovider/download.html
 */

public class ExampleECIES {

	public static void main(String[] args) throws Exception {

		//Dynamically add the FlexiCore JCE providers
		Security.addProvider(new FlexiCoreProvider());
		Security.addProvider(new FlexiECProvider());
		
		//Get an instance of the FlexiEC provider's ECIES KPG
		/*
		 * NOTE: The KeyPairGenerator can be requested with the provider
		 * or without the provider. Without the provider explicitly specified,
		 * the search-order of providers in the Java.security file is honored.
		 */
//		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES", "FlexiEC");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");

		CurveParams ecParams = new BrainpoolP160r1();

		//TODO: Get key from disk in a keystore
		kpg.initialize(ecParams, new SecureRandom());
		KeyPair keyPair = kpg.generateKeyPair();
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey privKey = keyPair.getPrivate();
		
		Cipher cipher = Cipher.getInstance("ECIES", "FlexiEC");

		IESParameterSpec iesParams = new IESParameterSpec("AES128_CBC", "HmacSHA1", null, null);

		cipher.init(Cipher.ENCRYPT_MODE, pubKey, iesParams);
		String cleartextFile = "src/main/resources/cleartext.txt";
		String ciphertextFile = "target/ciphertextECIES.txt";

		byte[] block = new byte[64];
		FileInputStream fis = new FileInputStream(cleartextFile);
		FileOutputStream fos = new FileOutputStream(ciphertextFile);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);

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
	}
}
