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

        System.out.println("Total RSA time: " +
            rsaTotalTime + "ms, Average time: " + rsaTotalTime / iterations + "ms, Iterations: " + iterations);
        System.out.println("Total ECIES time: " +
            eciesTotalTime + "ms, Average time: " + eciesTotalTime / iterations + "ms, Iterations: " + iterations);
    }

    private static String getTextFromFile(String file) throws Exception {
        FileReader fReader = new FileReader(file);
        java.io.BufferedReader bReader = new java.io.BufferedReader(fReader);
        return bReader.readLine();
    }
}
