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

import sun.misc.BASE64Encoder;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class SymmetricEncryptAES
{
    private static final int AES_BIT_STRENGTH_128 = 128;
    private static final int AES_BIT_STRENGTH_192 = 192;
    private static final int AES_BIT_STRENGTH_256 = 256;
    public final static String MESSAGE = "Four score and seven years ago";

    public static void main( String[] args )
    throws NoSuchAlgorithmException, NoSuchProviderException,
    NoSuchPaddingException, InvalidKeyException,
    IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        int iterations = 1000;
        long total128time = 0;
        long total192time = 0;
        long total256time = 0;
        
        for (int i = 0; i < iterations; i++) {
            total128time += encryptAndDecrypt(MESSAGE, AES_BIT_STRENGTH_128);
        }
        
        for (int i = 0; i < iterations; i++) {
            total192time += encryptAndDecrypt(MESSAGE, AES_BIT_STRENGTH_192);
        }
        
        for (int i = 0; i < iterations; i++) {
            total256time = encryptAndDecrypt(MESSAGE, AES_BIT_STRENGTH_256);
        }
        
        System.out.println("Average time for AES 128 encryption: " + (total128time / iterations));
        System.out.println("Average time for AES 192 encryption: " + (total192time / iterations));
        System.out.println("Average time for AES 256 encryption: " + (total256time / iterations));
    }

    private static long encryptAndDecrypt(String message, int bitStrength) throws NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
    BadPaddingException, UnsupportedEncodingException {
        long beforeAES = java.lang.System.currentTimeMillis();

        //Build a new encryption key
        final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        //256 bit fails on Worldwide Policy File (Strong) but succeeds on Unlimited
        keyGen.init(bitStrength);
        final SecretKey aesKey = keyGen.generateKey();

        //Set up the cipher
        final Cipher aesCipher = Cipher.getInstance("AES");

        //////////////////////////////////////
        //Put the cipher in encryption mode
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

        //Encrypt and output the base64 data
        byte[] clearText = message.getBytes("UTF8");
        byte[] encryptedBytes = aesCipher.doFinal(clearText);
        long afterAES = java.lang.System.currentTimeMillis();
        long time = afterAES - beforeAES;

        BASE64Encoder b64e = new sun.misc.BASE64Encoder();
        String base64Encrypted = b64e.encode(encryptedBytes);

        System.out.println("Encrypted text: " + base64Encrypted);
        System.out.println("AES encryption at bitstrength " + bitStrength + " took: " + time + "ms");


        //////////////////////////////////////
        //Put the cipher in decryption mode
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

        //Decrypt and output the original string
        byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, "UTF8");
        System.out.println("Decrypted text: " + decryptedText);
        
        return time;
    }
}
