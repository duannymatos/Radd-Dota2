package com.radd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaHero;
import com.radd.models.DotaHeroAbility;

@Repository
public interface DotaHeroAbilityRepository extends JpaRepository<DotaHeroAbility, String> {
	
	public List<DotaHeroAbility> findByHeroId_HeroId(String id);
	public List<DotaHeroAbility> findByHeroId_HeroName(String name);

}
