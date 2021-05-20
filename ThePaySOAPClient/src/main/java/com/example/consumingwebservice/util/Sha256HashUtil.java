package com.example.consumingwebservice.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Assert;

public class Sha256HashUtil {

	public static String hash(String arg) {
		//Assert.notNull(arg, "Agrument arg cannot be null.");
		Assert.hasText(arg, "Agrument arg cannot be empty.");
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		byte[] hash = digest.digest(arg.getBytes(StandardCharsets.UTF_8));
		return byteArrayToHex(hash);

	}
	
	public static String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b));
		   return sb.toString();
	}
}
