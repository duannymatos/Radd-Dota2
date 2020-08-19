package com.radd.models;
//Gotten from https://www.javainuse.com/spring/boot-jwt
import java.io.Serializable;
import java.io.Serializable;

//Response class object to return java webtoken
public class JwtResponse implements Serializable 
{

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}