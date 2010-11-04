package com.ambientideas;

import java.io.FileReader;

public class HeadToHeadTest
{
    public static final String CLEARTEXT_FILENAME = "src/main/resources/cleartext.txt";

    public static void main(String[] args) throws Exception {
        int iterations = 10;
        long rsaTotalTime = 0;
        long eciesTotalTime = 0;

        System.out.println("Plaintext = " + getTextFromFile(CLEARTEXT_FILENAME));

        for (int i = 0; i < iterations; i++) {
            rsaTotalTime = rsaTotalTime + RSAEncrypt.runExample();
        }

        for (int i = 0; i < iterations; i++) {
            eciesTotalTime = eciesTotalTime + ECCEncrypt.runExample();
        }

        System.out.println("Each algorithm was run [" + iterations + "] times to get an average");
        System.out.println("RSA bit strength:" + RSAEncrypt.RSA_BITSTRENGTH);
        System.out.println("Average RSA time: " + rsaTotalTime / iterations + "ms");
        System.out.println("ECIES bit strength (curve):" + ECCEncrypt.CURVE);
        System.out.println("ECIES Average time: " + eciesTotalTime / iterations + "ms");
    }

    private static String getTextFromFile(String file) throws Exception {
        FileReader fReader = new FileReader(file);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        return bReader.readLine();
    }
}
