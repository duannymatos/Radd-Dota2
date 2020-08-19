package com.radd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaPrimaryAttributes;
import com.radd.repositories.DotaPrimaryAttributesRepostirory;

@Service
public class DotaPrimaryAttributesService {

	@Autowired
	DotaPrimaryAttributesRepostirory repo;
	
	public List<DotaPrimaryAttributes> getAllAtributes() {
		return repo.findAll();
	}
}
