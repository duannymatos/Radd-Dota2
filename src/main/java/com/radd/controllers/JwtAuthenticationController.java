package com.radd.controllers;
//With help from https://www.javainuse.com/spring/boot-jwt
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.radd.config.JwtTokenUtil;
import com.radd.models.ApplicationUser;
import com.radd.models.JwtRequest;
import com.radd.models.JwtResponse;
import com.radd.models.UnwiredApplicationUser;
import com.radd.services.JwtUserDetailsService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class JwtAuthenticationController 
{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtServ;

	//Throws exception if invalid login to show correct response
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest user) throws Exception 
	{

		authenticate(user.getUsername(), user.getPassword());

		//call loadUserByUsernameToGoToDatabase
		final UserDetails userDetails = jwtServ
				.loadUserByUsername(user.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	

		
	@RequestMapping(value = "/user/role", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UnwiredApplicationUser user, @RequestHeader(value="Authorization",required = false) String token) throws Exception
	{
		//get the users current role to check if they are an admin in order to create new accounts
		String role = new String("0");
		if(token!=null)
		{
			role= jwtTokenUtil.getRole(token);
		}
		
		if(Integer.parseInt(role)>=2)
		{
			ApplicationUser tUser=jwtServ.save(user);
			//inform them if the username is already in database as User is and always will be in database
			if(tUser.getUsername()=="User")
			{
				return new ResponseEntity<String>("Username already in use",HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(tUser);
		}
		return new ResponseEntity<String>("Must be an admin to create accounts.",HttpStatus.BAD_REQUEST);
	}

	
	private void authenticate(String username, String password) throws Exception 
	{
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} 
		catch (DisabledException e) 
		{
			throw new Exception("USER_DISABLED", e);
		} 
		catch (BadCredentialsException e) 
		{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}