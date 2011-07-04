package com.ambientideas.encrypt;

import junit.framework.Assert;

import org.bouncycastle.util.Strings;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestBCDES
{
    public static String DATA = "Four score and seven years ago.";
    public static byte[] KEY = "thisisak".getBytes();
    
	@Test
    public void testBCDES() throws Exception {
	    byte[] plaintextOriginal = Strings.toUTF8ByteArray(DATA);
	    byte[] ciphertext = TestBCBlowfishAndDESDirectClasses.doCryptDES(true, KEY, Strings.toUTF8ByteArray(DATA));
	    byte[] plaintextDecrypted = TestBCBlowfishAndDESDirectClasses.doCryptDES(false, KEY, ciphertext);
	    String plaintextOriginalString = Strings.fromUTF8ByteArray(plaintextOriginal);
	    String plaintextDecryptedString = Strings.fromUTF8ByteArray(plaintextDecrypted);
	    
        System.out.println("Plaintext DES Original String: " + plaintextOriginalString);
        System.out.println("Plaintext DES Decrypted String: " + plaintextDecryptedString);

        Assert.assertEquals(plaintextOriginalString, plaintextDecryptedString);
    }
	
    @Test
    public void testBCBlowfish() throws Exception {
        byte[] plaintextOriginal = Strings.toUTF8ByteArray(DATA);
        byte[] ciphertext = TestBCBlowfishAndDESDirectClasses.doCryptBlowfish(true, KEY, Strings.toUTF8ByteArray(DATA));
        byte[] plaintextDecrypted = TestBCBlowfishAndDESDirectClasses.doCryptBlowfish(false, KEY, ciphertext);
        String plaintextOriginalString = Strings.fromUTF8ByteArray(plaintextOriginal);
        String plaintextDecryptedString = Strings.fromUTF8ByteArray(plaintextDecrypted);
        
        System.out.println("Plaintext Blowfish Original String: " + plaintextOriginalString);
        System.out.println("Plaintext Blowfish Decrypted String: " + plaintextDecryptedString);
        
        Assert.assertEquals(plaintextOriginalString, plaintextDecryptedString);
    }
}
