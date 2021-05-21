
package com.example.consumingwebservice;

import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

public class SOAPClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(SOAPClient.class);
	private static final String DEMO_URI = "https://www.thepay.cz/sender-demo-gate/api/payments/?v=2";
	
	//private static final String WSDL_DEMO_URI = "https://www.thepay.cz/sender-demo-gate/api/payments-api-demo.wsdl";
	private static final String PASSWORD = "test";


	public SendPaymentsResponse sendPayments(List<Payment> paymentList, BigInteger merchantId, BigInteger accountId) {
		Assert.notNull(merchantId, "merchantId is mandatory.");
		Assert.notNull(accountId, "accountId is mandatory.");
		Assert.notNull(paymentList, "payments are mandatory.");
		Assert.notEmpty(paymentList, "payments must have at least one payment.");

		SendPaymentsRequest request = new SendPaymentsRequest();
		request.setAccountId(accountId);
		request.setMerchantId(merchantId);
		
		
		Payments payments = new Payments();
		payments.getPayment().addAll(paymentList);
		request.setPayments(payments);
		
		String hash = createHash(merchantId, accountId, paymentList);
		request.setSignature(hash);

		SendPaymentsResponse response = (SendPaymentsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(DEMO_URI, request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}

	private String createHash(BigInteger merchantId, BigInteger accountId, List<Payment> paymentList) {
		
		String allPaymentsHash = paymentList.stream().map(payment -> getPaymentHash(payment)).collect(Collectors.joining("|"));
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("merchantId=" + merchantId);
		
		sb.append("&accountId=" + accountId);

		sb.append("&payments=" + allPaymentsHash);
		
		sb.append("&password=" + PASSWORD);

		if(log.isDebugEnabled()) {
			log.debug("hash: " + sb.toString());
		}
		
		return Sha256HashUtil.hash(sb.toString());
	}

	private String getPaymentHash(Payment payment) {
		Assert.hasText(payment.getAccountNumber(), "AccountNumber is mandatory.");
		Assert.hasText(payment.getBankCode(), "BankCode is mandatory.");
		Assert.notNull(payment.getAmount(), "Amount is mandatory.");
		Assert.hasText(payment.getMerchantData(), "MerchantData is mandatory.");
		
		StringBuilder sb = new StringBuilder();
		
		if(payment.getAccountPrefix() != null && StringUtils.hasText(payment.getAccountPrefix().getValue())) { sb.append("accountPrefix=" + payment.getAccountPrefix().getValue() + "&"); }
		
		sb.append("accountNumber=" + payment.getAccountNumber());
		
		sb.append("&bankCode=" + payment.getBankCode());
		
		sb.append("&amount=" + String.format(Locale.US, "%.02f", payment.getAmount().doubleValue()));
		
		if(payment.getCurrency() != null && payment.getCurrency().getValue() != null) { sb.append("&currency=" + payment.getCurrency().getValue()); }

		if(payment.getVs() != null && StringUtils.hasText(payment.getVs().getValue())) { sb.append("&vs=" + payment.getVs().getValue()); }
		
		if(payment.getSs() != null && StringUtils.hasText(payment.getSs().getValue())) { sb.append("&ss=" + payment.getSs().getValue()); }
		
		if(payment.getKs() != null && StringUtils.hasText(payment.getKs().getValue())) { sb.append("&ks=" + payment.getKs().getValue()); }
		
		sb.append("&merchantData=" + payment.getMerchantData());
		
		if(payment.getDescription() != null && StringUtils.hasText(payment.getDescription().getValue())) { sb.append("&description=" + payment.getDescription().getValue()); }
		
		if(payment.getMerchantDescription() != null && StringUtils.hasText(payment.getMerchantDescription().getValue())) { sb.append("&merchantDescription=" + payment.getMerchantDescription().getValue()); }
		
		if(payment.getEmail() != null && StringUtils.hasText(payment.getEmail().getValue())) { sb.append("&email=" + payment.getEmail().getValue()); }
		
		if(log.isDebugEnabled()) {
			log.debug("paymentHash: " + sb.toString());
		}
		
		return Sha256HashUtil.hash(sb.toString());
	}

}
