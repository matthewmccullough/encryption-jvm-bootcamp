package com.ambientideas.encrypt;

import java.security.Security;

import junit.framework.Assert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestBCSHA512
{
    public static String DATA = "Four score and seven years ago.";
    public static byte[] KEY = "thisisak".getBytes();
    
	@Test
    public void testBCHash() throws Exception {
//Assert.assertEquals("HSKASDLKSALKJD", ExampleBCSHA512.hash(Strings.toUTF8ByteArray(DATA)));

	    //Register Bouncy Castle JCE provider
        Security.addProvider(new BouncyCastleProvider());
        
        //hash
    }	
}
