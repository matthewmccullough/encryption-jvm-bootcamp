package com.ambientideas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Use the SecureRandom java security class to generate a more expensive, but
 * cryptographically secure random number.
 */
public class TestJCEKeygenRSAToFile {
    @Test
    public void testKeygenToFileOutputStream() throws NoSuchAlgorithmException,
            NoSuchProviderException, IOException {
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();

        final PrivateKey priv = pair.getPrivate();
        final PublicKey pub = pair.getPublic();

        // Write the private key to a file
        FileOutputStream privOS = new FileOutputStream("RSAPrivateKey.key");
        Assert.assertNotNull(privOS);
        privOS.write(priv.getEncoded());
        privOS.close();

        // Write the private key to a file
        FileOutputStream publicOS = new FileOutputStream("RSAPublicKey.key");
        Assert.assertNotNull(publicOS);
        publicOS.write(pub.getEncoded());
        publicOS.close();
    }
    
//    @Test
//    public void testKeygenFromFileInputStream() throws NoSuchAlgorithmException,
//            NoSuchProviderException, IOException, ClassNotFoundException {
//        KeyPair pair = null;//Get from file
//
//        PrivateKey priv = null;
//        PublicKey pub = null;
//
//        // Write the private key to a file
//        ObjectInputStream privIS = new ObjectInputStream(new FileInputStream("RSAPrivateKey.key"));
//        priv = (PrivateKey) privIS.readObject();
//        privIS.close();
//        Assert.assertNotNull(priv);
//        
//        // Write the private key to a file
//        ObjectInputStream publicIS = new ObjectInputStream(new FileInputStream("RSAPublicKey.key"));
//        pub = (PublicKey) publicIS.readObject();
//        publicIS.close();
//        Assert.assertNotNull(pub);
//    }
}
