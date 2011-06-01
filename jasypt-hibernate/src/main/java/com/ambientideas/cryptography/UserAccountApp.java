package com.ambientideas.cryptography;


public class UserAccountApp {

    public static void main(String[] args) {
        UserAccountManager mgr = new UserAccountManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreUserAccount("john.smith", "mypasswordcleartext", "john@smith.com", "I was the president of nothing until I was deposed.");
            mgr.createAndStoreUserAccount("bill.webber", "mypasswordcleartext", "bill@webber.com", "In a past life, I was a rock.");
        }

        HibernateUtil.getSessionFactory().close();
    }
}
