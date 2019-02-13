package com.fuelconsumption.persistance.exception;

public class PersistenceServiceException extends Exception {

	private static final long serialVersionUID = 1207428295818535206L;

	public PersistenceServiceException(final String message) {
		super(message);
	}

	public PersistenceServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
