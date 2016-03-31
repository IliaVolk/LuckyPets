package com.springapp.mvc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestEncoder {
    @Test
    public void testEncoder() throws NoSuchAlgorithmException {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String realPassword = "1234";
        String code = "7110eda4d09e062aa5e4a390b0a572ac0d2c0220";
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(realPassword.getBytes());

        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        Assert.assertEquals(sb.toString(), code);

    }
}
