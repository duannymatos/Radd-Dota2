package com.radd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radd.models.DotaCounters;
import com.radd.models.DotaHero;

@Repository
public interface DotaCountersRepository extends JpaRepository<DotaCounters, String>
{
	
}
