package com.example.consumingwebservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CountryClientTest {

	@Test
	void testRounding() {
		
		
		System.out.printf(Locale.US, "%.02f", 3.0);
		assertEquals("3.00", String.format(Locale.US, "%.02f", 3.0));
		
		System.out.println();
		
		System.out.printf(Locale.US, "%.02f", 3.009);
		assertEquals("3.01", String.format(Locale.US, "%.02f", 3.009));
		
		System.out.println();

		System.out.printf(Locale.US, "%.02f", 3.011);
		assertEquals("3.01", String.format(Locale.US, "%.02f", 3.011));
		
	}
	
	
}

