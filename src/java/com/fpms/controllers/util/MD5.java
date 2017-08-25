/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author aabello
 */
public class MD5 {
    
    public static String hash(String hashString) {
	try {
	    byte[] passByte = hashString.getBytes();
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(passByte);

	    byte messageDigest[] = md.digest();

	    StringBuilder hexString = new StringBuilder();

	    for (int i=0; i < messageDigest.length; i++) {
		String hex = Integer.toHexString(0xFF & messageDigest[i]);
		if (hex.length() < 2) hex = "0" + hex;
		hexString.append(hex);
	    }

	    return hexString.toString();
	} catch (NoSuchAlgorithmException ex) {
	    throw new RuntimeException(ex);
	}
    }
    
}
