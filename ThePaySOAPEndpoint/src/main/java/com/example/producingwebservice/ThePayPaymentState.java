package com.example.producingwebservice;

import java.util.Arrays;
import java.util.Optional;

public enum ThePayPaymentState {

	WAITING_TO_BE_SEND("1"),
	SUCCESSFULLY_SENT("2"),
	CANCELED("3"),
	REJECTED_BY_BANK("4"),
	ACTUALLY_BEING_PROCESSED("5"),
	BOUNCED_BACK("6"),   // (E.G. ACCOUNT NOT EXISTS)
	RECLAIMED("7"),
	RECLAIMED_RETURNED_BY_CUSTOMER("8"),
	RECLAIMED_UNSUCCESSFULLY("9"),
	RETURNED_BY_CUSTOMER("11");

	private final String stateNumber;

	ThePayPaymentState(final String stateNumber) {
		this.stateNumber = stateNumber;
	}

    public static ThePayPaymentState getByStateNumber(final String value) {
    	Optional<ThePayPaymentState> state = Arrays.stream(values())  
    			.filter(s -> s.stateNumber.equals(value))
                .findFirst();
    	if(!state.isPresent()) {
    		throw new IllegalArgumentException("No such ThePay payment state: " + value);
    	}
		return state.get();
    }
    
}
