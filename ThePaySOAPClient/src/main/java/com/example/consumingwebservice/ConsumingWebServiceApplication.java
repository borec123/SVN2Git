
package com.example.consumingwebservice;

import java.math.BigInteger;

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
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			String country = "Spain";

			if (args.length > 0) {
				country = args[0];
			}
			Payment payment = PaymentFactory.createPayment("1661011", "0800", 100.00, "12345");
			SendPaymentsResponse response = quoteClient.sendPayment(payment , BigInteger.ONE, BigInteger.ONE);
			System.out.println("Status: " + response.getStatus());
			System.out.println("Err desc: " + response.getErrorDescription());

		};
	}

}
