package com.example.producingwebservice;

import java.util.Arrays;

public enum ThePayPaymentState {

	WAITING_TO_BE_SEND(1),
	SUCCESSFULLY_SENT(2),
	CANCELED(3),
	REJECTED_BY_BANK(4),
	ACTUALLY_BEING_PROCESSED(5),
	BOUNCED_BACK(6),   // (E.G. ACCOUNT NOT EXISTS)
	RECLAIMED(7),
	RECLAIMED_RETURNED_BY_CUSTOMER(8),
	RECLAIMED_UNSUCCESSFULLY(9),
	RETURNED_BY_CUSTOMER(11);

	private int value;

	ThePayPaymentState(final int i) {
		value = i;
	}

	public int getValue() {
		return value;
	}

    public static ThePayPaymentState valueOf(int value) {
        return Arrays.stream(values())  .filter(s -> s.value == value)
                .findFirst().get();
    }
    
}
