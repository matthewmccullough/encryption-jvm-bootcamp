package com.ambientideas;

import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

public class TestSymmetricEncryptAESFromKeystore {
    private static final String MESSAGE1 = "Four score and seven years ago";
    private static Cipher aesCipher;
    private static Key aesKey;

    @Test
    public void testKeystoreAESCryptAndDescrypt()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, Exception {
        loadKeystoreAndKey();

        String encryptedString = encrypt(MESSAGE1);
        Assert.assertEquals("3exaVfZhR69oCatsAL/laBhxUdg6413CkDiMoh40qOM=\r\n", encryptedString);
        Assert.assertEquals(MESSAGE1, decrypt(encryptedString));
    }

    /**
     * Load the key from a keystore on disk
     */
    public static void loadKeystoreAndKey() throws Exception {
        // Get a new encryption key from the keystore
        FileInputStream fis = new FileInputStream("mykeys.keystore");
        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(fis, "password".toCharArray());
        aesKey = ks.getKey("mykey", "password".toCharArray());
        // Set up the cipher
        aesCipher = Cipher.getInstance("AES");
    }

    public static String encrypt(String message) throws Exception {
        // ////////////////////////////////////
        // Put the cipher in encryption mode
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

        // Encrypt and output the base64 data
        byte[] clearText = message.getBytes("UTF8");
        byte[] encryptedBytes = aesCipher.doFinal(clearText);

        // Base64 Encoded for display only
        String base64Encrypted = Base64.encodeBase64String(encryptedBytes);
        System.out.println("Encrypted text: " + base64Encrypted);

        return base64Encrypted;
    }

    public static String decrypt(String encryptedString) throws Exception {
        // ////////////////////////////////////
        // Put the cipher in decryption mode
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

        // Decrypt and output the original string
        byte[] encryptedBytes = Base64.decodeBase64(encryptedString);
        byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, "UTF8");
        System.out.println("Decrypted text: " + decryptedText);

        return decryptedText;
    }
}
