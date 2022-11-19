package com.noon.guestparking.exceptions.interceptor;

import com.noon.guestparking.constants.ApplicationConstants;
import com.noon.guestparking.constants.TestConstants;
import com.noon.guestparking.exceptions.CustomError;
import com.noon.guestparking.exceptions.InvalidArgumentException;
import com.noon.guestparking.exceptions.IllegalAccessException;
import com.noon.guestparking.exceptions.ServiceException;
import com.noon.guestparking.interceptor.GlobalResponseExceptionInterceptor;
import com.noon.guestparking.response.ResponseDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
public class GlobalResponseDtoExceptionInterceptorTest {

	@Mock
	private ConstraintViolationException constraintViolationException;
	@Mock
	private WebRequest webRequest;
	@Mock
	private MethodArgumentTypeMismatchException methodArgumentTypeMismatchException;
	@Mock
	private Exception exception;
	@Mock
	private IllegalAccessException illegalAccessException;
	@Mock
	private InvalidArgumentException invalidArgumentException;
	@Mock
	private ServiceException serviceException;

	@InjectMocks
	private GlobalResponseExceptionInterceptor globalResponseExceptionInterceptor;

	@Test
	public void constraintViolationExceptionTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(constraintViolationException.getMessage()).thenReturn("constraintViolationException message");
		Mockito.when(constraintViolationException.getStackTrace())
				.thenReturn(trace);
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleConstraintViolationException(constraintViolationException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "CONSTRAINT_VIOLATION",
				"constraintViolationException message", TestConstants.TRACE_ID, null);
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleMethodArgumentTypeMismatchTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(methodArgumentTypeMismatchException.getMessage())
				.thenReturn("methodArgumentTypeMismatchException message");
		Mockito.when(methodArgumentTypeMismatchException.getStackTrace())
				.thenReturn(trace);
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleMethodArgumentTypeMismatch(methodArgumentTypeMismatchException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "METHOD_ARGUMENT_TYPE_MISMATCH",
				"methodArgumentTypeMismatchException message", TestConstants.TRACE_ID, null);
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleServiceExceptionTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(serviceException.getMessage()).thenReturn("serviceException message");
		Mockito.when(serviceException.getStackTrace()).thenReturn(trace);
		Mockito.when(serviceException.getErrorCodes()).thenReturn(Collections.singletonList("500"));
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleServiceException(serviceException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SERVICE_EXCEPTION",
				"serviceException message", TestConstants.TRACE_ID, Collections.singletonList("500"));
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleServiceExceptionTest_when_status_code_isPresent() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(serviceException.getMessage()).thenReturn("serviceException message");
		Mockito.when(serviceException.getStackTrace()).thenReturn(trace);
		Mockito.when(serviceException.getErrorCodes()).thenReturn(Collections.singletonList("500"));
		Mockito.when(serviceException.getStatusCode()).thenReturn("SERVICE_EXCEPTION");
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleServiceException(serviceException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SERVICE_EXCEPTION",
				"serviceException message", TestConstants.TRACE_ID, Collections.singletonList("500"));
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleInvalidArgumentExceptionTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(invalidArgumentException.getMessage()).thenReturn("invalidArgumentException message");
		Mockito.when(invalidArgumentException.getStackTrace()).thenReturn(trace);
		Mockito.when(invalidArgumentException.getErrorCodes()).thenReturn(Collections.singletonList("400"));
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleInvalidArgumentException(invalidArgumentException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "INVALID_ARGUMENT",
				"invalidArgumentException message", TestConstants.TRACE_ID, Collections.singletonList("400"));
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleInvalidArgumentExceptionTest_when_status_code_isPresent() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(invalidArgumentException.getMessage()).thenReturn("invalidArgumentException message");
		Mockito.when(invalidArgumentException.getStackTrace()).thenReturn(trace);
		Mockito.when(invalidArgumentException.getErrorCodes()).thenReturn(Collections.singletonList("400"));
		Mockito.when(invalidArgumentException.getStatusCode()).thenReturn("INVALID_ARGUMENT");
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleInvalidArgumentException(invalidArgumentException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.value(), "INVALID_ARGUMENT",
				"invalidArgumentException message", TestConstants.TRACE_ID, Collections.singletonList("400"));
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleIllegalAccessExceptionTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(illegalAccessException.getMessage()).thenReturn("illegalAccessException message");
		Mockito.when(illegalAccessException.getStackTrace()).thenReturn(trace);
		Mockito.when(illegalAccessException.getErrorCodes()).thenReturn(Collections.singletonList("401"));
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleAccessException(illegalAccessException, webRequest);

		final CustomError customError = new CustomError(HttpStatus.UNAUTHORIZED.value(), "ILLEGAL_ACCESS",
				"illegalAccessException message", TestConstants.TRACE_ID, Collections.singletonList("401"));
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

	@Test
	public void handleAllExceptionsTest() {
		final StackTraceElement[] trace = new StackTraceElement[] {
				new StackTraceElement("ClassName1", "methodName1", "fileName1", 10),
				new StackTraceElement("ClassName2", "methodName2", "fileName2", 20),
				new StackTraceElement("ClassName3", "methodName3", "fileName3", 14) };
		Mockito.when(exception.getMessage()).thenReturn("exception message");
		Mockito.when(exception.getStackTrace()).thenReturn(trace);
		Mockito.when(webRequest.getHeader(ApplicationConstants.TRACE_ID)).thenReturn(TestConstants.TRACE_ID);

		final ResponseEntity<ResponseDto<Object>> actualResponse = globalResponseExceptionInterceptor
				.handleAll(exception, webRequest);

		final CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"INTERNAL_SERVER_ERROR",
				"exception message", TestConstants.TRACE_ID, null);
		final ResponseDto<Object> expectedResponse = new ResponseDto<>(false, null, customError);

		Assert.assertEquals(expectedResponse.getClientMeta(),
				Objects.requireNonNull(actualResponse.getBody()).getClientMeta());
		Assert.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
		Assert.assertEquals(expectedResponse.getError(), actualResponse.getBody().getError());
		Assert.assertEquals(expectedResponse.getSuccess(), actualResponse.getBody().getSuccess());
		Assert.assertEquals(expectedResponse.getVersion(), actualResponse.getBody().getVersion());

		Mockito.verify(webRequest).getHeader(ApplicationConstants.TRACE_ID);
	}

}
