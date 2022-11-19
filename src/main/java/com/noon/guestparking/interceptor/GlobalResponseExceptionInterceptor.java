package com.noon.guestparking.interceptor;

import com.noon.guestparking.constants.ApplicationConstants;
import com.noon.guestparking.exceptions.CustomError;
import com.noon.guestparking.exceptions.InvalidArgumentException;
import com.noon.guestparking.exceptions.IllegalAccessException;
import com.noon.guestparking.exceptions.ServiceException;
import com.noon.guestparking.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalResponseExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseDto<Object>> handleConstraintViolationException(final ConstraintViolationException ex,
			final WebRequest request) {
		log.error("ConstraintViolationException", ex);
		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "CONSTRAINT_VIOLATION",
				ex.getMessage(), request.getHeader(ApplicationConstants.TRACE_ID));
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<ResponseDto<Object>> handleInvalidArgumentException(final InvalidArgumentException ex,
			final WebRequest request) {
		log.error("InvalidArgumentException", ex);
		final String status = ex.getStatusCode() != null ? ex.getStatusCode() : "INVALID_ARGUMENT";
		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), status, ex.getMessage(),
				request.getHeader(ApplicationConstants.TRACE_ID), ex.getErrorCodes());
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseDto<Object>> handleMethodArgumentTypeMismatch(
			final MethodArgumentTypeMismatchException ex, final WebRequest request) {
		log.error("MethodArgumentTypeMismatchException", ex);
		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "METHOD_ARGUMENT_TYPE_MISMATCH",
				ex.getMessage(), request.getHeader(ApplicationConstants.TRACE_ID));
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<ResponseDto<Object>> handleAccessException(final IllegalAccessException ex,
			final WebRequest request) {
		log.error("IllegalAccessException", ex);
		final String status = ex.getStatusCode() != null ? ex.getStatusCode() : "ILLEGAL_ACCESS";
		final CustomError customError = new CustomError(HttpStatus.UNAUTHORIZED.value(), status, ex.getMessage(),
				request.getHeader(ApplicationConstants.TRACE_ID), ex.getErrorCodes());
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ResponseDto<Object>> handleServiceException(final ServiceException ex,
			final WebRequest request) {
		log.error("serviceException", ex);
		final String status = ex.getStatusCode() != null ? ex.getStatusCode() : "SERVICE_EXCEPTION";
		final CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(), status,
				ex.getMessage(), request.getHeader(ApplicationConstants.TRACE_ID), ex.getErrorCodes());
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto<Object>> handleAll(final Exception ex, final WebRequest request) {
		log.error("GeneralException", ex);
		final CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"INTERNAL_SERVER_ERROR",
				ex.getMessage(), request.getHeader(ApplicationConstants.TRACE_ID));
		return new ResponseEntity<>(new ResponseDto<>(false, null, customError), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
