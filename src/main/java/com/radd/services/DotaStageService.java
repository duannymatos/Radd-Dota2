package com.radd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaStage;
import com.radd.repositories.DotaStageRepository;

@Service
public class DotaStageService
{
	@Autowired
	DotaStageRepository repo;
	
	public List<DotaStage> getAllStages()
	{
		return repo.findAll();
	}
}
