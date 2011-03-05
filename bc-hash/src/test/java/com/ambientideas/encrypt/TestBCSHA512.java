package com.ambientideas.encrypt;

import junit.framework.Assert;

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
    public void testBCDES() throws Exception {
        Assert.assertEquals("HSKASDLKSALKJD", ExampleBCSHA512.hash(Strings.toUTF8ByteArray(DATA)));
    }
	
    @Test
    public void testBCBlowfish() throws Exception {
        Assert.assertEquals("SHSDHSDHJKS", ExampleBCSHA512.hash(Strings.toUTF8ByteArray(DATA)));
    }
}
