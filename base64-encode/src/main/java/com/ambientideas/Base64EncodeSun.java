package com.ambientideas;

import java.io.IOException;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class Base64EncodeSun 
{
  public static void main( String[] args ) throws IOException {
    final String message1 = "This is <bold>code</bold> with \t tabs, semicolons;"
    + " and alert-bells \b that could cause escaping issues if not encoded in Base64";
    
    System.out.println("Raw text:\r\n" + message1 + "\r\n");
    
    BASE64Encoder b64e = new sun.misc.BASE64Encoder();
    String base64Encoded = b64e.encode(message1.getBytes("UTF8"));
    System.out.println("Base64 encoded text:\r\n" + base64Encoded + "\r\n");
    
    BASE64Decoder b64d = new sun.misc.BASE64Decoder();
    String base64Decoded = new String(b64d.decodeBuffer(base64Encoded));
    
    System.out.println("Decoded text:\r\n" + base64Decoded);
  }
}
