package com.ambientideas;

// http://www.cs.princeton.edu/introcs/79crypto/RSA.java.html
public class RSA {
    private static BigInteger one = new BigInteger("1");
    private static SecureRandom random = new SecureRandom();
    private BigInteger privateKey, publicKey, modulus;

    // Generate an N-bit (roughly) public and private key
    RSA(int n) {
        BigInteger p = BigInteger.probablePrime(n/2, random);
        BigInteger q = BigInteger.probablePrime(n/2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");
        privateKey = publicKey.modInverse(phi);
    }

    BigInteger encrypt(BigInteger message) { return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // key size
        RSA key = new RSA(n);
        // create random message, encrypt and decrypt
        BigInteger message = new BigInteger(n-1, random);
        BigInteger encrypt = key.encrypt(message);
        BigInteger decrypt = key.decrypt(encrypt);
    }
}
