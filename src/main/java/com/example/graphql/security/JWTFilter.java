package com.example.graphql.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;

@Slf4j
public class JWTFilter extends GenericFilterBean {

	private final BasicProvider basicProvider;

	private final TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider, BasicProvider basicProvider) {
		this.tokenProvider = tokenProvider;
		this.basicProvider = basicProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		try {
			Authentication authentication = null;
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			String idToken = resolveToken(httpServletRequest);
			if (Boolean.TRUE.equals(isBearer(httpServletRequest))) {
				authentication = tokenProvider.getAuthentication(idToken);
			} else {
				authentication = basicProvider.getAuthentication(idToken);
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(servletRequest, servletResponse);
			this.resetAuthenticationAfterRequest();
		} catch (WebApplicationException e) {
			log.error(e.getMessage(), e);
			((HttpServletResponse) servletResponse).setStatus(e.getResponse().getStatus());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private void resetAuthenticationAfterRequest() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	private Boolean isBearer(HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(OAuth2AccessToken.BEARER_TYPE + " ")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private String resolveToken(HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(OAuth2AccessToken.BEARER_TYPE + " ")) {
			return authHeader.substring(7);
		}
		return authHeader;
	}
}