package com.system.edu.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;

@Component
public class HashService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public String hash(String str) {
        try {
            byte[] message = str.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(message, 0, message.length);
            byte messageDigest[] = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }

            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            logger.error("Error during encoding", e);
        }

        return "";
    }
}