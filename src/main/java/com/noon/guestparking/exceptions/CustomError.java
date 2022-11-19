package com.noon.guestparking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CustomError {
	private Integer code;
	private String status;
	private String message;
	private String traceId;
	private List<String> errorCodes;
	private String stackTrace;

	public CustomError(final Integer code, final String status, final String message, final String traceId) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.traceId = traceId;
	}

	public CustomError(final Integer code, final String status, final String message, final String traceId,
			final List<String> errorCodes) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.traceId = traceId;
		this.errorCodes = errorCodes;
	}

	public CustomError(final Integer code, final String status, final String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public CustomError(final Integer code, final String status, final List<String> errorCodes) {
		this.code = code;
		this.status = status;
		this.errorCodes = errorCodes;
	}
}