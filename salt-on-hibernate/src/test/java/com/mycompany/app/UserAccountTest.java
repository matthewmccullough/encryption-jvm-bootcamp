package com.mycompany.app;

import org.junit.Test;

import com.mycompany.app.util.HibernateUtil;

/**
 * Unit test for simple App.
 */
public class UserAccountTest
{
	@Test
    public void testApp()
    {
    	UserAccountManager mgr = new UserAccountManager();
        mgr.createAndStoreUserAccount("johntest.smith", "testsdk23k4j24k", "johntest@smith.com");
        HibernateUtil.getSessionFactory().close();
    }
}
