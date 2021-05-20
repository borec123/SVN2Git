
package com.example.consumingwebservice;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.util.Sha256HashUtil;
import com.example.consumingwebservice.wsdl.Payment;
import com.example.consumingwebservice.wsdl.Payments;
import com.example.consumingwebservice.wsdl.SendPaymentsRequest;
import com.example.consumingwebservice.wsdl.SendPaymentsResponse;

public class CountryClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(CountryClient.class);
	private static final String DEMO_URI = "https://www.thepay.cz/sender-demo-gate/api/payments/?v=2";
	
	private static final String WSDL_DEMO_URI = "https://www.thepay.cz/sender-demo-gate/api/payments-api-demo.wsdl";

	public SendPaymentsResponse sendPayment(Payment payment, BigInteger merchantId, BigInteger accountId) {
		Assert.notNull(merchantId, "merchantId is mandatory.");
		Assert.notNull(accountId, "accountId is mandatory.");
		Assert.notNull(payment, "payment is mandatory.");

		SendPaymentsRequest request = new SendPaymentsRequest();
		request.setAccountId(accountId);
		request.setMerchantId(merchantId);
		
		
		/*
		 * Payment payment = new Payment(); payment.setAccountNumber("1661011");
		 * payment.setBankCode("0800"); BigDecimal amount = BigDecimal.valueOf(100.00);
		 * amount.setScale(2); payment.setAmount(amount);
		 * 
		 * BigInteger b = BigInteger.ONE;
		 * 
		 * QName q = new QName("currency");
		 * 
		 * JAXBElement<BigInteger> currency = new JAXBElement<BigInteger>(q,
		 * BigInteger.class, b);
		 * 
		 * payment.setCurrency(currency); payment.setMerchantData("12345");
		 */
		
		Payments payments = new Payments();
		payments.getPayment().add(payment);
		
		request.setPayments(payments);
		
		String paymentHash = getPaymentHash(payment);

		request.setSignature("d9c916bc3022be72d645051ab2b71c6d5680628865442c5dcf1b2fe6ca1fd75c");



		SendPaymentsResponse response = (SendPaymentsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(DEMO_URI, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}

	private String getPaymentHash(Payment payment) {
		Assert.hasText(payment.getAccountNumber(), "AccountNumber is mandatory.");
		Assert.hasText(payment.getBankCode(), "BankCode is mandatory.");
		Assert.notNull(payment.getAmount(), "Amount is mandatory.");
		Assert.hasText(payment.getMerchantData(), "AccountNumber is mandatory.");
		
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.hasText(payment.getAccountPrefix().getValue())) { sb.append("accountPrefix=" + payment.getAccountPrefix().getValue() + "&"); }
		
		sb.append("accountNumber=" + payment.getAccountNumber());
		
		sb.append("&bankCode=" + payment.getBankCode());
		
		sb.append("&amount=" + payment.getAmount());
		
		if(payment.getCurrency().getValue() != null) { sb.append("&currency=" + payment.getCurrency().getValue()); }

		if(StringUtils.hasText(payment.getVs().getValue())) { sb.append("&vs=" + payment.getVs().getValue()); }
		
		if(StringUtils.hasText(payment.getSs().getValue())) { sb.append("&ss=" + payment.getSs().getValue()); }
		
		if(StringUtils.hasText(payment.getKs().getValue())) { sb.append("&ks=" + payment.getKs().getValue()); }
		
		sb.append("&merchantData=" + payment.getMerchantData());
		
		if(StringUtils.hasText(payment.getDescription().getValue())) { sb.append("&description=" + payment.getDescription().getValue()); }
		
		if(StringUtils.hasText(payment.getMerchantDescription().getValue())) { sb.append("&merchantDescription=" + payment.getMerchantDescription().getValue()); }
		
		if(StringUtils.hasText(payment.getEmail().getValue())) { sb.append("&email=" + payment.getEmail().getValue()); }
		
		return Sha256HashUtil.hash(sb.toString());
	}

}
