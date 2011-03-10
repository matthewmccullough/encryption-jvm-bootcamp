package com.ambientideas.encrypt;

import org.jasypt.digest.StandardStringDigester;

/*
 * Use BouncyCastle for SHA Hashing
 */
public class ExampleJasyptHash {
    public static String hashDefaultAlgorithmZeroSalt(String data) {
        StandardStringDigester digester = new StandardStringDigester();
        digester.setSaltSizeBytes(0);// Must set salt to 0
        String digest = digester.digest(data);

        return digest;
    }
    
    public static String hashDefaultAlgorithmWithSalt(String data) {
        StandardStringDigester digester = new StandardStringDigester();
        String digest = digester.digest(data);

        return digest;
    }
    
    public static boolean matchesWithSalt(String plaintext, String hash) {
        StandardStringDigester digester = new StandardStringDigester();
        return digester.matches(plaintext, hash);
    }

    public static String hashSHA512(String data) {
        StandardStringDigester digester = new StandardStringDigester();
        digester.setSaltSizeBytes(0);
        digester.setAlgorithm("SHA-512");
        String digest = digester.digest(data);

        return digest;
    }

    public static String hashSHA512By50000(String data) {
        StandardStringDigester digester = new StandardStringDigester();
        digester.setSaltSizeBytes(0);
        digester.setAlgorithm("SHA-512");
        digester.setIterations(50000);
        String digest = digester.digest(data);

        return digest;
    }
}
