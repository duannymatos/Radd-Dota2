package com.radd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaHero;
import com.radd.models.DotaPrimaryAttributes;

@Repository
public interface DotaHeroRepository extends JpaRepository<DotaHero, String> {
	
	public DotaHero findByHeroId(String id);
	public DotaHero findByHeroName(String name);
	public List<DotaHero> findByAtri_PrimaryAtri(String attribute);
	public DotaHero findByAttacktype(String attackType);
}
