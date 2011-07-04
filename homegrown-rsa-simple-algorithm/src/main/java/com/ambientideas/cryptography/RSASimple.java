package com.ambientideas.cryptography;

import java.math.BigInteger;
import java.security.SecureRandom;

// http://www.cs.princeton.edu/introcs/79crypto/RSA.java.html
public class RSASimple {
    private static BigInteger one = new BigInteger("1");
    private static SecureRandom random = new SecureRandom();
    private BigInteger privateKey, publicKey, modulus;

    // Generate an N-bit (roughly) public and private key
    RSASimple(int bitStrength) {
        BigInteger p = BigInteger.probablePrime(bitStrength/2, random);
        BigInteger q = BigInteger.probablePrime(bitStrength/2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");
        privateKey = publicKey.modInverse(phi);
    }
    
    public BigInteger getPublicKey() {
        return publicKey;
    }
    
    public BigInteger getPrivateKey() {
        return privateKey;
    }
    
    public BigInteger getModulus() {
        return modulus;
    }
    
    public void setKeys(BigInteger publicKey, BigInteger privateKey, BigInteger modulus) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.modulus = modulus;
    }
    
    BigInteger encrypt(BigInteger message) { return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }
}
