package com.example.consumingwebservice;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.example.consumingwebservice.wsdl.Payment;
import com.example.consumingwebservice.wsdl.SendPaymentsResponse;

public class CountryClientTest {

	@Test
	void testMessage() {
		CountryClient quoteClient = new CountryClient();
		Payment payment = PaymentFactory.createPayment("1661011", "0800", 100.00, "12345");
		SendPaymentsResponse response = quoteClient.sendPayment(payment , BigInteger.ONE, BigInteger.ONE);
		System.out.println("Status: " + response.getStatus());
	}
	
	
}

