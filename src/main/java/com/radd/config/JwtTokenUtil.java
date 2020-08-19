package com.radd.config;
//With help from https://www.javainuse.com/spring/boot-jwt
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable 
{

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	//inject value from Configuration.properties
	
	@Value("${jwt.secret}")
	private String secret;

	//retrieve username from jwt token
	public String getUsernameFromToken(String token) 
	{
		Claims claims = getAllClaimsFromToken(token);
		return (String)claims.getSubject();
	}
	
	//getRole for admin powers
	public String getRole(String token)
	{
		Claims claims = getAllClaimsFromToken(token);
		return (String)claims.get("ROLE");
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) 
	{
		Claims claims = getAllClaimsFromToken(token);
		return claims.getExpiration();
	}

    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) 
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) 
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(UserDetails userDetails) 
	{
		Map<String, Object> claims = new HashMap<>();
		Iterator<? extends GrantedAuthority> itr =userDetails.getAuthorities().iterator();
		
		//add in all claims
		while(itr.hasNext())
		{
			String[] individualClaim=itr.next().toString().split("_");
			claims.put(individualClaim[0],individualClaim[1]);
		}
		
		/*
		Set<String> claimKeys=claims.keySet();
		
		
      ///check claims
		for(String claimValues: claimKeys)
		{
			System.out.println("-------------");
			System.out.println(claims.get(claimKeys));
			System.out.println(claims.get(claimValues));
			System.out.println("-------------");
		}
		*/

		//set claims of token
		//Use algorith HS512 to has by secretKey
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}


	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) 
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}