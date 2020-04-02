package com.tanmay.discourse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.tanmay.discourse.helper.AuthenticationHelper;
import com.tanmay.discourse.model.User;
import com.tanmay.discourse.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

@Component
public class AuthenticationFilter implements Filter {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		final String tokenHeader = req.getHeader("authorization");
		
		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (null == tokenHeader || !tokenHeader.startsWith("Bearer ")) {
				sendUnauthorizedResponse(res);
			}
			
			try {
				final String token = tokenHeader.substring(7);
				Claims claims = AuthenticationHelper.getClaimsFromToken(token);
				User user = userRepository.findByUsername(claims.getSubject());
				if (AuthenticationHelper.verifyClaims(user, claims)) {
					chain.doFilter(req, res);
				} else {
					sendUnauthorizedResponse(res);
				}
				
			} catch (SignatureException e) {
				sendUnauthorizedResponse(res);
			}
		}
	}

	private void sendUnauthorizedResponse(final HttpServletResponse res) throws IOException {
		res.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid credentials");
	}

}
