package com.ambientideas.saltedpassword;

import org.hibernate.Session;
import java.util.Date;

import com.ambientideas.saltedpassword.util.HibernateUtil;

public class UserAccountManager {
    public void createAndStoreUserAccount(String userName, String password, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount theNewUser = new UserAccount();
        theNewUser.setUsername(userName);
        
        String passwordHash = HashUtils.hash(password);
        theNewUser.setPasswordHash(passwordHash);
        theNewUser.setEmailAddress(emailAddress);
        theNewUser.setAccountCreationDate(new Date());

        session.save(theNewUser);

        session.getTransaction().commit();
    }
    
    public void validateLoginUserAccount(String userName, String passwordHash, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount theUser = new UserAccount();
        theUser.setUsername(userName);
        theUser.setPasswordHash(passwordHash);
        theUser.setEmailAddress(emailAddress);

        //FIND USER session.save(theUser);

        session.getTransaction().commit();
    }
}