package com.ambientideas.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ExampleBCRSA {

    /**
     * @param args
     * @throws NoSuchPaddingException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        Security.addProvider(new BouncyCastleProvider());
        
        Cipher cipherSunRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Cipher cipherBCRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
    }

}
