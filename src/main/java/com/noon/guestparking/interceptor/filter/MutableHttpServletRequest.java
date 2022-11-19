package com.noon.guestparking.interceptor.filter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Primary
@Component
public class MutableHttpServletRequest extends HttpServletRequestWrapper {
	private final Map<String, String> customHeaders;

	public MutableHttpServletRequest(final HttpServletRequest request) {
		super(request);
		this.customHeaders = new HashMap<>();
	}

	public void putHeader(final String name, final String value) {
		this.customHeaders.put(name, value);
	}

	@Override
	public String getHeader(String name) {
		final String headerValue = customHeaders.get(name);
		if (null != headerValue) {
			return headerValue;
		}
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		final Set<String> set = new HashSet<>(customHeaders.keySet());
		final Enumeration<String> headerNames = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (headerNames.hasMoreElements()) {
			set.add(headerNames.nextElement());
		}
		return Collections.enumeration(set);
	}
}