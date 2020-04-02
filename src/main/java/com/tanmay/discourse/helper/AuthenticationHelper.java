package com.tanmay.discourse.helper;

import java.util.Date;

import com.tanmay.discourse.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationHelper {

	public static String createSignedJWT(User user) {
		String token = "";
		long currentTime = System.currentTimeMillis();

		token = Jwts.builder().setId(user.getPassword()).setSubject(user.getUsername())
				.setIssuedAt(new Date(currentTime)).setExpiration(new Date(currentTime + 36000000))
				.signWith(SignatureAlgorithm.HS256, "secretKey").setIssuer("discourse-rs").compact();

		return token;
	}

	public static Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
	}
	
	public static boolean verifyClaims(User user, Claims claims) {
		return (
			claims.getId().equals(user.getPassword()) &&
			claims.getIssuer().equals("discourse-rs") &&
			claims.getSubject().equals(user.getUsername()) &&
			claims.getExpiration().after(new Date())
		);
	}

}
