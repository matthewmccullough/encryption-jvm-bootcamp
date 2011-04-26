package com.ambientideas.cryptography;

import java.io.IOException;

import junit.framework.Assert;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestBase64BouncyCastle {
    @Test
    public void testBase64Apache() throws IOException {
        final String message1 = "This is <bold>code</bold> with \t tabs, semicolons;"
                + " and alert-bells \b that could cause escaping issues if not encoded in Base64";

        System.out.println("BouncyCastle Raw text:\r\n" + message1);
        System.out.println("BouncyCastle Raw text byte count: " + message1.length() + "\r\n");
        Assert.assertEquals(126, message1.length());

        byte[] base64Encoded = Base64.encode(message1.getBytes("UTF-8"));

        System.out.println("BouncyCastle Base64 encoded text:\r\n" + base64Encoded);
        System.out.println("BouncyCastle Base64 encoded text byte count: "
                + base64Encoded.length + "\r\n");
        
        //Isn't it awesome that BouncyCastle, unlike the others, has NO newlines embedded?
        Assert.assertEquals(
                "VGhpcyBpcyA8Ym9sZD5jb2RlPC9ib2xkPiB3aXRoIAkgdGFicywgc2VtaWNvbG9uczsgYW5kIGFs" +
                "ZXJ0LWJlbGxzIAggdGhhdCBjb3VsZCBjYXVzZSBlc2NhcGluZyBpc3N1ZXMgaWYgbm90IGVuY29k" +
                "ZWQgaW4gQmFzZTY0", new String(base64Encoded));

        String base64Decoded = new String(Base64.decode(base64Encoded), "UTF-8");

        System.out.println("BouncyCastle Decoded text:\r\n" + base64Decoded);
        Assert.assertEquals(message1, base64Decoded);
    }
}
