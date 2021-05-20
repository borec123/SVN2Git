package com.example.consumingwebservice.util;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class Sha256HashUtilTest {

	@Test
	void testMessage() throws NoSuchAlgorithmException {
		
		
		String payment = "accountNumber=1661011&bankCode=0800&amount=100.00&currency=1&merchantData=12345";
		String paymentHash = Sha256HashUtil.hash(payment);
		
		String msg = "merchantId=1&accountId=1&payments=" + paymentHash + "&password=test";
		String msgHash = Sha256HashUtil.hash(msg);
		
		assertEquals("d9c916bc3022be72d645051ab2b71c6d5680628865442c5dcf1b2fe6ca1fd75c", msgHash);
	}

	@Test
	void testNull() {
		try {
			Sha256HashUtil.hash(null);
			fail("IllegalArgumentException should be thrown.");
			
		} catch (IllegalArgumentException e) {
			// OK - test passed.
		}
	}

	@Test
	void testEmpty() {
		try {
			System.out.println(Sha256HashUtil.hash(""));
			fail("IllegalArgumentException should be thrown.");
			
		} catch (IllegalArgumentException e) {
			// OK - test passed.
		}
	}

}
