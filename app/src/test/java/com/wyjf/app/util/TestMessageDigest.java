package com.wyjf.app.util;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestMessageDigest {
    private static final Logger log = LoggerFactory.getLogger(TestMessageDigest.class);

    @Test
    public void testSHA256Degest(){
        String text = "123";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            String output = Hex.encodeHexString(hash);
            log.info(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
