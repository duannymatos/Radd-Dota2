package com.radd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radd.models.DotaItemStage;
import com.radd.repositories.DotaItemStageRepository;

@Service
public class DotaItemStageService {

	@Autowired
	DotaItemStageRepository repo;
	
	public List<DotaItemStage> getAllItemStages(){
		return repo.findAll();
	}
	
	public List<DotaItemStage> getItemsByHero(String heroId){
		return repo.findByHeroId_HeroId(heroId);
	}
}
