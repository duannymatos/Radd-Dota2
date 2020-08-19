package com.radd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaItemStage;

@Repository
public interface DotaItemStageRepository extends JpaRepository<DotaItemStage, String> {

	public List<DotaItemStage> findByHeroId_HeroId(String heroId);
}
