package com.ambientideas.saltedpassword;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.ambientideas.saltedpassword.util.HibernateUtil;

public class UserAccountManager {
	static final Logger log = Logger.getLogger(UserAccountManager.class);
	
    public void createAndStoreUserAccount(String userName, String password, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount theNewUser = new UserAccount();
        theNewUser.setUsername(userName);
        
        String randomSalt = HashUtils.randomSalt();
        
        String passwordHash = HashUtils.hash(randomSalt + password);
        theNewUser.setPasswordHash(passwordHash);
        theNewUser.setEmailAddress(emailAddress);
        theNewUser.setRandomSalt(randomSalt);
        theNewUser.setAccountCreationDate(new Date());

        session.save(theNewUser);

        session.getTransaction().commit();
    }
    
    public boolean validateLoginUserAccount(String userName, String password, String emailAddress) {
    	boolean loginSuccessful = false;
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount exampleUserAccount = new UserAccount();
        exampleUserAccount.setUsername(userName);
        
        UserAccount ua = (UserAccount) session.createCriteria(UserAccount.class).add( Example.create(exampleUserAccount)).list().get(0);
        session.getTransaction().commit();
        
        if (ua != null) {
            log.debug(ua);
        	//Test password validity
            String hashWithSalt = HashUtils.hash(ua.getRandomSalt() + password);
            if (hashWithSalt.equals(ua.getPasswordHash())) {
        	    loginSuccessful = true;
            }
        }
        
        return loginSuccessful;
    }
}