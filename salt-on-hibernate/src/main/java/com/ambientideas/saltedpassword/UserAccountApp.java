package com.ambientideas.saltedpassword;

import com.ambientideas.saltedpassword.util.HibernateUtil;

public class UserAccountApp {

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
}
