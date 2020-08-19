package com.radd.services;
//With help from https://www.javainuse.com/spring/boot-jwt

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.radd.models.ApplicationUser;
import com.radd.models.UnwiredApplicationUser;
import com.radd.repositories.ApplicationUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService //spring security class
{
	private static final String ROLE_PREFIX="ROLE_";
	//Wire in an object so that we can save it
	@Autowired
	private ApplicationUserRepository repo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		ApplicationUser user = repo.findByUsername(username);
		
		//Check if username is in database if not throw error
		if (user==null) 
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		} 
		//Setup authorities
		List<GrantedAuthority> authorities = new LinkedList();
		
		authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+user.getUserRole()));
		//Pass details to spring security core if its in the database
		User userToToken =new User(user.getUsername(), user.getPassword(),authorities);
		return userToToken;
	}
	
	//Save method for creating a new Application user
	public ApplicationUser save(UnwiredApplicationUser user) throws Exception 
	{
		ApplicationUser u = new ApplicationUser();
		//check if its already in database
		if(repo.findByUsername(user.getUsername())==null)
		{
			u.setUsername(user.getUsername());
			//Give everyone default role
			u.setUserRole(1);
			
			//Encrypt password and store it
			u.setPassword(bcryptEncoder.encode(user.getPassword()));
			
			//call save method to store user in database
			return repo.save(u);
		}
		else 
		{
		u.setUsername("User");
		return u;
		}
	}
}