package com.ambientideas.cryptography;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestDemoCrypt
{
    public static ApplicationContext context = null;
    public static final String SSN = "234234234";
    public static final String PHRASE = "Round the rugged rock";
    
    @BeforeClass
    public static void setup()
    {
        context = new ClassPathXmlApplicationContext("SpringBeans.xml");
    }

    @Test
    public void testDemoCrypt()
    {
        DemoCrypt dc = (DemoCrypt) context.getBean("cryptBean");
        dc.setSocialSecurityNumber(SSN);
        String retrievedSSN = dc.getSocialSecurityNumber();
        Assert.assertEquals(SSN, retrievedSSN);
    }
    
    @Test
    public void testPBEStringEncryptor()
    {
        PBEStringEncryptor cryptor = (PBEStringEncryptor) context.getBean("strongEncryptor");
        String crypted = cryptor.encrypt(PHRASE);
        Assert.assertFalse(PHRASE.equals(crypted));
        Assert.assertEquals(44, crypted.length());
        
        String decrypted = cryptor.decrypt(crypted);
        Assert.assertEquals(PHRASE, decrypted);
    }
}
