package com.ambientideas.saltedpassword;

import com.ambientideas.saltedpassword.util.HibernateUtil;

public class UserAccountApp {

    public static void main(String[] args) {
        UserAccountManager mgr = new UserAccountManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreUserAccount("john.smith", "mypasswordcleartext", "john@smith.com");
            mgr.createAndStoreUserAccount("bill.webber", "mypasswordcleartext", "bill@webber.com");
        }

        HibernateUtil.getSessionFactory().close();
    }
}
