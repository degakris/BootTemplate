package com.noon.guestparking.interceptor.filter;

import com.noon.guestparking.constants.ApplicationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RequestHeaderFilter implements Filter {
	private final Tracer tracer;

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		if (null == httpRequest.getHeader(ApplicationConstants.TRACE_ID)) {
			final MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(
					(HttpServletRequest) servletRequest);
			final String traceId = Optional.ofNullable(tracer.currentSpan()).map(span -> span.context().traceId())
					.orElse(UUID.randomUUID().toString());
			mutableRequest.putHeader(ApplicationConstants.TRACE_ID, traceId);
			httpRequest = mutableRequest;
		}
		final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		httpServletResponse
				.setHeader(ApplicationConstants.TRACE_ID, httpRequest.getHeader(ApplicationConstants.TRACE_ID));
		filterChain.doFilter(httpRequest, servletResponse);
	}
}
