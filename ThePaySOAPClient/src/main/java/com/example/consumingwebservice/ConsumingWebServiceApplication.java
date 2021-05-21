
package com.example.consumingwebservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.Payment;
import com.example.consumingwebservice.wsdl.SendPaymentsResponse;

@SpringBootApplication
public class ConsumingWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(SOAPClient quoteClient) {
		return args -> {
			
			Payment payment = new PaymentBuilder("7927781", "0710", BigDecimal.valueOf(100.00), "12345")
					.setAccountPrefix("1011")
					.setCurrency(BigInteger.ONE)
					.setVs("123")
					.setSs("123")
					.setKs("123")
					.setDescription("asdgfsdfag")
					.setMerchantDescription("asdgfsdfag")
					.setEmail("aa@seznam.cz")
					.build();
			
			Payment payment2 = new PaymentBuilder("1661011", "0800", BigDecimal.valueOf(101.009), "12345")
					.build();
			
			send(Collections.singletonList(payment), quoteClient);
			
			send(Collections.singletonList(payment2), quoteClient);
			
			//--- send both in one message:
			send(Stream.of(payment, payment2).collect(Collectors.toList()), quoteClient);
			
		};
	}

	private void send(List<Payment> paymentList, SOAPClient quoteClient) {
		SendPaymentsResponse response = quoteClient.sendPayments(paymentList , BigInteger.ONE, BigInteger.ONE);
		System.out.println("Status: " + response.getStatus());
		System.out.println("Error description: " + response.getErrorDescription());
	}

}
