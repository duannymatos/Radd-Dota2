package com.radd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaCounters;
import com.radd.models.DotaHero;
import com.radd.repositories.DotaCountersRepository;
import com.radd.repositories.DotaHeroRepository;

@Service
public class DotaCounterService
{
	
	@Autowired
	DotaCountersRepository repo;
	@Autowired
	DotaHeroRepository heroRepo;
	
	public DotaCounters getCounter(String id)
	{
		return	repo.getOne(id);
	}
	public DotaCounters updateCounter(DotaCounters hero) {
		return repo.save(hero);
	}
}
