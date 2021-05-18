package com.example.producingwebservice;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.producingwebservice.generated.Payment;
import com.example.producingwebservice.generated.SentPaymentsRequest;
import com.example.producingwebservice.generated.SentPaymentsResponse;



@Endpoint
public class PaymentEndpoint {

	private static final String OK = "OK";


	
	@PayloadRoot(namespace = WebServiceConfig.DEMO_URI, localPart = "sentPaymentsRequest")
	@ResponsePayload
	public SentPaymentsResponse receivePayments(@RequestPayload SentPaymentsRequest request) {
		SentPaymentsResponse response = new SentPaymentsResponse();
		
		processPayments(request.getPayments().getPayment());
		
		response.setStatus(OK);

		return response;
	}



	private void processPayments(List<Payment> payments) {
		payments.forEach(p -> System.out.println(p.getAccountNumber() + ", amount: "
		+ p.getAmount() + ", status: " + p.getStatus() + ", error: " + p.getErrorDescription()));
		
		for (Payment payment : payments) {
			ThePayPaymentState state = ThePayPaymentState.valueOf(payment.getStatus());
			switch(state) {
			case SUCCESSFULLY_SENT:
				// Completed
				break;
			case CANCELED:
			case REJECTED_BY_BANK:
			case BOUNCED_BACK:
			case RECLAIMED:
			case RECLAIMED_RETURNED_BY_CUSTOMER:
			case RECLAIMED_UNSUCCESSFULLY:
			case RETURNED_BY_CUSTOMER:
				// Error
				break;
			}
		}
		

		
		
	}
}
