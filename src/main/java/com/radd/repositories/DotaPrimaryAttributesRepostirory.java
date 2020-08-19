package com.radd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaPrimaryAttributes;

@Repository
public interface DotaPrimaryAttributesRepostirory extends JpaRepository<DotaPrimaryAttributes, String>{
	
}
