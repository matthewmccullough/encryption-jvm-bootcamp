package com.ambientideas;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Test;

import sun.misc.BASE64Encoder;

public class TestSymmetricEncryptDESEDE
{
    public final String MESSAGE = "Four score and seven years ago";
    public final String CIPHERTEXTDESEDE = "pxngiHf0+k/x7dH+X6rrPgBmbk3g88zNBRmwr6/lBAQ=";

    @Test
    public void testSymmetricDESEDEPerformance()
    throws NoSuchAlgorithmException, NoSuchProviderException,
    NoSuchPaddingException, InvalidKeyException,
    IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    { 
        BASE64Encoder b64e = new sun.misc.BASE64Encoder();
        final KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
        keyGen.init(168);
        final SecretKey desKey = keyGen.generateKey();
        System.out.println("The DES key: " + b64e.encode(desKey.getEncoded()));

        final Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");

        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        
        byte[] clearText = MESSAGE.getBytes("UTF8");
        byte[] encryptedBytesFirstTime = desCipher.doFinal(clearText);
        byte[] encryptedBytesSecondTime = desCipher.doFinal(clearText);
        String base64EncryptedFirstTime = b64e.encode(encryptedBytesFirstTime);
        String base64EncryptedSecondTime = b64e.encode(encryptedBytesSecondTime);
        System.out.println("Encrypted text: " + base64EncryptedFirstTime);
        Assert.assertEquals("Encrypting the same text with the same key should yield the same text",base64EncryptedSecondTime, base64EncryptedFirstTime);

        //////////////////////////////////////
        //Put the cipher in decryption mode
        desCipher.init(Cipher.DECRYPT_MODE, desKey);

        //Decrypt and output the original string
        byte[] decryptedBytes = desCipher.doFinal(encryptedBytesFirstTime);
        String decryptedText = new String(decryptedBytes, "UTF8");
        System.out.println("Decrypted text: " + decryptedText);

        Assert.assertEquals(MESSAGE, decryptedText);
    }
    
//    private SecurityKey generateKey() {
//        return xHWXSoUTlyPjhQIlzmcL4OxYGZtrYXMl
//    }
//    
//    private SecurityKey getExistingKey() {
//        return //
//        xHWXSoUTlyPjhQIlzmcL4OxYGZtrYXMl
//        final KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
//        final SecretKey desKey = new SecretKey()
//    }
}
