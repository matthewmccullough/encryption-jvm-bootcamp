package com.mycompany.app;

import org.hibernate.Session;
import java.util.Date;
import java.util.Properties;

import com.mycompany.app.util.HibernateUtil;

public class UserAccountManager {

    public static void main(String[] args) {
        UserAccountManager mgr = new UserAccountManager();

        if (args[0].equals("store")) {
			System.out.println("Creating and storing event...");
            mgr.createAndStoreUserAccount("john.smith", "sdk23k4j24k", "john@smith.com");
        } else {
			System.out.println("Hello World: "+System.getProperty("java.version","Unknown") );
		}

        HibernateUtil.getSessionFactory().close();
    }

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