package com.radd.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ApplicationUser {
	
		@Id
		private String username;
		
		@Column(name="pass")
		@JsonIgnore
		private String password;
	
		private int userRole;

		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getUserRole() {
			return userRole;
		}

		public void setUserRole(int userRole) {
			this.userRole = userRole;
		}
		
		
}
