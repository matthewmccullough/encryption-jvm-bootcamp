package com.ambientideas.encrypt;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import junit.framework.Assert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

/**
 * Use the standard JCE classes for crypto but with the BouncyCastle
 * provider providing the AES algorithm with CTR (counter) mode and no padding.
 */
public class TestExampleBCPBEViaJCE {

    private static final String salt = "Use this phrase as the input to salt the encryption (should be random)";
    private static final int iterations = 2000;
    private static final int keyLength = 256;
    private static final SecureRandom random = new SecureRandom();
    
    static final String PASSPHRASE = "MySup3rSecRe7Pa$$";
    static final String PLAINTEXT = "Four scor and seven years ago.";

    @Test
    public void test() throws Exception {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);

        byte [] ciphertext = encrypt(PASSPHRASE, PLAINTEXT);
        String decryptedPlaintext = decrypt(PASSPHRASE, ciphertext);
        System.out.println("Decrypted plaintext: " + decryptedPlaintext);
        Assert.assertEquals(PLAINTEXT, decryptedPlaintext);
    }

    private static byte [] encrypt(String passphrase, String plaintext) throws Exception {
        SecretKey key = generateKey(passphrase);

        Cipher cipher = Cipher.getInstance("AES/CTR/NOPADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key, generateIV(cipher), random);
        return cipher.doFinal(plaintext.getBytes());
    }

    private static String decrypt(String passphrase, byte [] ciphertext) throws Exception {
        SecretKey key = generateKey(passphrase);

        Cipher cipher = Cipher.getInstance("AES/CTR/NOPADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, generateIV(cipher), random);
        return new String(cipher.doFinal(ciphertext));
    }

    private static SecretKey generateKey(String passphrase) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt.getBytes(), iterations, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWITHSHA256AND256BITAES-CBC-BC");
        return keyFactory.generateSecret(keySpec);
    }

    private static IvParameterSpec generateIV(Cipher cipher) throws Exception {
        byte [] ivBytes = new byte[cipher.getBlockSize()];
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

}