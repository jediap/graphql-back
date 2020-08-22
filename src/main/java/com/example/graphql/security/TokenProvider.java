package com.example.graphql.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Component
@Data
public class TokenProvider {


	public Authentication getAuthentication(final String token) {
		try {
			Boolean isResult = Boolean.TRUE;
			if ("Bearer test".equals(token)) {
				final OpenIdConnectUserDetails user = new OpenIdConnectUserDetails(1L, "test", null, isResult, true, true, true, new ArrayList<>(), null);
				return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			}else{
				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}
}
