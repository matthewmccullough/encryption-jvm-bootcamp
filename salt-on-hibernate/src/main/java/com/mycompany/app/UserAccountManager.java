package com.mycompany.app;

import org.hibernate.Session;
import java.util.Date;
import java.util.Properties;

import com.mycompany.app.util.HibernateUtil;

public class UserAccountManager {
    public void createAndStoreUserAccount(String userName, String passwordHash, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserAccount theNewUser = new UserAccount();
        theNewUser.setUsername(userName);
        theNewUser.setPasswordHash(passwordHash);
        theNewUser.setEmailAddress(emailAddress);
        theNewUser.setAccountCreationDate(new Date());

        session.save(theNewUser);

        session.getTransaction().commit();
    }
}