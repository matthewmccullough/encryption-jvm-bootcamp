package com.ambientideas;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestSymmetric
{
	@Test
    public void testAES() throws Exception {
        SymmetricEncryptAES.main(null);
    }
	
	@Test
	public void testDES() throws Exception {
	    SymmetricEncryptDESEDE.main(null);
	}
}
