package com.capgemini.bankapp.exception;

public class DebitLimitExceededException extends RuntimeException {

	public DebitLimitExceededException(String message) {
		super(message);
	}

}
