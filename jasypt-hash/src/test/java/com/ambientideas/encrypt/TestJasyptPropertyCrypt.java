package com.ambientideas.encrypt;

import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.junit.Test;
import org.omg.CORBA.PolicyListHelper;

/**
 * Jasypt makes crypting properties much simpler than straight JCE.
 */
public class TestJasyptPropertyCrypt {
    public static String DATA = "Four score and seven years ago.";
    
    final String VALUEPLAINTEXT = "supersecretvalue";
    final String PASSWORDPLAINTEXT = "thisismypassword";
    
    @Test
    public void testCryptingAStringForThePropertiesFile() throws Exception {

        
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORDPLAINTEXT);
        String encryptedValue = encryptor.encrypt(VALUEPLAINTEXT);
        String encryptedValue2 = encryptor.encrypt(VALUEPLAINTEXT);
        
        //Each new encryption has a different salt
        Assert.assertFalse(encryptedValue2.equals(encryptedValue));
        
        System.out.println("Plaintext value: " + VALUEPLAINTEXT);
        System.out.println("Ciphertext value: " + encryptedValue);
        
        String decryptedValue = encryptor.decrypt(encryptedValue);
        
        System.out.println("Decrypted plaintext value: " + decryptedValue);
    }
    
    @Test
    public void testDecryptPropertiesEntry() throws Exception {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORDPLAINTEXT);
         
        Properties props = new EncryptableProperties(encryptor);
        props.load(new FileInputStream("sample.properties"));

        String datasourceUsername = props.getProperty("mysystem.username");
        String datasourcePassword = props.getProperty("mysystem.password");
        
        System.out.println("Plaintext Username: " + datasourceUsername);
        System.out.println("Decrypted Password: " + datasourcePassword);
        
        Assert.assertEquals("matthewmccullough", datasourceUsername);
        Assert.assertEquals(VALUEPLAINTEXT, datasourcePassword);
    }
}
