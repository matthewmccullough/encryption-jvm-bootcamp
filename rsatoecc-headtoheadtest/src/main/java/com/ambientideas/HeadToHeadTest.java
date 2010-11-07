package com.ambientideas;

import java.io.FileReader;

public class HeadToHeadTest
{
    public static final String CLEARTEXT_FILENAME = "src/main/resources/cleartext.txt";

    public static void main(String[] args) throws Exception {
        int iterations = 10;
        long rsa1024TotalTime = 0;
        long rsa2048TotalTime = 0;
        long rsa3072TotalTime = 0;
        long eciesTotalTime = 0;

        System.out.println("Plaintext = " + getTextFromFile(CLEARTEXT_FILENAME));

        for (int i = 0; i < iterations; i++) {
            rsa1024TotalTime = rsa1024TotalTime + RSAEncrypt.runExample(RSAEncrypt.RSA_BITSTRENGTH_1024, false);
            rsa2048TotalTime = rsa2048TotalTime + RSAEncrypt.runExample(RSAEncrypt.RSA_BITSTRENGTH_2048, false);
            rsa3072TotalTime = rsa3072TotalTime + RSAEncrypt.runExample(RSAEncrypt.RSA_BITSTRENGTH_3072, false);
            eciesTotalTime = eciesTotalTime + ECCEncrypt.runExample();
        }

        System.out.println("Each algorithm was run [" + iterations + "] times to get an average");
        System.out.println("Average RSA 1024 time: " + rsa1024TotalTime / iterations + "ms");
        System.out.println("Average RSA 2048 time: " + rsa2048TotalTime / iterations + "ms");
        System.out.println("Average RSA 3072 time: " + rsa3072TotalTime / iterations + "ms");
        System.out.println("ECIES bit strength (curve):" + ECCEncrypt.CURVE);
        System.out.println("ECIES Average time: " + eciesTotalTime / iterations + "ms");
    }

    public static String getTextFromFile(String file) throws Exception {
        FileReader fReader = new FileReader(file);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        return bReader.readLine();
    }
}
