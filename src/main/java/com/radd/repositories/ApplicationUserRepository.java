package com.radd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String>{
	//autogenerate a find by username method
	ApplicationUser findByUsername(String username);

}
