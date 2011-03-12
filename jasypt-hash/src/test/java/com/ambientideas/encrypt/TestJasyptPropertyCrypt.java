package com.ambientideas.encrypt;

import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.junit.Test;

/**
 * Jasypt makes crypting properties much simpler than straight JCE.
 */
public class TestJasyptPropertyCrypt {
    public static String DATA = "Four score and seven years ago.";
    
    final String VALUEPLAINTEXT = "supersecretvalue";
    final String PASSWORDPLAINTEXT = "thisismypassword";
    final String CIPHERTEXTPASSWORD = "pJPtkzjRnYaOWb0c4FpvV69PHuEDieNBp96HibES4/A=";
    
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
        InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("sample.properties");
        props.load(inputStream);

        String datasourceUsername = props.getProperty("mysystem.username");
        String datasourcePassword = props.getProperty("mysystem.password");
        
        System.out.println("Plaintext Username: " + datasourceUsername);
        System.out.println("Decrypted Password: " + datasourcePassword);
        
        Assert.assertEquals("matthewmccullough", datasourceUsername);
        Assert.assertEquals(CIPHERTEXTPASSWORD, datasourcePassword);
    }
}
