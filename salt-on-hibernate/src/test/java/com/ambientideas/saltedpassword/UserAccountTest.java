package com.ambientideas.saltedpassword;

import org.junit.Test;

import com.ambientideas.saltedpassword.UserAccountManager;
import com.ambientideas.saltedpassword.util.HibernateUtil;

/**
 * Unit test for simple App.
 */
public class UserAccountTest
{
	@Test
    public void testApp()
    {
    	UserAccountManager mgr = new UserAccountManager();
        mgr.createAndStoreUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com");
        HibernateUtil.getSessionFactory().close();
    }
}
