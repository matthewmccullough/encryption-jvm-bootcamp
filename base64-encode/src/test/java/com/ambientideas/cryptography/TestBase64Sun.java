package com.ambientideas.cryptography;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestBase64Sun {
    @Test
    public void testBase64Apache() throws IOException {
        final String message1 = "This is <bold>code</bold> with \t tabs, semicolons;"
                + " and alert-bells \b that could cause escaping issues if not encoded in Base64";

        System.out.println("Sun Raw text:\r\n" + message1);
        System.out.println("Sun Raw text byte count: " + message1.length() + "\r\n");
        Assert.assertEquals(126, message1.length());

        BASE64Encoder b64e = new sun.misc.BASE64Encoder();
        String base64Encoded = b64e.encode(message1.getBytes("UTF-8"));

        System.out.println("Sun Base64 encoded text:\r\n" + base64Encoded);
        System.out.println("Sun Base64 encoded text byte count: "
                + base64Encoded.length() + "\r\n");
        
        //Isn't it awesome that it has newlines embedded?
        Assert.assertEquals(
                "VGhpcyBpcyA8Ym9sZD5jb2RlPC9ib2xkPiB3aXRoIAkgdGFicywgc2VtaWNvbG9uczsgYW5kIGFs\n" +
                "ZXJ0LWJlbGxzIAggdGhhdCBjb3VsZCBjYXVzZSBlc2NhcGluZyBpc3N1ZXMgaWYgbm90IGVuY29k\n" +
                "ZWQgaW4gQmFzZTY0", base64Encoded);

        BASE64Decoder b64d = new sun.misc.BASE64Decoder();
        String base64Decoded = new String(b64d.decodeBuffer(base64Encoded), "UTF-8");

        System.out.println("Sun Decoded text:\r\n" + base64Decoded);
        Assert.assertEquals(message1, base64Decoded);
    }
}
