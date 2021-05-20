package com.example.consumingwebservice;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.example.consumingwebservice.wsdl.Payment;

public class PaymentFactory {

	public static Payment createPayment(String accountNumber, String bankCode, double d, String merchantData) {
		Payment payment = new Payment();
		payment.setAccountNumber(accountNumber);
		payment.setBankCode(bankCode);
		BigDecimal amount = BigDecimal.valueOf(100.00);
		amount.setScale(2);
		payment.setAmount(amount);

		//--- Currency CZK has value 1.
		BigInteger b = BigInteger.ONE;
		QName q = new QName("currency");
		
		JAXBElement<BigInteger> currency = new JAXBElement<BigInteger>(q, BigInteger.class, b);
		
		payment.setCurrency(currency);
		payment.setMerchantData(merchantData);
		return payment;
	}

}
