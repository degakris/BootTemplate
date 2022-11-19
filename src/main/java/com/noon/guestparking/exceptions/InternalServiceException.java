package com.noon.guestparking.exceptions;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class InternalServiceException extends ServiceException {

	public InternalServiceException(String message) {
		super(message);
	}

	public InternalServiceException(Throwable cause) {
		super(cause);
	}

	public InternalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServiceException(List<String> errorCodes) {
		super(errorCodes);
	}

	public InternalServiceException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public InternalServiceException(String message, List<String> errorCodes, Throwable cause) {
		super(message, errorCodes, cause);
	}

	public InternalServiceException(List<String> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	public InternalServiceException(String message, List<String> errorCodes, String statusCode, Throwable cause) {
		super(message, errorCodes, statusCode, cause);
	}

	public InternalServiceException(List<String> errorCodes, String statusCode, Throwable cause) {
		super(errorCodes, statusCode, cause);
	}

	public InternalServiceException(String message, List<String> errorCodes, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, enableSuppression, writableStackTrace, cause);
	}

	public InternalServiceException(String message, List<String> errorCodes, String statusCode,
			boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, statusCode, enableSuppression, writableStackTrace, cause);
	}
}

