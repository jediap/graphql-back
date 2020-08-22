package com.example.graphql.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

@Service
@Data
public class BasicProvider {

	public Authentication getAuthentication(final String token) {
		try {
			String credentials[] = credentialsDecode(token);
			String username = credentials[0];
			String password = credentials[1];
			if ("test".equals(username) && "test".equals(password)) {
				final OpenIdConnectUserDetails userConnect = new OpenIdConnectUserDetails(1L, "test", "test", true, true, true, true, new ArrayList<>(), null);
				return new UsernamePasswordAuthenticationToken(userConnect, null, userConnect.getAuthorities());
			}else{
				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}

	private String[] credentialsDecode(final String token) {
		String base64Credentials = token.substring("Basic".length()).trim();
		byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		return credentials.split(":");
	}
}
