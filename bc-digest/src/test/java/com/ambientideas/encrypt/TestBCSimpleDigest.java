package com.ambientideas.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;

import junit.framework.Assert;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test the BouncyCastle SHA512 implementation
 */
public class TestBCSimpleDigest
{
    public static String DATA = "Four score and seven years ago.";
    public static byte[] KEY = "thisisak".getBytes();
    public static String SHA1_DIGEST_KNOWN_RESULT = "PC0ImQNAvA9Nf8/n5qhJqEtUD1A=";
    public static String WHIRLPOOL_DIGEST_KNOWN_RESULT = "Vp47q36YdKmVYHgVZ5MpLPJWbc829DPJ5TevTrt5Bu6IyZ7OcCyc/dNte1rt5KqrG7yAOBttItL2"
        +"PO026bGh0w==";
    
    @SuppressWarnings("restriction")
    
    @BeforeClass
    public static void loadBCProvider() {
        //Register Bouncy Castle JCE provider at the end of the list
        Security.addProvider(new BouncyCastleProvider());
    }
    
	@SuppressWarnings("restriction")
    @Test
    public void testBCHashDirectAPI() throws Exception {
	    byte[] utf8ByteArrayData = Strings.toUTF8ByteArray(DATA);
	    
	    Digest digest = new SHA1Digest();
        byte[] hashResultBytes = new byte[digest.getDigestSize()];
        digest.update(utf8ByteArrayData, 0, utf8ByteArrayData.length);
        digest.doFinal(hashResultBytes, 0);
        String hashResult = new String(Base64.encode(hashResultBytes));
        
        //System.out.println("Hash result: " + hashResult);
        Assert.assertEquals(SHA1_DIGEST_KNOWN_RESULT, hashResult);
    }
	
	/**
	 * Ask for SHA-1 with BouncyCastle at end of provider list. Will get
	 * the core JCE implementation instead.
	 */
	@SuppressWarnings("restriction")
    @Test
	public void testBCHashJCEAPI() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //Ask for a algorithm that both SUN and BC have and prove it is BC by the class name
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        
        Assert.assertEquals("SHA1 Message Digest from SUN, <initialized>\n", sha.toString());
        Assert.assertEquals("SHA1", sha.getAlgorithm().toString());
        Assert.assertEquals("SUN version 1.6", sha.getProvider().toString());
        
        byte[] digestResultBytes = sha.digest(DATA.getBytes("UTF8"));
        byte[] digestResultB64String = Base64.encode(digestResultBytes);
        
        //System.out.println("Hash result: " + digestResultB64String);
        Assert.assertEquals(SHA1_DIGEST_KNOWN_RESULT, new String(digestResultB64String));
	}
	
    /**
     * Ask for SHA-1 with BouncyCastle at end of provider list. Will get
     * the core JCE implementation instead.
     */
    @SuppressWarnings("restriction")
    @Test
    public void testBCHashWhirlpoolJCEAPI() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //Register Bouncy Castle JCE provider at the beginning of the provider list
        //Security.insertProviderAt(new BouncyCastleProvider(), 1);
        
        //Ask for a algorithm that only BC has and prove it is BC by the class name
        MessageDigest sha = MessageDigest.getInstance("Whirlpool");
        
        Assert.assertEquals("Whirlpool Message Digest from BC, <initialized>\n", sha.toString());
        Assert.assertEquals("Whirlpool", sha.getAlgorithm().toString());
        Assert.assertEquals("BC version 1.45", sha.getProvider().toString());
        
        byte[] digestResultBytes = sha.digest(DATA.getBytes("UTF8"));
        byte[] digestResultB64String = Base64.encode(digestResultBytes);
        
        //System.out.println("Hash result: " + digestResultB64String);
        Assert.assertEquals(WHIRLPOOL_DIGEST_KNOWN_RESULT, new String(digestResultB64String));
    }
	
	/**
     * Ask for SHA-1 with BouncyCastle at end of provider list. Will get
     * the core JCE implementation.
     */
    @SuppressWarnings("restriction")
    @Test
    public void testSHA1HashJCEProvider() throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        //Ask for a algorithm that both SUN and BC have and prove it is JCE by the class name
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        
        Assert.assertEquals("SHA1 Message Digest from SUN, <initialized>\n", sha.toString());
        Assert.assertEquals("SHA1", sha.getAlgorithm().toString());
        Assert.assertEquals("SUN version 1.6", sha.getProvider().toString());
        
        byte[] digestResultBytes = sha.digest(DATA.getBytes("UTF8"));
        byte[] digestResultB64String = Base64.encode(digestResultBytes);
        
        Assert.assertEquals(SHA1_DIGEST_KNOWN_RESULT, new String(digestResultB64String));
    }
    
    /**
     * Ask for SHA-1 with BouncyCastle at end of provider list. Given that we asked
     * qualified by the "BC" provider sub-name, we will get the BC implemented as
     * expected.
     */
    @SuppressWarnings("restriction")
    @Test
    public void testSHA1HashBCProviderDirectly() throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        //Ask for a algorithm that both SUN and BC have and prove it is BC by the class name
        MessageDigest sha = MessageDigest.getInstance("SHA1", "BC");
        
        Assert.assertEquals("SHA-1 Message Digest from BC, <initialized>\n", sha.toString());
        Assert.assertEquals("SHA-1", sha.getAlgorithm().toString());
        Assert.assertEquals("BC version 1.45", sha.getProvider().toString());
        
        byte[] digestResultBytes = sha.digest(DATA.getBytes("UTF8"));
        byte[] digestResultB64String = Base64.encode(digestResultBytes);
        
        Assert.assertEquals(SHA1_DIGEST_KNOWN_RESULT, new String(digestResultB64String));
    }
    
    /**
     * Ask for SHA-1 with BouncyCastle at front of provider list. We will get the BC implemented as
     * expected.
     */
    @SuppressWarnings("restriction")
    @Test
    public void testSHA1HashBCProvider() throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        //Since BC was already registered in the startup of the test harness, we must remove it before re-adding
        Security.removeProvider("BC");
        //Register Bouncy Castle JCE provider at the front of the list, ensuring it takes position 1
        assertEquals(1, Security.insertProviderAt(new BouncyCastleProvider(), 1));
  
        //Debugging output
//        System.out.println(Security.getAlgorithms("MessageDigest"));
//        for (Provider p : Security.getProviders()) {
//            System.out.println(p);
//        }
                
        //Ask for a algorithm that both SUN and BC have and prove it is BC by the class name
        MessageDigest sha = MessageDigest.getInstance("SHA1");
        
        Assert.assertEquals("SHA-1 Message Digest from BC, <initialized>\n", sha.toString());
        Assert.assertEquals("SHA-1", sha.getAlgorithm().toString());
        Assert.assertEquals("BC version 1.45", sha.getProvider().toString());
        
        byte[] digestResultBytes = sha.digest(DATA.getBytes("UTF8"));
        byte[] digestResultB64String = Base64.encode(digestResultBytes);
        
        Assert.assertEquals(SHA1_DIGEST_KNOWN_RESULT, new String(digestResultB64String));
    }
    
    @Test(expected=NoSuchAlgorithmException.class)
    public void testBCAlgorithmNotFound() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA9999");
    }
}
