package com.radd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaHero;
import com.radd.models.DotaPrimaryAttributes;
import com.radd.models.DotaStage;
import com.radd.repositories.DotaHeroRepository;

@Service
public class DotaHeroService {

	@Autowired
	DotaHeroRepository repo;
	
	public List<DotaHero> getAllHeros()
	{
		return repo.findAll();
	}
	
	public DotaHero getHeroById(String id) {
		return repo.findByHeroId(id);
	}
	
	public DotaHero getHeroByName(String name) {
		return repo.findByHeroName(name);
	}
	
	public DotaHero createHero(DotaHero hero) {
		return repo.save(hero);
	}
	
	public DotaHero updateHero(DotaHero hero) {
		return repo.save(hero);
	}
	
	public void deleteHero(DotaHero hero) {
		 repo.delete(hero);
	}
	
	public DotaHero getByAttackType(String type) {
		return repo.findByAttacktype(type);
	}
	
	public List<DotaHero> getByAtri(String attribute) {
		return repo.findByAtri_PrimaryAtri(attribute);
	}
	
	
}
