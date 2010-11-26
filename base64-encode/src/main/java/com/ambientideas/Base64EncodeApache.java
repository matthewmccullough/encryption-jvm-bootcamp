package com.ambientideas;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class Base64EncodeApache 
{
  public static void main( String[] args ) throws IOException {
    final String message1 = "This is <bold>code</bold> with \t tabs, semicolons;"
    + " and alert-bells \b that could cause escaping issues if not encoded in Base64";
    
    System.out.println("Raw text:\r\n" + message1);
    System.out.println("Raw text byte count: " + message1.length() + "\r\n");
    
    String base64Encoded = Base64.encodeBase64String(message1.getBytes("UTF8")); 
    System.out.println("Base64 encoded text:\r\n" + base64Encoded);
    System.out.println("Base64 encoded text byte count: " + base64Encoded.length() + "\r\n");
    
    String base64Decoded = new String(Base64.decodeBase64(base64Encoded));
    
    System.out.println("Decoded text:\r\n" + base64Decoded);
  }
}
