package com.jkxy.poetry.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 过滤器，将页面编码统一为UTF-8
 * @author Yanqiang
 *
 */
public class EncodingFilter implements Filter {

	private String characterEncoding;

	public void init(FilterConfig filterConfig) throws ServletException {
		characterEncoding = filterConfig.getInitParameter("encoding");
		if (characterEncoding == null) {
			throw new ServletException("字符编码为空！");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!characterEncoding.equalsIgnoreCase(request.getCharacterEncoding())) {
			request.setCharacterEncoding(characterEncoding);
		}
		response.setCharacterEncoding(characterEncoding);
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
