package com.radd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaStage;

@Repository
public interface DotaStageRepository extends JpaRepository<DotaStage,String> 
{
	
}
