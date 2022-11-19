package com.noon.guestparking.exceptions;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class IllegalAccessException extends ServiceException {

	public IllegalAccessException(String message) {
		super(message);
	}

	public IllegalAccessException(Throwable cause) {
		super(cause);
	}

	public IllegalAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAccessException(List<String> errorCodes) {
		super(errorCodes);
	}

	public IllegalAccessException(String message, List<String> errorCodes) {
		super(message, errorCodes);
	}

	public IllegalAccessException(String message, List<String> errorCodes, Throwable cause) {
		super(message, errorCodes, cause);
	}

	public IllegalAccessException(List<String> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	public IllegalAccessException(String message, List<String> errorCodes, String statusCode, Throwable cause) {
		super(message, errorCodes, statusCode, cause);
	}

	public IllegalAccessException(List<String> errorCodes, String statusCode, Throwable cause) {
		super(errorCodes, statusCode, cause);
	}

	public IllegalAccessException(String message, List<String> errorCodes, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, enableSuppression, writableStackTrace, cause);
	}

	public IllegalAccessException(String message, List<String> errorCodes, String statusCode, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, errorCodes, statusCode, enableSuppression, writableStackTrace, cause);
	}

	public IllegalAccessException(Throwable cause, List<String> errorCodes) {
		super(errorCodes, cause);
	}

	public IllegalAccessException(String message, Throwable cause, List<String> errorCodes, String statusCode) {
		super(message, errorCodes, statusCode, cause);
	}

	public IllegalAccessException(Throwable cause, List<String> errorCodes, String statusCode) {
		super(errorCodes, statusCode, cause);
	}
}
