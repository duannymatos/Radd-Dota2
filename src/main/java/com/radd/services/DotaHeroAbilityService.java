package com.radd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaHero;
import com.radd.models.DotaHeroAbility;
import com.radd.repositories.DotaHeroAbilityRepository;

@Service
public class DotaHeroAbilityService {

	@Autowired
	DotaHeroAbilityRepository repo;
	
	public List<DotaHeroAbility> getAllAbilities(){
		return repo.findAll();
	}
	
	public List<DotaHeroAbility> getAbilityByHeroId(String id){
		return repo.findByHeroId_HeroId(id);
	}
	
	public List<DotaHeroAbility> getAbilityByHeroName(String name){
		return repo.findByHeroId_HeroName(name);
	}
	
	public DotaHeroAbility createAbility(DotaHeroAbility ability) {
		return repo.save(ability);
	}
	
	public DotaHeroAbility updateAbility(DotaHeroAbility ability) {
		return repo.save(ability);
	}
	
	public void deleteAbility(DotaHeroAbility ability) {
		 repo.delete(ability);
	}
}
