package com.ambientideas.encrypt;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestJasyptHash
{
    public static String DATA = "Four score and seven years ago.";
    
	@Test
    public void testBCDES() throws Exception {
        Assert.assertEquals("HSKASDLKSALKJD", ExampleJasyptHash.hash(DATA));
    }
	
    @Test
    public void testBCBlowfish() throws Exception {
        Assert.assertEquals("SHSDHSDHJKS", ExampleJasyptHash.hash(DATA));
    }
}
