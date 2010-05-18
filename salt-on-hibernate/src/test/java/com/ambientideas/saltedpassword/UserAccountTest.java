package com.ambientideas.saltedpassword;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.ambientideas.saltedpassword.UserAccountManager;
import com.ambientideas.saltedpassword.util.HibernateUtil;

/**
 * Unit test for simple App.
 */
public class UserAccountTest
{
	static final Logger log = Logger.getLogger(UserAccountTest.class);
	
	@Test
    public void testUserAccountCreation()
    {
    	UserAccountManager mgr = new UserAccountManager();
        mgr.createAndStoreUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com");
        HibernateUtil.getSessionFactory().close();
    }
	
	@Test
	public void testUserAccountLogin() {
    	UserAccountManager mgr = new UserAccountManager();
    	mgr.createAndStoreUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com");
        assert (mgr.validateLoginUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com"));
        HibernateUtil.getSessionFactory().close();
	}
}
