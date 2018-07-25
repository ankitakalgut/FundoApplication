package com.bridgelabz.toDoApplication.utilservice;

import java.util.Date;
import java.util.logging.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/*********************************************************************************
 * @author Ankita Kalgutkar
 *
 * 
 *PURPOSE:creating token
 ************************************************************************************/
public class JWToken 
{
	static Logger logger = Logger.getLogger(JWToken.class.getName());
	public String createJWT(String issuer, String email)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date((System.currentTimeMillis()+600000)))
				.setIssuedAt(new Date())
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, "passKey");
				logger.info(builder.compact());
				return builder.compact();
	}

	public String verifyToken(String token) 
	{
		try
		{
			Claims claims = Jwts.parser()
					.setSigningKey("passKey")
					.parseClaimsJws(token)
					.getBody();
			return claims.getSubject();
		} 
		catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			throw new JWTException("Error in verifying JW Token");
		} 
		catch (ExpiredJwtException e) 
		{
			throw new JWTException("Token Expired");
		}
	}
}
