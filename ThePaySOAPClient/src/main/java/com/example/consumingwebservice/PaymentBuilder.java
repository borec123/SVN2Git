package com.example.consumingwebservice;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.example.consumingwebservice.wsdl.Payment;

public class PaymentBuilder {

    protected String accountPrefix;

    protected String accountNumber;

    protected String bankCode;

    protected BigDecimal amount;

    protected BigInteger currency;

    protected String vs;

    protected String ss;

    protected String ks;

    protected String merchantData;

    protected String description;

    protected String merchantDescription;

    protected String email;
    
    
    public PaymentBuilder(String accountNumber, String bankCode, BigDecimal amount, String merchantData) {

		this.accountNumber = accountNumber;    	
		this.bankCode = bankCode;    	
		this.amount = amount;    	
		this.merchantData = merchantData;    	
    	
		/*
		 * Payment payment = new Payment(); payment.setAccountNumber(accountNumber);
		 * payment.setBankCode(bankCode); BigDecimal amount =
		 * BigDecimal.valueOf(100.00);
		 * 
		 * payment.setAmount(amount);
		 * 
		 * //--- Currency CZK has value 1. BigInteger b = BigInteger.ONE; QName q = new
		 * QName("currency");
		 * 
		 * JAXBElement<BigInteger> currency = new JAXBElement<BigInteger>(q,
		 * BigInteger.class, b);
		 * 
		 * payment.setCurrency(currency); payment.setMerchantData(merchantData);
		 */

	}

    public Payment build() {
    	Payment payment = new Payment();

    	//--- mandatory fields:
    	payment.setAccountNumber(accountNumber);
    	payment.setBankCode(bankCode);
    	payment.setAmount(amount);
    	payment.setMerchantData(merchantData);
    	
    	//--- voluntary fields:
    	if(accountPrefix != null) {
    		QName q = new QName("accountPrefix");
    		JAXBElement<String> accountPrefixE = new JAXBElement<String>(q, String.class, accountPrefix);
    		payment.setAccountPrefix(accountPrefixE);
    	}
    	
    	if(currency != null) {
    		QName q = new QName("currency");
     		JAXBElement<BigInteger> currencyE = new JAXBElement<BigInteger>(q, BigInteger.class, currency);
    		payment.setCurrency(currencyE);
    	}
    	
    	if(vs != null) {
    		QName q = new QName("vs");
    		JAXBElement<String> vsE = new JAXBElement<String>(q, String.class, vs);
    		payment.setVs(vsE);
    	}
    	
    	if(ss != null) {
    		QName q = new QName("ss");
    		JAXBElement<String> ssE = new JAXBElement<String>(q, String.class, ss);
    		payment.setSs(ssE);
    	}
    	
    	if(ks != null) {
    		QName q = new QName("ks");
    		JAXBElement<String> ksE = new JAXBElement<String>(q, String.class, ks);
    		payment.setKs(ksE);
    	}
    	
    	if(description != null) {
    		QName q = new QName("description");
    		JAXBElement<String> descriptionE = new JAXBElement<String>(q, String.class, description);
    		payment.setDescription(descriptionE);
    	}
    	
    	if(merchantDescription != null) {
    		QName q = new QName("merchantDescription");
    		JAXBElement<String> merchantDescriptionE = new JAXBElement<String>(q, String.class, merchantDescription);
    		payment.setMerchantDescription(merchantDescriptionE);
    	}
    	
    	if(email != null) {
    		QName q = new QName("email");
    		JAXBElement<String> emailE = new JAXBElement<String>(q, String.class, email);
    		payment.setEmail(emailE);
    	}
    	
		return payment;    	
    }

	public PaymentBuilder setAccountPrefix(String accountPrefix) {
		this.accountPrefix = accountPrefix;
		return this;
	}


	public PaymentBuilder setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return this;
	}


	public PaymentBuilder setBankCode(String bankCode) {
		this.bankCode = bankCode;
		return this;
	}


	public PaymentBuilder setAmount(BigDecimal amount) {
		this.amount = amount;
		return this;
	}


	public PaymentBuilder setCurrency(BigInteger currency) {
		this.currency = currency;
		return this;
	}


	public PaymentBuilder setVs(String vs) {
		this.vs = vs;
		return this;
	}


	public PaymentBuilder setSs(String ss) {
		this.ss = ss;
		return this;
	}


	public PaymentBuilder setKs(String ks) {
		this.ks = ks;
		return this;
	}


	public PaymentBuilder setMerchantData(String merchantData) {
		this.merchantData = merchantData;
		return this;
	}


	public PaymentBuilder setDescription(String description) {
		this.description = description;
		return this;
	}


	public PaymentBuilder setMerchantDescription(String merchantDescription) {
		this.merchantDescription = merchantDescription;
		return this;
	}


	public PaymentBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

}
