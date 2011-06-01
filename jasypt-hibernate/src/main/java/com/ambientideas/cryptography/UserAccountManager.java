package com.ambientideas.cryptography;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Example;


public class UserAccountManager {
	static final Logger log = Logger.getLogger(UserAccountManager.class);
	
    public void createAndStoreUserAccount(String userName, String password, String emailAddress, String biography) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount theNewUser = new UserAccount();
        theNewUser.setUsername(userName);
        
        theNewUser.setEmailAddress(emailAddress);
        theNewUser.setAccountCreationDate(new Date());
        theNewUser.setBiography(biography);

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
            //Test username validity
            if (userName.equals(ua.getUsername())) {
        	    loginSuccessful = true;
            }
        }
        
        return loginSuccessful;
    }
    
    public String getBiography(String userName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount exampleUserAccount = new UserAccount();
        exampleUserAccount.setUsername(userName);
        
        UserAccount ua = (UserAccount) session.createCriteria(UserAccount.class).add( Example.create(exampleUserAccount)).list().get(0);
        session.getTransaction().commit();
        
        if (ua != null) {
            log.debug(ua);
            return ua.getBiography();
        }
        else {
            return null;
        }
    }
    
    public String getBiographyRaw(String userName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount exampleUserAccount = new UserAccount();
        exampleUserAccount.setUsername(userName);
        
        UserAccount ua = (UserAccount) session.createCriteria(UserAccount.class).add( Example.create(exampleUserAccount)).list().get(0);
        session.getTransaction().commit();
        
        if (ua != null) {
            log.debug(ua);
            return ua.getBiographyRaw();
        }
        else {
            return null;
        }
    }
}