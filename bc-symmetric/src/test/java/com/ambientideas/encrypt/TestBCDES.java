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
        Assert.assertEquals("[B@5f326484", ExampleBCDES.doCryptDES(true, KEY, Strings.toUTF8ByteArray(DATA)));
    }
	
    @Test
    public void testBCBlowfish() throws Exception {
        Assert.assertEquals("[B@4ebac9b9", ExampleBCDES.doCryptBlowfish(true, KEY, Strings.toUTF8ByteArray(DATA)));
    }
}
