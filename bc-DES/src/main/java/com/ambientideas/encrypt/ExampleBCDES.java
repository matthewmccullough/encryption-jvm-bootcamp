package com.ambientideas.encrypt;

import java.security.Security;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.PaddedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*
 * Use BouncyCastle for DES encryption
 */
public class ExampleBCDES {
    
	public static void main(String[] args) throws Exception {
		//Register Bouncy Castle JCE provider
		Security.addProvider(new BouncyCastleProvider());
		
		String data = "Four score and seven years ago.";
		byte[] key = "thisisakey".getBytes();
		
	    BufferedBlockCipher cipher;
	    KeyParameter keyp;

	    /*
        cipher = new PaddedBlockCipher(
                 new CBCBlockCipher(
                 new DESEngine() ) );
         */
	    
		cipher = new PaddedBlockCipher(
                new CBCBlockCipher(
                new BlowfishEngine() ) );
        
        keyp = new KeyParameter( key );
        
        cipher.init( true, keyp );
        
        byte[] databytes = data.getBytes();
        int size = cipher.getOutputSize(databytes.length);
        byte[] result = new byte[size];
        int olen = cipher.processBytes(databytes, 0, databytes.length, result, 0);
        olen += cipher.doFinal(result, olen);

        if (olen < size) {
            byte[] tmp = new byte[olen];
            System.arraycopy(result, 0, tmp, 0, olen);
            result = tmp;
        }
        
        System.out.println("Encrypt Result: " + result.toString());
        
        
        //Decrypt
        cipher.init( false, keyp );
        byte[] databytesdecrypt = result;
        int sizedecrypt = cipher.getOutputSize(databytesdecrypt.length);
        byte[] resultdecrypt = new byte[sizedecrypt];
        int olendecrypt = cipher.processBytes(databytesdecrypt, 0, databytesdecrypt.length, result, 0);
        olendecrypt += cipher.doFinal(resultdecrypt, olendecrypt);

        if (olendecrypt < sizedecrypt) {
            byte[] tmp = new byte[olendecrypt];
            System.arraycopy(result, 0, tmp, 0, olendecrypt);
            resultdecrypt = tmp;
        }
        
        System.out.println("Decrypt Result: " + new String(resultdecrypt));
	}	
}
