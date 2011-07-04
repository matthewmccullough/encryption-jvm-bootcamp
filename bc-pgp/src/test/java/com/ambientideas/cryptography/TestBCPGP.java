package com.ambientideas.cryptography;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;

import junit.framework.Assert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import com.ambientideas.cryptography.KeyBasedFileProcessorUtil;

/*
 * Use the Bouncy Castle implementation of PGP to encrypt a file with an
 * existing private key.
 * 
 * Requires a non-ASCII-armored public key file which can be created via
 * the KeyBasedFileProcessorUtil class.
 */
public class TestBCPGP
{
    public static String EXPECTED_PUBKEY =
        "mI0ETYfxUAEEAJpIb1uamFr2RaNjdDCR+FHwNMFpCKkGw0ElPzzjXLv3ZYzZwnnRDt/QyiHXLdnw"+
        "5K5GuFi1EZVK/pFLLWoyUXzWse5o8rKanBGWTFYV7jtXDNZ3Q1bAbn3WwSiNviAxQdDmECZgXYEE"+
        "z4kKwNkUBKsNA4SIpwzA6U8c/hXpqscDABEBAAG0DG1hdHRoZXdsb2NhbIicBBABAgAGBQJNh/FQ"+
        "AAoJENOHrqRHDNUD/8wD/0HiirtSgm2O6WdZseWgPaG/ue/DBY3hsHx7ZMu2ic5wW8I2XE2BmWF4"+
        "PzOCcV9I9Al6BoOoep52pOTnIsY+dROTETjw/QmcN+G9xhyJMwwTfB4zfKtrnfCXaNngYKmJUY7W"+
        "fN5/ydEUanlXTNGh76SrMYAFTKVd4bpn3EVywyuc";    
    
    @Test
   // @Ignore //Doesn't return the expected provider unless explicitly requested with BC as the 2nd param
    public void testBCPGPProviderRegistration() throws NoSuchAlgorithmException, GeneralSecurityException {
        //Test the algorithm brought back before the BC provider is inserted
        Cipher cipherSunRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Assert.assertEquals("SunJCE version 1.6", cipherSunRSA.getProvider().toString());

        //Add the BC provider. If no position it given, provider adds to end of list
        int positionAdded = Security.insertProviderAt(new BouncyCastleProvider(), 1);
        Assert.assertEquals(1, positionAdded);
        
        //Now that the BC provider is inserted at position 1, an unconstrained request for RSA will
        // still come from BC
        Cipher cipherDefaultRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Assert.assertEquals("BC version 1.45", cipherDefaultRSA.getProvider().toString());
        
        //When specifically requested via "BC", then BouncyCastle is returned.
        Cipher cipherBCRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
        Assert.assertEquals("BC version 1.45", cipherBCRSA.getProvider().toString());
    }

    @Test
    public void testBCPGP() throws Exception {
        //Register Bouncy Castle JCE provider
        Security.addProvider(new BouncyCastleProvider());
        
        //Load Public Key File
        FileInputStream pubKeyIn = new FileInputStream("src/main/resources/keys/pub.bpg");
        PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(pubKeyIn);
        Assert.assertEquals(EXPECTED_PUBKEY, new String(Base64.encode(pubKey.getEncoded())));
        
        //Output file
        FileOutputStream out = new FileOutputStream("target/encrypted1.bpg");
        
        //Input file
        String inputFilename = "src/main/resources/cleartext1.txt";
        
        //Other settings
        boolean armor = false;
        boolean integrityCheck = true;
        
        KeyBasedFileProcessorUtil.encryptFile(out, inputFilename, pubKey, armor, integrityCheck);
        
        FileInputStream privKeyIn = new FileInputStream("src/main/resources/keys/secret.bpg");
        FileInputStream encryptedFileIn = new FileInputStream("target/encrypted1.bpg");
        //Note that the final parameter is a path to output all the potential files inside the BPG file
        KeyBasedFileProcessorUtil.decryptFile(encryptedFileIn, privKeyIn, "123456789".toCharArray(), "", "target/");
    }
	
    @Test(expected=org.bouncycastle.openpgp.PGPException.class)
    public void testBCPGPNotRightPassword() throws Exception {
        //Register Bouncy Castle JCE provider
        Security.addProvider(new BouncyCastleProvider());
        
        //Load Public Key File
        FileInputStream pubKeyIn = new FileInputStream("src/main/resources/keys/pub.bpg");
        PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(pubKeyIn);
        Assert.assertEquals(EXPECTED_PUBKEY, new String(Base64.encode(pubKey.getEncoded())));
        
        //Output file
        FileOutputStream out = new FileOutputStream("target/encrypted2.bpg");
        
        //Input file
        String inputFilename = "src/main/resources/cleartext2.txt";
        
        //Other settings
        boolean armor = false;
        boolean integrityCheck = true;
        
        KeyBasedFileProcessorUtil.encryptFile(out, inputFilename, pubKey, armor, integrityCheck);
        
        FileInputStream privKeyIn = new FileInputStream("src/main/resources/keys/secret.bpg");
        FileInputStream encryptedFileIn = new FileInputStream("target/encrypted2.bpg");
        KeyBasedFileProcessorUtil.decryptFile(encryptedFileIn, privKeyIn, "NOTTHERIGHTPASSWORD".toCharArray(), "", "target/");
    }
}
