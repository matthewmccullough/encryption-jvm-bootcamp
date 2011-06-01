package com.ambientideas.cryptography;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;
import org.junit.Test;
import org.junit.*;

import com.ambientideas.cryptography.HibernateUtil;
import com.ambientideas.cryptography.UserAccountManager;

/**
 * Unit test for simple App.
 */
public class UserAccountTest
{
	static final Logger log = Logger.getLogger(UserAccountTest.class);
	
	@Before
	public void setupJasyptWithHibernate() {
	    StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
	    strongEncryptor.setPassword("mydogisrover");
	    HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
	    registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);
	}
	
	@Test
    public void testUserAccountCreation()
    {
    	UserAccountManager mgr = new UserAccountManager();
        mgr.createAndStoreUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com",
                "I was the president of nothing until I was deposed.");
        HibernateUtil.getSessionFactory().close();
    }
	
	@Test
	public void testUserAccountLogin() {
    	    UserAccountManager mgr = new UserAccountManager();
    	    mgr.createAndStoreUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com",
    	        "In a past life, I was a rock.");
        assertTrue(mgr.validateLoginUserAccount("johntest.smith", "testmypasswordcleartext", "johntest@smith.com"));
        HibernateUtil.getSessionFactory().close();
	}
	
	@Test
    public void testBioDecrypt() {
        UserAccountManager mgr = new UserAccountManager();
        String bio = "Round the rugged rock the ragged rascal ran.";
        mgr.createAndStoreUserAccount("johntestbio.smith", "testmyotherpasswordcleartext", "johntestbio@smith.com",
                bio);
        assertEquals(bio, mgr.getBiography("johntestbio.smith"));
        HibernateUtil.getSessionFactory().close();
    }
	
    @Test
    public void testBioRaw() {
        UserAccountManager mgr = new UserAccountManager();
        String bio = "This is just plain naked text. Or is it?";
        mgr.createAndStoreUserAccount("johntestbioraw.smith", "testmyotherpasswordcleartext", "johntestbioraw@smith.com",
                bio);
        String bioRaw = mgr.getBiographyRaw("johntestbioraw.smith");
        System.out.println(mgr.toString());
        assertFalse("Should be encrypted gibberish, not plaintext", bio.equals(bioRaw));
        HibernateUtil.getSessionFactory().close();
    }
}
