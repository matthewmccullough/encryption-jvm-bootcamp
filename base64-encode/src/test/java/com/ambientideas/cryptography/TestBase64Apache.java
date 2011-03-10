package com.ambientideas.cryptography;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestBase64Apache {
    @Test
    public void testSimplerBase64Apache() throws UnsupportedEncodingException {
        final String message1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        System.out.println("Apache Raw text:\r\n" + message1);
        System.out.println("Apache Raw text byte count: " + message1.length() + "\r\n");
        Assert.assertEquals(39, message1.length());

        String base64Encoded = Base64.encodeBase64String(message1.getBytes("UTF8"));
        
        System.out.println("Apache Base64 encoded text:\r\n" + base64Encoded);
        System.out.println("Apache Base64 encoded text byte count: "
                + base64Encoded.length() + "\r\n");

        //Isn't it awesome that it has newlines embedded?
        final String BASE64ENCODED = new String("YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFh\r\n");
        System.out.println("Apache Base64 encoded CONSTANT:\r\n" + BASE64ENCODED);
        Assert.assertEquals(BASE64ENCODED, base64Encoded);

        String base64Decoded = new String(Base64.decodeBase64(base64Encoded), "UTF-8");
        System.out.println("Apache Decoded text:\r\n" + base64Decoded);
        Assert.assertEquals(message1, base64Decoded);
    }
    
    @Test
    public void testBase64Apache() throws UnsupportedEncodingException {
        final String message1 = "This is <bold>code</bold> with \t tabs, semicolons;"
                + " and alert-bells \b that could cause escaping issues if not encoded in Base64";

        System.out.println("Apache Raw text:\r\n" + message1);
        System.out.println("Apache Raw text byte count: " + message1.length() + "\r\n");
        Assert.assertEquals(126, message1.length());

        String base64Encoded = Base64.encodeBase64String(message1.getBytes("UTF8"));
        
        System.out.println("Apache Base64 encoded text:\r\n" + base64Encoded);
        System.out.println("Apache Base64 encoded text byte count: "
                + base64Encoded.length() + "\r\n");

        //Isn't it awesome that it has newlines embedded?
        final String BASE64ENCODED = new String("VGhpcyBpcyA8Ym9sZD5jb2RlPC9ib2xkPiB3aXRoIAkgdGFicywgc2VtaWNvbG9uczsgYW5kIGFs\r\n"+
                "ZXJ0LWJlbGxzIAggdGhhdCBjb3VsZCBjYXVzZSBlc2NhcGluZyBpc3N1ZXMgaWYgbm90IGVuY29k\r\n"+
                "ZWQgaW4gQmFzZTY0\r\n");
        System.out.println("Apache Base64 encoded CONSTANT:\r\n" + BASE64ENCODED);
        Assert.assertEquals(BASE64ENCODED, base64Encoded);

        String base64Decoded = new String(Base64.decodeBase64(base64Encoded), "UTF-8");
        System.out.println("Apache Decoded text:\r\n" + base64Decoded);
        Assert.assertEquals(message1, base64Decoded);
    }
}
