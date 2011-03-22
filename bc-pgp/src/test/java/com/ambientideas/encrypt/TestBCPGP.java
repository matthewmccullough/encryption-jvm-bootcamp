package com.ambientideas.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Security;

import junit.framework.Assert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.junit.Test;

import sun.misc.BASE64Encoder;

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
        "mI0ETYfxUAEEAJpIb1uamFr2RaNjdDCR+FHwNMFpCKkGw0ElPzzjXLv3ZYzZwnnRDt/QyiHXLdnw\n"+
        "5K5GuFi1EZVK/pFLLWoyUXzWse5o8rKanBGWTFYV7jtXDNZ3Q1bAbn3WwSiNviAxQdDmECZgXYEE\n"+
        "z4kKwNkUBKsNA4SIpwzA6U8c/hXpqscDABEBAAG0DG1hdHRoZXdsb2NhbIicBBABAgAGBQJNh/FQ\n"+
        "AAoJENOHrqRHDNUD/8wD/0HiirtSgm2O6WdZseWgPaG/ue/DBY3hsHx7ZMu2ic5wW8I2XE2BmWF4\n"+
        "PzOCcV9I9Al6BoOoep52pOTnIsY+dROTETjw/QmcN+G9xhyJMwwTfB4zfKtrnfCXaNngYKmJUY7W\n"+
        "fN5/ydEUanlXTNGh76SrMYAFTKVd4bpn3EVywyuc";
    @SuppressWarnings("restriction")
    public static BASE64Encoder b64e = new sun.misc.BASE64Encoder();
    
	@SuppressWarnings("restriction")
    @Test
    public void testBCPGP() throws Exception {
        //Register Bouncy Castle JCE provider
        Security.addProvider(new BouncyCastleProvider());
        
        //Load Public Key File
        FileInputStream pubKeyIn = new FileInputStream("src/main/resources/keys/pub.bpg");
        PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(pubKeyIn);
        Assert.assertEquals(EXPECTED_PUBKEY, b64e.encode(pubKey.getEncoded()));
        
        //Output file
        FileOutputStream out = new FileOutputStream("target/encrypted.bpg");
        
        //Input file
        String inputFilename = "src/main/resources/cleartext.txt";
        
        //Other settings
        boolean armor = false;
        boolean integrityCheck = true;
        
        KeyBasedFileProcessorUtil.encryptFile(out, inputFilename, pubKey, armor, integrityCheck);
        
        FileInputStream privKeyIn = new FileInputStream("src/main/resources/keys/secret.bpg");
        FileInputStream encryptedFileIn = new FileInputStream("target/encrypted.bpg");
        KeyBasedFileProcessorUtil.decryptFile(encryptedFileIn, privKeyIn, "123456789".toCharArray(), "target/decrypted.txt");
    }
	
	@SuppressWarnings("restriction")
    @Test(expected=org.bouncycastle.openpgp.PGPException.class)
    public void testBCPGPNotRightPassword() throws Exception {
        //Register Bouncy Castle JCE provider
        Security.addProvider(new BouncyCastleProvider());
        
        //Load Public Key File
        FileInputStream pubKeyIn = new FileInputStream("src/main/resources/keys/pub.bpg");
        PGPPublicKey pubKey = KeyBasedFileProcessorUtil.readPublicKey(pubKeyIn);
        Assert.assertEquals(EXPECTED_PUBKEY, b64e.encode(pubKey.getEncoded()));
        
        //Output file
        FileOutputStream out = new FileOutputStream("target/encrypted.bpg");
        
        //Input file
        String inputFilename = "src/main/resources/cleartext.txt";
        
        //Other settings
        boolean armor = false;
        boolean integrityCheck = true;
        
        KeyBasedFileProcessorUtil.encryptFile(out, inputFilename, pubKey, armor, integrityCheck);
        
        FileInputStream privKeyIn = new FileInputStream("src/main/resources/keys/secret.bpg");
        FileInputStream encryptedFileIn = new FileInputStream("target/encrypted.bpg");
        KeyBasedFileProcessorUtil.decryptFile(encryptedFileIn, privKeyIn, "NOTTHERIGHTPASSWORD".toCharArray(), "target/decrypted.txt");
    }
}
