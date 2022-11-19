package com.noon.guestparking.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ServiceException extends Exception {

	private List<String> errorCodes;
	private String statusCode;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, List<String> errorCodes) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, List<String> errorCodes, Throwable cause) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public ServiceException(List<String> errorCodes, Throwable cause) {
		super(cause);
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, List<String> errorCodes, String statusCode, Throwable cause) {
		super(message, cause);
		this.errorCodes = errorCodes;
		this.statusCode = statusCode;
	}

	public ServiceException(List<String> errorCodes, String statusCode, Throwable cause) {
		super(cause);
		this.errorCodes = errorCodes;
		this.statusCode = statusCode;
	}

	public ServiceException(String message, List<String> errorCodes, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, List<String> errorCodes, String statusCode, boolean enableSuppression,
			boolean writableStackTrace, Throwable cause) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCodes = errorCodes;
		this.statusCode = statusCode;
	}
}
