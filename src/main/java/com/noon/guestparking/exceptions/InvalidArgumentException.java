package com.noon.guestparking.exceptions;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class InvalidArgumentException extends ServiceException {

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(List<String> errorCodes) {
		super(errorCodes);
	}

	public InvalidArgumentException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public InvalidArgumentException(String message, List<String> errorCodes, Throwable cause) {
		super(message, errorCodes, cause);
	}

	public InvalidArgumentException(List<String> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	public InvalidArgumentException(String message, List<String> errorCodes, String statusCode, Throwable cause) {
		super(message, errorCodes, statusCode, cause);
	}

	public InvalidArgumentException(List<String> errorCodes, String statusCode, Throwable cause) {
		super(errorCodes, statusCode, cause);
	}

	public InvalidArgumentException(String message, List<String> errorCodes, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, enableSuppression, writableStackTrace, cause);
	}

	public InvalidArgumentException(String message, List<String> errorCodes, String statusCode,
			boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, statusCode, enableSuppression, writableStackTrace, cause);
	}
}


